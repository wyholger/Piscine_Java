package com.company;

import static com.company.Color.*;

public class RunThread implements Runnable
{
	Integer count;
	Integer start;
	Integer end;
	Integer result;
	Calculator calculator;

	public RunThread(Integer count, Integer start, Integer end, Calculator calculator)
	{
		this.count = count;
		this.start = start;
		this.end = end;
		this.calculator = calculator;
	}

	@Override
	public void run()
	{
		Integer sum = 0;

		for (int i = start; i <= end; i++)
			sum += calculator.get_array()[i];
		result = sum;
	}

	public void print_result()
	{
		System.out.println(BLUE + "Thread " + YELLOW + (this.get_count() + 1)
				+ BLUE + ": from " + YELLOW + this.get_start()
				+ BLUE + " to " + YELLOW + this.get_end()
				+ BLUE + " sum is " + YELLOW + this.get_result());
	}

	public Integer get_count()
	{
		return count;
	}

	public Integer get_start()
	{
		return start;
	}

	public Integer get_end()
	{
		return end;
	}

	public Integer get_result()
	{
		return result;
	}
}
