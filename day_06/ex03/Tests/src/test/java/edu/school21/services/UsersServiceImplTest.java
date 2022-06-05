package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class UsersServiceImplTest
{
	private UsersRepository users_repository;
	private UsersServiceImpl users_service;

	@BeforeEach
	public void init()
	{
		users_repository = Mockito.mock(UsersRepository.class);
		users_service = new UsersServiceImpl(users_repository);
	}

	@Test
	public void correct_login_password() throws EntityNotFoundException, AlreadyAuthenticatedException
	{
		boolean success;
		User user = new User(0L, "petya", "qwerty", false);
		when(users_repository.findByLogin("petya")).thenReturn(user);

		success = users_service.authenticate("petya", "qwerty");
		Assertions.assertTrue(success);

		verify(users_repository).update(user);
	}

	@Test
	public void incorrect_login() throws EntityNotFoundException
	{
		when(users_repository.findByLogin("not_exist_login")).thenThrow(new EntityNotFoundException());
		Assertions.assertThrows(EntityNotFoundException.class, () -> users_service.authenticate("not_exist_login", "not_exist_pass"));
	}

	@Test
	public void incorrect_pass() throws EntityNotFoundException, AlreadyAuthenticatedException
	{
		boolean success;
		User user = new User(0L, "petya", "true_pass", false);

		when(users_repository.findByLogin("petya")).thenReturn(user);
		success = users_service.authenticate("petya", "false_pass");
		Assertions.assertFalse(success);
	}


	@Test
	public void already_authenticated_user() throws EntityNotFoundException
	{
		boolean success;
		User user = new User(0L, "petya", "true_pass", true);

		when(users_repository.findByLogin("petya")).thenReturn(user);
		Assertions.assertThrows(AlreadyAuthenticatedException.class, () -> users_service.authenticate("petya", "true_pass"));
	}
}
