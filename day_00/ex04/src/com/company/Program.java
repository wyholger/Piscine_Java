package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Program
{
	public static char[] chars = new char[65537];

	public static void main(String[] args)
	{
		char[][]	result;
		float[]		result_coefficient;

		init_arr(scanning_sting());
		result = init_result_arr();
		result_coefficient = init_result_coefficient(result);
		print_result_diagram(result, result_coefficient);
	}

	public static void print_result_diagram(char[][] result, float[] result_coefficient)
	{
		float		percent = 0;

		for (int k = 0; k < 11; k++)
		{
			System.out.print(" ");
			for (int i = 0; i < 10; i++)
			{
				if (result_coefficient[i] == 0)
					break;
				if (result_coefficient[i] + percent >= 1)
				{
					if (result_coefficient[i] > 10)
						System.out.print(" # ");
					if (result_coefficient[i] < 10)
					{
						if (result[i][0] <= 9)
							System.out.print(" ");
						System.out.print((int)result[i][0] + " ");
						result_coefficient[i] += 10;
					}
				}
			}
			percent += 0.1;
			System.out.println();
		}
		System.out.print(" ");
		for (int i = 0; i < 10; i++)
		{
			if (result[i][1] == 0)
				break;
			System.out.print(" " + result[i][1] + " ");
		}
		System.out.println();
	}

	public static float[] init_result_coefficient(char[][] result)
	{
		int	max;
		float[]	result_coefficient = new float[10];

		max = result[0][0];
		for (int i = 0; i < 10; i++)
		{
			if (result[i][0] == 0)
				break;
			result_coefficient[i] =  (float)result[i][0] / (float)max;
		}
		return result_coefficient;
	}

	public static char[][] init_result_arr()
	{
		char[][] result = new char[10][];

		for (int i = 0; i < 10; i++)
			result[i] = get_max();
		return result;
	}

	public static String scanning_sting()
	{
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}

	public static void init_arr(String str)
	{
		for (int i = 0; i < str.length(); i++)
			chars[str.charAt(i)] += 1;
	}

	public static char[] get_max()
	{
		char	max 		= 0;
		char	charackter 	= 0;
		char[] 	result 		= new char[2];

		for (char i = 0; i < 65535; i++)
		{
			if (chars[i] > max)
			{
				max = chars[i];
				charackter = i;
			}
		}
		result[0] = max;
		result[1] = charackter;
		chars[charackter] = 0;
		return result;
	}
}
