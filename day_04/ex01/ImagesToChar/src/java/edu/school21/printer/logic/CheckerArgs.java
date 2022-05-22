package edu.school21.printer.logic;

import static edu.school21.printer.logic.ColorMy.*;

public class CheckerArgs
{
	public static String MSG_ERR_NUM_ARG = "Error. Two argument required.";
	public static String EXAMPLE_ARGS = "Example: java Program . 0";
	public static String DETAILS = "Arguments == <character is used for white color> <character is used for black color>";
	public static String MSG_NO_VALID_ARG = "Error. No valid arg.";

	public static boolean check_one_arg(String[] args)
	{
		return args.length == 1;
	}

	public static boolean check_two_arg(String[] args)
	{
		return args.length == 2;
	}

	public static boolean check_three_arg(String[] args)
	{
		return args.length == 3;
	}

	public static boolean check_valid_arg(String[] args)
	{
		return args[0].length() == 1 && args[1].length() == 1;
	}

	public static void print_err_msg_with_example(String str_1, String str_2, String str_3)
	{
		System.out.println(RED + str_1 + '\n' + YELLOW + str_2 + '\n' + PURPLE + str_3 + RESET);
	}
}
