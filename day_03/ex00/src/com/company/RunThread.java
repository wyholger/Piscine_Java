package com.company;

import java.util.Random;

public class RunThread implements Runnable
{
	Integer count;
	String msg;

	public RunThread(Integer count, String msg)
	{
		this.count = count;
		this.msg = msg;
	}

	@Override
	public void run()
	{
		Random random = new Random();

		int for_random = random.nextInt(50);

		sleeping(for_random + 1);
		for (int i = 0; i < count; i++)
		{
			System.out.println(msg);
			sleeping(10);
		}
	}

	private static void sleeping(Integer mill_sec)
	{
		if (mill_sec < 0)
			return;
		try
		{
			Thread.sleep(mill_sec);
		}
		catch (InterruptedException e)
		{
			System.out.println("Interrupt");
		}
	}
}
