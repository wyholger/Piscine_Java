package edu.school21.printer.app;

import static edu.school21.printer.logic.CheckerArgs.*;
import static edu.school21.printer.logic.ImageToChar.image_bmp_to_char;
import static edu.school21.printer.logic.ColorMy.*;


public class Program
{
	public static void main(String[] args)
	{
		int[][] map;

		if (!check_args(args))
			return;
		if ((map = image_bmp_to_char(args[2], args[1].charAt(0), args[0].charAt(0))) == null)
			return;
		print_img(map, args[1].charAt(0));
	}

	private static void print_img(int[][] map, char black)
	{
		for (int[] ints : map) {
			for (int anInt : ints) {
				if ((char) anInt == black)
					System.out.print(YELLOW + "" + (char) anInt + RESET);
				else
					System.out.print(BLUE + "" + (char) anInt + RESET);
			}
			System.out.println();
		}
	}

	private static boolean check_args(String[] args)
	{
		if (!check_three_arg(args))
		{
			print_err_msg_with_example(MSG_ERR_NUM_ARG, EXAMPLE_ARGS, DETAILS);
			return false;
		}
		if (!check_valid_arg(args))
		{
			print_err_msg_with_example(MSG_NO_VALID_ARG, EXAMPLE_ARGS, DETAILS);
			return false;
		}
		return true;
	}
}
