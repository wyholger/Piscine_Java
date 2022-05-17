package com.company;

import static com.company.Color.*;

public class CheckerArgs
{
	public static String MSG_ERR_NUM_ARG = "Error. Two argument required.";
	public static String EXAMPLE_ARGS = "Example: java Program --arraySize=13 --threadsCount=3";
	public static String DETAILS = "Arguments == --arraySize=<N> --threadsCount=<K>.\n<N> must be a positive integer more than 0. Not exceeding 2000000.\n<K> must be a positive integer more than 0. Not exceeding <N>.";
	public static String MSG_NO_VALID_ARG = "Error. No valid arg.";

	CheckerArgs(String example)
	{
		EXAMPLE_ARGS = example;
	}

	public static boolean check_one_arg(String[] args)
	{
		return args.length == 1;
	}

	public static boolean check_two_arg(String[] args)
	{
		return args.length == 2;
	}

	public static Integer[] check_valid_arg(String[] args)
	{
		String[]	str_first;
		String[]	str_second;

		Integer[]	val = new Integer[2];

		str_first = args[0].split("=");
		str_second = args[1].split("=");
		if (str_first.length != 2 || str_second.length != 2)
			return null;
		if (!str_first[0].equals("--arraySize") || !str_second[0].equals("--threadsCount"))
			return null;
		try
		{
			val[0] = Integer.parseInt(str_first[1]);
			val[1] = Integer.parseInt(str_second[1]);
		}
		catch (NumberFormatException e)
		{
			return null;
		}
		if (val[0] <= 0 || val[1] <= 0 || val[1] > val[0])
			return null;
		return val;
	}

	public static void print_err_msg_with_example(String str_1, String str_2, String str_3)
	{
		System.out.println(RED + str_1 + '\n' + YELLOW + str_2 + '\n' + PURPLE + str_3 + RESET);
	}

}
