package school21.spring.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

@Configuration
@ComponentScan(basePackages = "school21.spring.service")
public class ApplicationConfig
{
	@Bean
	public UsersRepositoryJdbcImpl.HikariDS standardDataSource()
	{
		return new UsersRepositoryJdbcImpl.HikariDS();
	}

	@Bean
	public UsersRepositoryJdbcImpl standardJDBC()
	{
		return new UsersRepositoryJdbcImpl(standardDataSource());
	}

	@Bean
	public UsersRepositoryJdbcTemplateImpl.ManagerDS templateDataSource()
	{
		return new UsersRepositoryJdbcTemplateImpl.ManagerDS();
	}

	@Bean
	public UsersRepositoryJdbcTemplateImpl templateJDBC()
	{
		return new UsersRepositoryJdbcTemplateImpl(templateDataSource());
	}
}
