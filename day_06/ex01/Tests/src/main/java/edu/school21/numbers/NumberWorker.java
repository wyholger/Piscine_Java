package edu.school21.numbers;

public class NumberWorker
{

	public int digitsSum(int number)
	{
		int num_tmp;
		int dec = 1000000000;
		int sum = 0;
		int sum_tmp;

		if (number == 0)
			return 0;
		if (number < 0)
			number *= -1;
		num_tmp = number;
		while (number / dec == 0)
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

	public boolean isPrime(int number)
	{
		boolean		status = true;

		if (number <= 1)
			throw new IllegalNumberException();
		if (number == 2)
			return true;
		if (number % 2 == 0)
			return false;
		for (int i = 2; i * i <= number; i++)
		{
			if (number % i == 0)
			{
				status = false;
				break;
			}
		}
		return status;
	}

	public static class IllegalNumberException extends RuntimeException
	{
		@Override
		public String toString()
		{
			return "Error. Illegal number. A positive number greater than 1 is expected.";
		}
	}
}
