package com.company;

import static com.company.Program.*;

public class RunThread implements Runnable
{
	Integer count;
	Integer start;
	Integer end;

	public RunThread(Integer count, Integer start, Integer end)
	{
		this.count = count;
		this.start = start;
		this.end = end;
	}

	@Override
	public void run()
	{
		Integer sum = 0;

		for (int i = start; i <= end; i++)
			sum += array[i];
		results[count] = sum;
	}
}
