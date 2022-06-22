package school21.spring.service.repositories;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import school21.spring.service.models.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Component
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository
{
	JdbcTemplate jdbc_template;
//	UserRowMapper user_row_mapper;

	public UsersRepositoryJdbcTemplateImpl(ManagerDS manager_data_source)
	{
		this.jdbc_template = new JdbcTemplate(manager_data_source.manager_data_source);
	}

	@Override
	public Optional<User> findById(Long id)
	{
		return jdbc_template.query("SELECT * FROM users WHERE id=?", new Object[]{id}, new UserRowMapper())
				.stream().findAny();
	}

	@Override
	public List<User> findAll()
	{
		return jdbc_template.query("SELECT * FROM users ORDER BY id", new UserRowMapper());
	}

	@Override
	public void save(User entity)
	{
		try
		{
			jdbc_template.update("INSERT INTO users(email) VALUES (?)", entity.getEmail());
		}
		catch (DuplicateKeyException e)
		{
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void update(User entity)
	{
		jdbc_template.update("UPDATE users SET email = ? WHERE id = ?", entity.getEmail(), entity.getId());
	}

	@Override
	public void delete(Long id)
	{
		jdbc_template.update("DELETE FROM users WHERE id = ?", id);
	}

	@Override
	public Optional<User> findByEmail(String email)
	{
		return jdbc_template.query("SELECT * FROM users WHERE email = ?", new Object[]{email}, new UserRowMapper())
				.stream().findAny();
	}

	private static class UserRowMapper implements RowMapper<User>
	{
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setEmail(rs.getString("email"));
			return user;
		}
	}


	public static class ManagerDS
	{
		public DriverManagerDataSource manager_data_source;
		private Properties properties;

		public ManagerDS()
		{
			manager_data_source = new DriverManagerDataSource();
			try
			{
				properties = new Properties();
				InputStream is = UsersRepositoryJdbcImpl.HikariDS.class.getClassLoader().getResourceAsStream("db.properties");
				properties.load(is);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			manager_data_source.setDriverClassName(properties.getProperty("db.driver.name"));
			manager_data_source.setUrl(properties.getProperty("db.url"));
			manager_data_source.setUsername(properties.getProperty("db.user"));
			manager_data_source.setPassword(properties.getProperty("db.password"));
		}
	}
}
