package com.company;

public class Thread
{
	public static void run(String name, Integer count)
	{
		for (int i = 0; i < count; i++)
		{
			System.out.println(name);
		}
	}
}
