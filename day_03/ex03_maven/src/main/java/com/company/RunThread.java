package com.company;

import java.io.*;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.Channels;
import java.util.concurrent.ConcurrentLinkedQueue;


import static com.company.Color.*;

public class RunThread implements Runnable
{

	private final ConcurrentLinkedQueue<Line>	queue;
	private final int							num_thread;

	public RunThread(ConcurrentLinkedQueue<Line> queue, int num_thread)
	{
		this.queue = queue;
		this.num_thread = num_thread;
	}

	@Override
	public void run()
	{
		while (true)
		{
			Line line = queue.poll();
			if (line == null)
				return;
			System.out.println(BLUE + "Thread-" + YELLOW + num_thread + BLUE + " start download file number " + YELLOW + line.getNum_line() + RESET);
			ReadableByteChannel rbc = init_bite_channel(line.getUrl(), line);
			if (rbc == null)
				return;
			String file_name = init_file_name(line.getUrl());
			file_name = "output_files/" + file_name;
			FileOutputStream fos = init_file_output_stream(file_name, line);
			if (fos == null)
				return;
			if (!downloading_file(rbc, fos, line))
				return;
			System.out.println(GREEN + "Thread-" + YELLOW + num_thread + GREEN + " finish download file number " + YELLOW + line.getNum_line() + RESET);
		}
	}

	private boolean downloading_file(ReadableByteChannel rbc, FileOutputStream fos, Line line)
	{
		try
		{
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		} catch (IOException e)
		{
			System.out.println(RED + "Thread-" + YELLOW + num_thread + RED + " can't download file number " + YELLOW + line.getNum_line() + RESET);
			return false;
		}
		return true;
	}

	private FileOutputStream init_file_output_stream(String file_name, Line line)
	{
		FileOutputStream fos;

		try
		{
			fos = new FileOutputStream(file_name);
		} catch (FileNotFoundException e)
		{
			System.out.println(RED + "Thread-" + YELLOW + num_thread + RED + " can't download file number " + YELLOW + line.getNum_line() + RESET);
			return null;
		}
		return fos;
	}

	private ReadableByteChannel init_bite_channel(URL url, Line line)
	{
		ReadableByteChannel rbc;
		try
		{
			rbc = Channels.newChannel(url.openStream());
		}
		catch (IOException e)
		{
			System.out.println(RED + "Thread-" + YELLOW + num_thread + RED + " can't download file number " + YELLOW + line.getNum_line() + RESET);
			return null;
		}
		return rbc;
	}

	private String init_file_name(URL url)
	{
		String[] for_split = url.toString().split("/");
		return for_split[for_split.length - 1];
	}
}
