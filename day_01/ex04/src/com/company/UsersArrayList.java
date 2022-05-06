package com.company;

public class UsersArrayList implements UsersList
{
	private User[] users = new User[DEFAULT_SIZE];
	private Integer size = 0;
	private Integer capacity = DEFAULT_SIZE;

	@Override
	public void add_user(User user)
	{
		if (size.equals(capacity))
			realloc();
		users[size] = user;
		size++;
	}

	private void realloc()
	{
		capacity = capacity * DEFAULT_SCALE;
		User[] users_new = new User[capacity];
		for (int i = 0; i < this.size; i++)
		{
			users_new[i] = users[i];
		}
		users = users_new;
	}

	@Override
	public User get_user_by_id(Integer id)
	{
		for (int i = 0; i < size; i++)
		{
			if (users[i].getId().equals(id))
				return users[i];
		}
		throw new UserNotFoundException();
	}

	@Override
	public User get_user_by_index(int index)
	{
		if (index >= size)
			throw new ArrayIndexOutOfBoundsException();
		else
			return users[index];
	}

	@Override
	public Integer length()
	{
		return size;
	}

	private class UserNotFoundException extends RuntimeException
	{
		@Override
		public String toString()
		{
			return "Error. User not found error.";
		}
	}
}
