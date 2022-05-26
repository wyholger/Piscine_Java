package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
				massage = new Message(result_set.getLong("id"),
						users_repository.findById(result_set.getLong("author")).orElse(null),
						chat_rooms_repository.findById(result_set.getLong("room")).orElse(null),
						result_set.getString("text"),
						result_set.getTimestamp("timestamp").toLocalDateTime());
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
}
