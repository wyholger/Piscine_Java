package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository
{
	private final Connection connection;
	private final ChatroomsRepository chat_rooms_repository;
	private final UsersRepository users_repository;

	public MessagesRepositoryJdbcImpl(Connection connection, ChatroomsRepository chat_rooms_repository, UsersRepository users_repository)
	{
		this.connection = connection;
		this.chat_rooms_repository = chat_rooms_repository;
		this.users_repository = users_repository;
	}

	@Override
	public Optional<Message> findById(Long id)
	{
		String SQL_SELECT_BY_ID = "SELECT * FROM chat.messages WHERE id=?";
		PreparedStatement prepared_statement;
		ResultSet result_set;
		Message massage;
		Optional<Message> optional_message = Optional.empty();

		try
		{
			prepared_statement = connection.prepareStatement(SQL_SELECT_BY_ID);
			prepared_statement.setLong(1, id);
			result_set = prepared_statement.executeQuery();
			if (result_set.next())
			{
				Timestamp timestamp = result_set.getTimestamp("timestamp");
				LocalDateTime time;
				if (timestamp != null)
					time = timestamp.toLocalDateTime();
				else
					time = null;
				massage = new Message(result_set.getLong("id"),
						users_repository.findById(result_set.getLong("author")).orElse(null),
						chat_rooms_repository.findById(result_set.getLong("room")).orElse(null),
						result_set.getString("text"),
						time);
				optional_message = Optional.of(massage);
			}
		}
		catch (SQLException e)
		{
			System.out.println("Massage by id " + id + " not found.");
			System.out.println(e.getMessage());
			return Optional.empty();
		}
		return optional_message;
	}

	@Override
	public void save(Message message)
	{
		String SQL_INSERT_MESSAGE = "INSERT INTO chat.messages(author, room, text, timestamp) VALUES (?, ?, ?, ?) RETURNING *";
		PreparedStatement prepared_statement;
		ResultSet result_set;
		Optional<Message> optional_message = Optional.empty();

		try
		{
			if (message.getAuthor() != null && message.getRoom() != null && users_repository.findById(message.getAuthor().getId()).isPresent()
					&& chat_rooms_repository.findById(message.getRoom().getId()).isPresent())
			{
				prepared_statement = connection.prepareStatement(SQL_INSERT_MESSAGE);
				prepared_statement.setLong(1, message.getAuthor().getId());
				prepared_statement.setLong(2, message.getRoom().getId());
				prepared_statement.setString(3, message.getText());
				prepared_statement.setTimestamp(4, Timestamp.valueOf(message.getData_time()));
				result_set = prepared_statement.executeQuery();
				result_set.next();
				message.setId(result_set.getLong("id"));
			}
			else
				throw new NotSavedSubEntityException();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void update(Message message)
	{
		String SQL_UPDATE_MESSAGE = "UPDATE chat.messages SET author = ?, room = ?, text = ?, timestamp = ? WHERE id = ?";
		PreparedStatement prepared_statement;

		try
		{
			prepared_statement = connection.prepareStatement(SQL_UPDATE_MESSAGE);
			prepared_statement.setLong(1, message.getAuthor().getId());
			prepared_statement.setLong(2, message.getRoom().getId());
			prepared_statement.setString(3, message.getText());
			try
			{
				prepared_statement.setTimestamp(4, Timestamp.valueOf(message.getData_time()));
			}
			catch (NullPointerException e)
			{
				prepared_statement.setTimestamp(4, null);
			}
			prepared_statement.setLong(5, message.getId());
			prepared_statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
