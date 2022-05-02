package com.company;

import java.util.Scanner;

public class Program
{
	public static Scanner scan	= new Scanner(System.in);

	public static void main(String[] args)
	{
		int num;
		int	sum = 0;

		while (true)
		{
			num = scanning(scan);
			if (num == 42)
				break;
			sum += prime_number(sum_digit(num));
		}
		System.out.println("Count of coffee-request - " + sum);
	}

	public static int scanning(Scanner scan)
	{
		return scan.nextInt();
	}

	public static int prime_number(final int num)
	{
		boolean		status = false;
		int			step = 0;

		if (num % 2 == 0)
			return 0;
		for (int i = 2; i * i <= num; i++)
		{
			if (num % i == 0)
			{
				status = true;
				break;
			}
		}
		if (status)
			return 0;
		else
			return 1;
	}

	public static int sum_digit(int num)
	{
		int num_tmp;
		int dec = 1000000000;
		int sum = 0;
		int sum_tmp;

		if (num == 0)
			return 0;
		if (num < 0)
			num *= -1;
		num_tmp = num;
		while (num / dec == 0)
			dec /= 10;
		while (dec != 0)
		{
			sum_tmp = num_tmp / dec;
			sum += sum_tmp;
			num_tmp = num_tmp - (sum_tmp * dec);
			dec /= 10;
		}
		return sum;
	}
}
