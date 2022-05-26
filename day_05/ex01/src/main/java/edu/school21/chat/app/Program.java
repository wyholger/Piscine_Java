package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static edu.school21.chat.app.InitConnection.init_connection;

public class Program
{
	public static void main(String[] args)
	{
		Connection connection = init_connection("jdbc:postgresql://localhost:5432/postgres", "wyholger", "");
		if (connection == null)
			return;
		UsersRepository users_repository = new UsersRepositoryJdbcImpl(connection);
		ChatroomsRepository chat_rooms_repository = new ChatroomsRepositoryJdbcImpl(connection, users_repository);
		MessagesRepository messages_repository = new MessagesRepositoryJdbcImpl(connection, chat_rooms_repository, users_repository);


	}
}
