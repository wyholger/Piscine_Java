package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.config.ApplicationConfig;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

import java.util.Optional;

import static school21.spring.service.application.Main.Color.*;

public class Main
{
	public static void main(String[] args)
	{
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		UsersRepositoryJdbcImpl spring_user_repo = context.getBean("usersRepositoryJdbcImpl", UsersRepositoryJdbcImpl.class);
		Optional<User> spring_user = spring_user_repo.findById(1L);
		System.out.println(PURPLE + "Result SQL command with spring and simple JDBS repository realisation" + RESET);
		System.out.println(YELLOW + "" + spring_user.orElse(null) + RESET);

		UsersRepositoryJdbcTemplateImpl spring_template_user_repo = context.getBean("usersRepositoryJdbcTemplateImpl", UsersRepositoryJdbcTemplateImpl.class);
		Optional<User> spring_template_user = spring_template_user_repo.findById(1L);
		System.out.println(PURPLE + "Result SQL command with spring and template JDBS repository realisation" + RESET);
		System.out.println(YELLOW + "" + spring_template_user.orElse(null) + RESET);
	}

	public enum Color
	{
		RESET("\033[0m"),

		BLACK("\033[0;30m"),
		PURPLE("\033[0;35m"),
		RED("\033[0;31m"),
		GREEN("\033[0;32m"),
		YELLOW("\033[0;33m"),
		BLUE("\033[0;34m"),
		MAGENTA("\033[0;35m"),
		CYAN("\033[0;36m"),
		WHITE("\033[0;37m");
		private final String code;
		Color(String code)
		{
			this.code = code;
		}
		@Override
		public String toString()
		{
			return code;
		}
	}
}
