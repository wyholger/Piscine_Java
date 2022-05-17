package com.company;

import static com.company.CheckerArgs.*;

public class Program
{

	private static Integer thread_count;
	private Loader loader;

	public static void main(String[] args)
	{
		if (!check_arg(args))
			System.exit(1);
		Loader loader = new Loader();
		loader.loading();
		System.exit(0);
	}

	private static boolean check_arg(String[] args)
	{
		Integer thread_count;

		if (!check_one_arg(args))
		{
			print_err_msg_with_example(MSG_ERR_NUM_ARG, EXAMPLE_ARGS, DETAILS);
			return false;
		}
		if ((thread_count = check_valid_arg(args)) == null)
		{
			print_err_msg_with_example(MSG_NO_VALID_ARG, EXAMPLE_ARGS, DETAILS);
			return false;
		}
		Program.thread_count = thread_count;
		return true;
	}

	public Integer getThread_count()
	{
		return thread_count;
	}

}
