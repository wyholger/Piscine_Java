package com.company;

import java.util.Arrays;

import static com.company.ArrayGenerator.*;
import static com.company.CheckerArgs.*;

public class Program
{

	public static Integer[]	results;
	public static Integer[] array;
	public static Thread[]	threads;

	public static void main(String[] args)
	{
		Integer[]	arg;
		Integer		arr_size;
		Integer		thread_size;



		arg = check_args_and_read_counts(args);
		if (arg == null)
			return;
		arr_size = arg[0];
		thread_size = arg[1];
		results = new Integer[thread_size];
		array = array_generator(arr_size);
		threads = generate_thread(thread_size, arr_size);
		starting_threads();
		joining_threads();
		System.out.println(Arrays.toString(results));
	}

	private static void joining_threads()
	{
		for (Thread thread : threads)
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
	}

	private static void starting_threads()
	{
		for (Thread thread : threads)
		{
			thread.start();
		}
	}

	private static Thread[] generate_thread(Integer thread_size, Integer arr_size)
	{
		Integer for_each_thread;
		Integer for_last_thread;
		Integer count_index_start;
		Integer count_index_end;

		Thread[] threads = new Thread[thread_size];

		if (arr_size % thread_size == 0)
		{
			for_each_thread = arr_size / thread_size;
			for_last_thread = for_each_thread;
		}
		else
		{
			for_each_thread = arr_size / thread_size;
			for_last_thread = arr_size - (for_each_thread * thread_size);
		}
		count_index_start = 0;
		count_index_end = for_each_thread;
		for (int i = 0; i < thread_size; i++)
		{
			RunThread run_thread = new RunThread(i, count_index_start, count_index_end);
			if (i != thread_size - 1)
			{
				count_index_start += for_each_thread;
				count_index_end += for_each_thread;
			}
			else
			{
				count_index_start += for_each_thread;
				count_index_end += for_last_thread;
			}
			threads[i] = new Thread(run_thread);
		}
		return threads;
	}

//	private static RunThread init_run()
//	{
//
//	}

	private static Integer[] check_args_and_read_counts(String[] args)
	{
		Integer[] count = null;

		if (!check_two_arg(args))
			print_err_msg_with_example(MSG_ERR_NUM_ARG, EXAMPLE_ARGS, DETAILS);
		if ((count = check_valid_arg(args)) == null)
			print_err_msg_with_example(MSG_NO_VALID_ARG, EXAMPLE_ARGS, DETAILS);
		return count;
	}
}
