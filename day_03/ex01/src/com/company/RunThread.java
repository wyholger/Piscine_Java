package com.company;

public class RunThread implements Runnable
{
	private static final Printer printer = new Printer();
	Integer count;
	Type type;

	public RunThread(Integer count, Type type)
	{
		this.count = count;
		this.type = type;
	}

	@Override
	public void run()
	{
		for (int i = 0; i < count; i++)
		{
			printer.printing(type);
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
