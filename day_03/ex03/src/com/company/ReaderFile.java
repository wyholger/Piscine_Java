package com.company;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.company.Color.*;

public class ReaderFile
{
	private static final String MSG_ERROR_OPEN_FILE = "Error. Open file with url error.";

	public static ConcurrentLinkedQueue<Line> reading_from_file(String path)
	{
		ConcurrentLinkedQueue<Line> list = new ConcurrentLinkedQueue<>();

		Scanner scan;

		FileReader file_output_stream = opening_file(path);

		if (file_output_stream == null)
		{
			System.out.println(RED + MSG_ERROR_OPEN_FILE + RESET);
			return null;
		}
		scan = new Scanner(file_output_stream);
		while (scan.hasNextLine())
		{
			String line = scan.nextLine();
			String[] for_split = line.split(" ");
			URL url = init_url(for_split[1]);
			if (url == null)
				return null;
			list.add(new Line(url, Integer.parseInt(for_split[0])));
		}
		closing_file(file_output_stream);
		return list;
	}

	private static URL init_url(String link)
	{
		URL url;

		try
		{
			url = new URL(link);
		}
		catch (MalformedURLException e)
		{
			System.out.println("Error. Format url error.\n" + link);
			return null;
		}
		return url;
	}

	private static void closing_file(FileReader file_output_stream)
	{
		try
		{
			file_output_stream.close();
		}
		catch (IOException e)
		{
			System.out.println(YELLOW + "Warning failed to close the file. It's not critical." + RESET);
		}
	}


	private static FileReader opening_file(String path)
	{
		FileReader file_output_stream;

		try
		{
			file_output_stream = new FileReader(path);
		}
		catch (FileNotFoundException e)
		{
			return null;
		}
		return file_output_stream;
	}
}
