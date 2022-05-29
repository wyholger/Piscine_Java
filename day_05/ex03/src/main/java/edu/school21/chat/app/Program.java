package edu.school21.chat.app;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.*;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Optional;

import static edu.school21.chat.util.Color.*;
import static edu.school21.chat.util.InitConnection.init_connection;

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
		Optional<Message> optional_message = messages_repository.findById(1L);
		if (optional_message.isPresent())
		{
			Message message = optional_message.get();
			System.out.println(MAGENTA + "Massage before changes" + RESET);
			System.out.println(message);
			message.setText("NEW TEXT MESSAGE!");
			message.setData_time(null);
			messages_repository.update(message);
			optional_message = messages_repository.findById(1L);
			if (optional_message.isPresent())
			{
				message = optional_message.get();
				System.out.println(MAGENTA + "Massage after changes" + RESET);
				System.out.println(message);
			}
		}
	}
}

