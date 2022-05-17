package com.company;

import static com.company.CheckerArgs.*;
import static com.company.Color.*;

public class Program
{
	public static void main(String[] args)
	{
		Integer[]	arg;
		Integer		arr_size;
		Integer		thread_size;

		Calculator	calculator;

		arg = check_args_and_read_counts(args);
		if (arg == null)
			return;
		arr_size = arg[0];
		thread_size = arg[1];
		System.out.println(PURPLE + "\nTesting mode with each element arr == 1\n" + RESET);
		calculator = new Calculator(arr_size, thread_size, true);
		calculator.calculate();
		System.out.println(PURPLE + "\nWork mode with each element arr is random number from 0 to 1000\n" + RESET);
		calculator = new Calculator(arr_size, thread_size, false);
		calculator.calculate();
	}

	private static Integer[] check_args_and_read_counts(String[] args)
	{
		Integer[] count;

		if (!check_two_arg(args))
			print_err_msg_with_example(MSG_ERR_NUM_ARG, EXAMPLE_ARGS, DETAILS);
		if ((count = check_valid_arg(args)) == null)
			print_err_msg_with_example(MSG_NO_VALID_ARG, EXAMPLE_ARGS, DETAILS);
		return count;
	}
}
