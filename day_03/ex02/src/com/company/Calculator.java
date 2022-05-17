package com.company;

import static com.company.ArrayGenerator.array_generator;
import static com.company.Color.BLUE;
import static com.company.Color.YELLOW;
import static com.company.ThreadGenerator.generate_threads;

public class Calculator
{
	private final int		arr_size;
	private final int		thread_size;

	private final boolean	flag_testing_mode;

	private Integer[]		array;
	private Integer			sum_without_thread;
	private Integer			sum_threads;

	private Thread[]		threads;

	private RunThread[]		run_threads;


	public Calculator(int arr_size, int thread_size, boolean flag_testing_mode)
	{
		this.arr_size = arr_size;
		this.thread_size = thread_size;
		this.flag_testing_mode = flag_testing_mode;
	}

	public void calculate()
	{
		array = array_generator(arr_size, flag_testing_mode);
		threads = generate_threads(thread_size, arr_size, this);
		sum_without_thread = calculate_sum_without_thread();
		starting_threads();
		joining_threads();
		printing_result();
	}

	private void starting_threads()
	{
		for (Thread thread : threads)
		{
			thread.start();
		}
	}

	private void joining_threads()
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

	private Integer calculate_sum_without_thread()
	{
		Integer result = 0;

		for (Integer i: array)
		{
			result += i;
		}
		return result;
	}

	private void printing_result()
	{
		Integer sum_threads = 0;

		System.out.println(BLUE + "Sum: " + YELLOW + sum_without_thread);
		for (RunThread i: run_threads)
		{
			i.print_result();
			sum_threads += i.get_result();
		}
		System.out.println(BLUE + "Sum by threads: " + YELLOW + sum_threads);
		set_sum_threads(sum_threads);
	}

	public Integer[] get_array()
	{
		return array;
	}

	public Integer get_sum_threads()
	{
		return sum_threads;
	}

	public Integer get_sum_without_thread()
	{
		return sum_without_thread;
	}

	public void set_run_threads(RunThread[] run_threads)
	{
		this.run_threads = run_threads;
	}

	public void set_sum_threads(Integer sum_threads)
	{
		this.sum_threads = sum_threads;
	}
}
