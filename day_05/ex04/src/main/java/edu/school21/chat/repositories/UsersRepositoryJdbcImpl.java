package edu.school21.chat.repositories;

import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository
{
	private final DataSource data_source;
	private Connection connection;

	public UsersRepositoryJdbcImpl(DataSource data_source)
	{
		this.data_source = data_source;
		this.connection = null;
	}

	public UsersRepositoryJdbcImpl(Connection connection)
	{
		this.data_source = null;
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

	@Override
	public List<User> findAll(int page, int size)
	{
		String SQL_SELECT_BY_PAGE = "SELECT * FROM chat.users ORDER BY id OFFSET ? FETCH NEXT ? ROWS ONLY";
		PreparedStatement prepared_statement;
		ResultSet result_set;
		List<User> list = null;

		try
		{
			if (data_source != null)
				connection = data_source.getConnection();
			else
				return null;
			prepared_statement = connection.prepareStatement(SQL_SELECT_BY_PAGE);
			prepared_statement.setLong(1, (long) page * size);
			prepared_statement.setLong(2, size);
			result_set = prepared_statement.executeQuery();
			list = new ArrayList<>();

			while (result_set.next())
			{
				Long id = result_set.getLong("id");
				String login = result_set.getString("login");
				String password = result_set.getString("password");

				list.add(new User(id, login, password));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return list;
	}
}
