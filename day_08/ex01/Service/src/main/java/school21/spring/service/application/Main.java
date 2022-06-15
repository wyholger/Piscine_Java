package school21.spring.service.application;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class Main
{
	public static void main(String[] args)
	{
		UsersRepositoryJdbcImpl users_repo = new UsersRepositoryJdbcImpl(new UsersRepositoryJdbcImpl.HikariDS());
		Optional<User> user = users_repo.findById(1L);
		System.out.println(user.orElse(null));

		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		UsersRepositoryJdbcImpl spring_user_repo = context.getBean("usr_repo_JDBS", UsersRepositoryJdbcImpl.class);
		Optional<User> spring_user = spring_user_repo.findById(1L);
		System.out.println(user.orElse(null));

		UsersRepositoryJdbcTemplateImpl users_repo_template = new UsersRepositoryJdbcTemplateImpl(new UsersRepositoryJdbcTemplateImpl.ManagerDS());
		List<User> users = users_repo_template.findAll();
		user = users_repo_template.findById(1L);
		System.out.println(users);
		System.out.println(user);
		User user_new = new User();
		user_new.setEmail("EMAI_2@gmail.com");
		users_repo_template.save(user_new);
		Optional<User> find_new = users_repo_template.findByEmail("EMAI_2@gmail.com");
		System.out.println(find_new);
	}
}
