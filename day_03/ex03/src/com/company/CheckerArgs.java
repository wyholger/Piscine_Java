package com.company;

import static com.company.Color.*;

public class CheckerArgs
{
	public static String MSG_ERR_NUM_ARG = "Error. One argument required.";
	public static String EXAMPLE_ARGS = "Example: java Program --threadsCount=3";
	public static String DETAILS = "Arguments == --threadsCount=<K>.\n<K> must be a positive integer more than 0.";
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

	public static Integer check_valid_arg(String[] args)
	{
		String[]	str;

		int val = 0;

		str = args[0].split("=");
		if (str.length != 2)
			return null;
		if (!str[0].equals("--threadsCount"))
			return null;
		try
		{
			val = Integer.parseInt(str[1]);
		}
		catch (NumberFormatException e)
		{
			return null;
		}
		if (val <= 0)
			return null;
		return val;
	}

	public static void print_err_msg_with_example(String str_1, String str_2, String str_3)
	{
		System.out.println(RED + str_1 + '\n' + YELLOW + str_2 + '\n' + PURPLE + str_3 + RESET);
	}

}
