package com.company;

import static com.company.CheckerArgs.*;

public class Program
{
	public static void main(String[] args)
	{
		Integer thread_count;
		Loader loader;

		if ((thread_count = check_arg(args)) == null)
			System.exit(1);
		loader = new Loader(thread_count);
		if (!loader.loading())
			System.exit(1);
		System.exit(0);
	}

	private static Integer check_arg(String[] args)
	{
		Integer thread_count;

		if (!check_one_arg(args))
		{
			print_err_msg_with_example(MSG_ERR_NUM_ARG, EXAMPLE_ARGS, DETAILS);
			return null;
		}
		if ((thread_count = check_valid_arg(args)) == null)
		{
			print_err_msg_with_example(MSG_NO_VALID_ARG, EXAMPLE_ARGS, DETAILS);
			return null;
		}
		return thread_count;
	}
}
