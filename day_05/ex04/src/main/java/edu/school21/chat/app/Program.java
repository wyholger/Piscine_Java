package edu.school21.chat.app;

import edu.school21.chat.models.User;
import edu.school21.chat.repositories.*;

import javax.sql.DataSource;
import java.util.List;

import static edu.school21.chat.util.InitConnection.*;

public class Program
{
	public static void main(String[] args)
	{
		DataSource data_source = init_data_source("jdbc:postgresql://localhost:5432/postgres", "wyholger", "");
		UsersRepository users_repository = new UsersRepositoryJdbcImpl(data_source);

		List<User> list = users_repository.findAll(3, 4);
		for (User user:	list)
			System.out.println(user);
	}
}
