package com.company;

public class UserIdsGenerator
{
	private static UserIdsGenerator instance;
	private static Integer id = 0;

	private UserIdsGenerator()
	{

	}

	public static UserIdsGenerator getInstance()
	{
		if(instance == null)
		{
			instance = new UserIdsGenerator();
		}
		return instance;
	}

	public Integer generateId()
	{
		id++;
		return (id);
	}
}
