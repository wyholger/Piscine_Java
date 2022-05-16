package com.company;

import java.util.ArrayList;

import static com.company.ArrayGenerator.array_generator;
import static com.company.Color.BLUE;
import static com.company.Color.YELLOW;
import static com.company.ThreadGenerator.generate_threads;

public class Calculator
{
	private final int	arr_size;
	private final int	thread_size;

	private Integer[]	array;
	private Integer		sum_without_thread;
	private Integer		sum_threads;

	private Thread[]	threads;

	private RunThread[]	run_threads;

	private ArrayList<int[]> list_index_for_threads;

	public Calculator(int arr_size, int thread_size)
	{
		this.arr_size = arr_size;
		this.thread_size = thread_size;
		this.list_index_for_threads = new ArrayList<>();
	}

	public void calculate()
	{
		array = array_generator(arr_size);
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
			sum_threads += i.getResult();
		}
		System.out.println(BLUE + "Sum by threads: " + YELLOW + sum_threads);
		setSum_threads(sum_threads);
	}

	public void setSum_threads(Integer sum_threads)
	{
		this.sum_threads = sum_threads;
	}

	public ArrayList<int[]> getList_index_for_threads()
	{
		return list_index_for_threads;
	}

	public Integer[] getArray()
	{
		return array;
	}

	public Integer getSum_threads()
	{
		return sum_threads;
	}

	public void setList_index_for_threads(ArrayList<int[]> list_index_for_threads)
	{
		this.list_index_for_threads = list_index_for_threads;
	}

	public void setArray(Integer[] array)
	{
		this.array = array;
	}

	public Integer getSum_without_thread()
	{
		return sum_without_thread;
	}

	public Thread[] getThreads()
	{
		return threads;
	}

	public RunThread[] getRun_threads()
	{
		return run_threads;
	}

	public void setSum_without_thread(Integer sum_without_thread)
	{
		this.sum_without_thread = sum_without_thread;
	}

	public void setThreads(Thread[] threads)
	{
		this.threads = threads;
	}

	public void setRun_threads(RunThread[] run_threads)
	{
		this.run_threads = run_threads;
	}
}
