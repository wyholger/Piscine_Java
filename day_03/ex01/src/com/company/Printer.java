package com.company;

import static com.company.Type.*;

public class Printer
{
	private int flag = 1;

	public synchronized void printing(Type type)
	{
		if (type.equals(CHICKEN))
		{
			while (flag == 1)
				waiting();
			flag = 1;
		}
		else
		{
			while (flag == 0)
				waiting();
			flag = 0;
		}
		System.out.println(type);
		notify();
	}

	private void waiting()
	{
		try
		{
			wait();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
