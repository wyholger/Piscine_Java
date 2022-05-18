package com.company;

import java.util.concurrent.ConcurrentLinkedQueue;

import static com.company.ReaderFile.reading_from_file;

public class Loader
{
	public static final String 			PATH =	"input_file/files_urls.txt";

	private ConcurrentLinkedQueue<Line> urls;

	private Thread[] 					threads;

	private final Integer 				thread_count;

	public Loader(int thread_count)
	{
		this.thread_count = thread_count;
	}

	public boolean loading()
	{
		urls = reading_from_file(PATH);
		if (urls == null)
			return false;
		threads = init_threads();
		run_threads();
		join_threads();
		return true;
	}

	private void join_threads()
	{
		for (int i = 0; i < thread_count; i++)
			join_one_thread(i);
	}

	private void join_one_thread(int i)
	{
		try
		{
			threads[i].join();
		} catch (InterruptedException e)
		{
			System.out.println("Error. Join thread failed.");
		}
	}

	private void run_threads()
	{
		for (int i = 0; i < thread_count; i++)
			threads[i].start();
	}

	private Thread[] init_threads()
	{
		Thread[] threads = new Thread[thread_count];

		for (int i = 0; i < thread_count; i++)
		{
			RunThread run = new RunThread(urls, i + 1);
			threads[i] = new Thread(run);
		}
		return threads;
	}

	public ConcurrentLinkedQueue<Line> getUrls()
	{
		return urls;
	}
}
