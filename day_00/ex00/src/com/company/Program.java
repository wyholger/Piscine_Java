package com.company;

public class Program 
{

	public static void main(String[] args) 
	{
		int number = 479598;
		int num_tmp;
		int dec = 1000000000;
		int sum = 0;
		int sum_tmp;

		if (number == 0)
		{
			System.out.println(0);
			return;
		}
		if (number < 0)
			number *= -1;
		num_tmp = number;
		while (number / dec == 0)
		{
			dec /= 10;
		}
		while (dec != 0)
		{
			sum_tmp = num_tmp / dec;
			sum += sum_tmp;
			num_tmp = num_tmp - (sum_tmp * dec);
			dec /= 10;
		}
		System.out.println(sum);
	}
}
