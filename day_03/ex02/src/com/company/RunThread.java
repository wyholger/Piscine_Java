package com.company;

import static com.company.Calculator.*;
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
			sum += calculator.getArray()[i];
		result = sum;
	}

	public void print_result()
	{
		System.out.println(BLUE + "Thread " + YELLOW + (this.getCount() + 1)
				+ BLUE + ": from " + YELLOW + this.getStart()
				+ BLUE + " to " + YELLOW + this.getEnd()
				+ BLUE + " sum is " + YELLOW + this.getResult());
	}

	public Integer getCount()
	{
		return count;
	}

	public Integer getStart()
	{
		return start;
	}

	public Integer getEnd()
	{
		return end;
	}

	public Integer getResult()
	{
		return result;
	}
}
