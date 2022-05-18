package com.company;

import static com.company.CheckerArgs.*;
import static com.company.Color.*;

public class Program
{
	private static Integer count;

	private static final String CHICKEN_MSG = BLUE + "Hen" + RESET;
	private static final String EGG_MSG = PURPLE + "Egg" + RESET;
	private static final String HUMAN_MSG = YELLOW + "Human" + RESET;

	public static void main(String[] args)
	{
		Integer	count;

		Thread	thread_chicken;
		Thread	thread_egg;
		Thread	thread_human;


		if ((count = check_args_and_read_count(args)) == null)
			return;
		thread_chicken = init_thread(count, CHICKEN_MSG);
		thread_egg = init_thread(count, EGG_MSG);
		thread_human = init_thread(count, HUMAN_MSG);

		thread_chicken.start();
		thread_egg.start();

		joining_thread(thread_chicken);
		joining_thread(thread_egg);

		thread_human.start();

		joining_thread(thread_human);
	}

	private static Thread init_thread(Integer count, String msg)
	{
		RunThread run_thread;

		Thread thread;

		run_thread = init_run(count, msg);
		thread = new Thread(run_thread);
		return thread;
	}

	private static void joining_thread(Thread thread)
	{
		try
		{
			thread.join();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	private static RunThread init_run(Integer count, String msg)
	{
		return new RunThread(count, msg);
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
