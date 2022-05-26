package edu.school21.chat.repositories;

import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository
{
	private final Connection connection;

	public UsersRepositoryJdbcImpl(Connection connection)
	{
		this.connection = connection;
	}

	@Override
	public Optional<User> findById(Long id)
	{
		String SQL_SELECT_BY_ID = "SELECT * FROM chat.users WHERE id=?";
		PreparedStatement prepared_statement;
		ResultSet result_set;
		User user;
		Optional<User> optional_user = Optional.empty();

		try
		{
			prepared_statement = connection.prepareStatement(SQL_SELECT_BY_ID);
			prepared_statement.setLong(1, id);
			result_set = prepared_statement.executeQuery();
			if (result_set.next())
			{
				user = new User(result_set.getLong("id"),
						result_set.getString("login"),
						result_set.getString("password"));
				optional_user = Optional.of(user);
			}
		}
		catch (SQLException e)
		{
			System.out.println("User by id " + id + " not found.");
			System.out.println(e.getMessage());
			return Optional.empty();
		}
		return optional_user;
	}
}
