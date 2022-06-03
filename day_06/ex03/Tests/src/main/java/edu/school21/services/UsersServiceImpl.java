package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

public class UsersServiceImpl
{

	private final UsersRepository users_repository;

	public UsersServiceImpl(UsersRepository users_repository)
	{
		this.users_repository = users_repository;
	}

	boolean authenticate(String login, String password) throws EntityNotFoundException, AlreadyAuthenticatedException
	{
		User user;

		user = users_repository.findByLogin(login);
		if (user.isAuthentication())
			throw new AlreadyAuthenticatedException();
		if (!password.equals(user.getPassword()))
			return false;
		user.setAuthentication(true);
		users_repository.update(user);
		return true;
	}
}
