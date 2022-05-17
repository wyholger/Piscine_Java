package com.company;

import static com.company.CheckerArgs.*;

public class Program
{

	private static Integer thread_count;

	public static void main(String[] args)
	{
		if (!check_arg(args))
			System.exit(1);
	}

	private static boolean check_arg(String[] args)
	{
		Integer thread_count;

		if (!check_one_arg(args))
			return false;
		if ((thread_count = check_valid_arg(args)) == null)
			return false;
		Program.thread_count = thread_count;
		return true;
	}

	public Integer getThread_count()
	{
		return thread_count;
	}

}
