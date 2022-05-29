package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ChatroomsRepositoryJdbcImpl implements ChatroomsRepository
{
	private final Connection connection;
	private final UsersRepository users_repository;

	public ChatroomsRepositoryJdbcImpl(Connection connection, UsersRepository users_repository)
	{
		this.connection = connection;
		this.users_repository = users_repository;
	}


	@Override
	public Optional<Chatroom> findById(Long id)
	{
		String SQL_SELECT_BY_ID = "SELECT * FROM chat.rooms WHERE id=?";
		PreparedStatement prepared_statement;
		ResultSet result_set;
		Chatroom chat_room;
		Optional<Chatroom> optional_chat_room = Optional.empty();
		User user;
		String owner;

		try
		{
			prepared_statement = connection.prepareStatement(SQL_SELECT_BY_ID);
			prepared_statement.setLong(1, id);
			result_set = prepared_statement.executeQuery();
			if (result_set.next())
			{
				user = users_repository.findById(result_set.getLong("owner")).orElse(null);
				chat_room = new Chatroom(result_set.getLong("id"),
						result_set.getString("name"),
						user);
				optional_chat_room = Optional.of(chat_room);
			}
		}
		catch (SQLException e)
		{
			System.out.println("Chat room by id " + id + " not found.");
			System.out.println(e.getMessage());
			return Optional.empty();
		}
		return optional_chat_room;
	}
}
