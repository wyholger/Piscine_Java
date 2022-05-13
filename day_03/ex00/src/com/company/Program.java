package com.company;

import static com.company.CheckerArgs.*;

public class Program
{
	private static Integer count;

	public static void main(String[] args)
	{
		Integer count;

		if ((count = check_args_and_read_count(args)) == null)
			return;

	}

	private static Integer check_args_and_read_count(String[] args)
	{
		Integer count = null;

		if (!check_one_arg(args))
			print_err_msg_with_example(MSG_ERR_NUM_ARG, EXAMPLE_ARGS, DETAILS);
		if ((count = check_valid_arg(args)) == null)
			print_err_msg_with_example(MSG_NO_VALID_ARG, EXAMPLE_ARGS, DETAILS);
		return count;
	}
}
