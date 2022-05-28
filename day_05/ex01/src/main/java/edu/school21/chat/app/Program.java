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
import java.util.Scanner;

import static edu.school21.chat.app.Color.*;
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
		System.out.println(BLUE + "Enter a " + YELLOW + "message ID " + RESET);
		Scanner scan = new Scanner(System.in);
		if (scan.hasNextLong())
		{
			Long id = scan.nextLong();
			Message message = messages_repository.findById(id).orElse(null);
			if (message == null)
				System.out.println(RED + "Message bi id " + YELLOW + id + RED + " not found.");
			else
				System.out.println(message);
		}
		else
			System.out.println(RED + "Error. Incorrect message ID." + RESET);
	}
}
