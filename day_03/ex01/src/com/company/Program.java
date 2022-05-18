package com.company;

import static com.company.CheckerArgs.*;
import static com.company.Type.*;

public class Program
{
	public static void main(String[] args)
	{
		Integer	count;

		Thread	thread_chicken;
		Thread	thread_egg;

		if ((count = check_args_and_read_count(args)) == null)
			return;
		thread_chicken = init_thread(count, CHICKEN);
		thread_egg = init_thread(count, EGG);

		thread_chicken.start();
		thread_egg.start();

		joining_thread(thread_chicken);
		joining_thread(thread_egg);
	}

	private static Thread init_thread(Integer count, Type type)
	{
		RunThread run_thread;

		Thread thread;

		run_thread = init_run(count, type);
		thread = new Thread(run_thread);
		return thread;
	}

	private static void joining_thread(Thread thread)
	{
		try
		{
			thread.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	private static RunThread init_run(Integer count, Type type)
	{
		return new RunThread(count, type);
	}

	private static Integer check_args_and_read_count(String[] args)
	{
		Integer count = null;

		if (!check_one_arg(args))
			print_err_msg_with_example(MSG_ERR_NUM_ARG, EXAMPLE_ARGS, DETAILS);
		if ((count = check_valid_arg(args)) == null)
			print_err_msg_with_example(MSG_NO_VALID_ARG, EXAMPLE_ARGS, DETAILS);
		return count;
	}
}
