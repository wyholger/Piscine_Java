package com.company;

import java.util.Scanner;

public class Program
{

	public static void main(String[] args)
	{
		long all_gr;

		all_gr = scanning_cycle();
		print_result_diagram(all_gr);
	}

	public static void print_result_diagram(long all_gr)
	{
		long	dec = 1000000000000000000L;
		int		i	= 1;
		long	result;

		result = all_gr / dec;
		print_one_unit_on_diagram(result, i);
		while (true)
		{
			i++;
			all_gr -= result * dec;
			dec /= 10;
			result = all_gr / dec;
			if (result == 0)
				break;
			print_one_unit_on_diagram(result, i);
		}
	}

	public static void print_one_unit_on_diagram(long result, int week)
	{
		System.out.print("Week " + week + " ");
		while (result-- > 0)
			System.out.print("=");
		System.out.println(">");
	}

	public static long scanning_cycle()
	{
		int		i 		= 1;
		long	all_gr	= 0;
		Scanner scan	= new Scanner(System.in);
		int		week;
		String	buf;

		while (i < 19)
		{
			buf = scan.next();
			if (buf.equals("42"))
				break;
			week = scan.nextInt();
			if (week != i)
				error_and_exit();
			all_gr = get_update_all_gr(week, get_min_grade(scan), all_gr);
			i++;
		}
		return all_gr;
	}

	public static void error_and_exit()
	{
		System.err.println("Illegal Argument");
		System.exit(-1);
	}

	public static long get_update_all_gr(int week, int min_grade, long all_gr)
	{
		long	dec = 1000000000000000000L;

		while (--week > 0)
			dec /= 10;
		all_gr += ((long)dec * min_grade);
		return all_gr;
	}

	public static int get_min_grade(Scanner scan)
	{
		int		grade;
		int		min;

		min = 10;
		for (int k = 0; k < 5; k++)
		{
			grade = scan.nextInt();
			if (grade < min)
				min = grade;
		}
		return min;
	}
}
