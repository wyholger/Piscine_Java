package school21.spring.service.repositories;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jdk.internal.util.xml.impl.Input;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class UsersRepositoryJdbcImpl implements UsersRepository
{
	private final DataSource data_source;

	public UsersRepositoryJdbcImpl(HikariDS data_source)
	{
		this.data_source = data_source.data_source;
	}

	@Override
	public Optional<User> findById(Long id)
	{
		String SQL_SELECT_BY_ID = "SELECT * FROM users WHERE id=?";
		PreparedStatement prepared_statement;
		ResultSet result_set;
		User user;
		Optional<User> optional_user = Optional.empty();
		Connection connection;

		try
		{
			connection = data_source.getConnection();
			prepared_statement = connection.prepareStatement(SQL_SELECT_BY_ID);
			prepared_statement.setLong(1, id);
			result_set = prepared_statement.executeQuery();
			if (result_set.next())
			{
				user = new User(result_set.getLong("id"),
						result_set.getString("email"));
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
	public List<User> findAll()
	{
		String SQL_SELECT_BY_PAGE = "SELECT * FROM users ORDER BY id";
		PreparedStatement prepared_statement;
		ResultSet result_set;
		List<User> list = null;
		Connection connection;

		try
		{
			connection = data_source.getConnection();
			prepared_statement = connection.prepareStatement(SQL_SELECT_BY_PAGE);
			result_set = prepared_statement.executeQuery();
			list = new ArrayList<>();

			while (result_set.next())
			{
				Long id = result_set.getLong("id");
				String email = result_set.getString("email");
				list.add(new User(id, email));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void save(User entity)
	{
		String SQL_INSERT_MESSAGE = "INSERT INTO users(email) VALUES (?) RETURNING *";
		PreparedStatement prepared_statement;
		ResultSet result_set;
		Connection connection;


		try
		{
			if (entity.getEmail() != null)
			{
				connection = data_source.getConnection();
				prepared_statement = connection.prepareStatement(SQL_INSERT_MESSAGE);
				prepared_statement.setString(1, entity.getEmail());
				result_set = prepared_statement.executeQuery();
				result_set.next();
				entity.setId(result_set.getLong("id"));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	@Override
	public void update(User entity)
	{
		String SQL_UPDATE_MESSAGE = "UPDATE users SET email = ? WHERE id = ?";
		PreparedStatement prepared_statement;
		Connection connection;

		try
		{
			connection = data_source.getConnection();
			prepared_statement = connection.prepareStatement(SQL_UPDATE_MESSAGE);
			prepared_statement.setString(1, entity.getEmail());
			prepared_statement.setLong(2, entity.getId());
			prepared_statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Long id)
	{
		String SQL_DELETE = "DELETE FROM users WHERE id = ?";
		PreparedStatement prepared_statement;
		Connection connection;

		try
		{
			connection = data_source.getConnection();
			prepared_statement = connection.prepareStatement(SQL_DELETE);
			prepared_statement.setLong(1, id);
			prepared_statement.execute();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public Optional<User> findByEmail(String email)
	{
		User user = null;
		String SQL_SELECT_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
		PreparedStatement prepared_statement;
		Connection connection;
		ResultSet result_set;

		try
		{
			connection = data_source.getConnection();
			prepared_statement = connection.prepareStatement(SQL_SELECT_BY_EMAIL);
			prepared_statement.setString(1, email);
			result_set = prepared_statement.executeQuery();
			if (result_set.next())
				user = new User(result_set.getLong("id"), result_set.getString("email"));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return Optional.of(user);
	}

	public static class HikariDS
	{
		public HikariDataSource data_source;

		private Properties properties;

		public HikariDS()
		{
			HikariConfig config = new HikariConfig();
			try
			{
				properties = new Properties();
				InputStream is = HikariDS.class.getClassLoader().getResourceAsStream("db.properties");
				properties.load(is);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			config.setJdbcUrl(properties.getProperty("db.url"));
			config.setUsername(properties.getProperty("db.user"));
			config.setPassword(properties.getProperty("db.password"));
			config.addDataSourceProperty( "cachePrepStmts" , "true" );
			config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
			config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
			data_source = new HikariDataSource(config);
		}
	}
}
