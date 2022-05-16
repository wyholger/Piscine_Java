package com.company;

import static com.company.Calculator.*;

public class ThreadGenerator
{
	public static Thread[] generate_threads(Integer thread_size, Integer arr_size, Calculator calculator)
	{
		Thread[] threads = new Thread[thread_size];
		RunThread[] run_threads;

		int chunk_each_thread;
		int index_start;
		int index_end;

		run_threads = new RunThread[thread_size];
		chunk_each_thread = generate_chunk_each_thread(thread_size, arr_size);
		index_start = 0;
		if (chunk_each_thread == 1)
			index_end = index_start;
		else
			index_end = chunk_each_thread - 1;
		for (int i = 0; i < thread_size; i++)
		{
			RunThread run_thread = new RunThread(i, index_start, index_end, calculator);
			calculator.getList_index_for_threads().add(new int[]{i, index_start, index_end});
			run_threads[i] = run_thread;
			index_start = recalculation_index_start(chunk_each_thread, index_start);
			index_end = recalculation_index_end(i, thread_size, chunk_each_thread, index_end, arr_size);
			threads[i] = new Thread(run_thread);
		}
		calculator.setRun_threads(run_threads);
		return threads;
	}

	private static int recalculation_index_start(int chunk_each_thread, int index_start)
	{
		if (chunk_each_thread == 1)
			index_start++;
		else
			index_start += chunk_each_thread;
		return index_start;
	}

	private static int recalculation_index_end(int i, Integer thread_size, int chunk_each_thread, int index_end, Integer arr_size)
	{
		if (chunk_each_thread == 1 && i != thread_size - 2)
		{
			index_end++;
			return index_end;
		}
		if (i != thread_size - 2)
			index_end = Math.min(index_end + chunk_each_thread, arr_size - 1);
		else
			index_end = arr_size - 1;
		return index_end;
	}

	private static int generate_chunk_each_thread(Integer thread_size, Integer arr_size)
	{
		int chunk_each_thread;

		if (thread_size == 1)
			chunk_each_thread = arr_size;
		else
			chunk_each_thread = arr_size / thread_size;
		return chunk_each_thread;
	}
}
