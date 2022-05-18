package com.company;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.company.Color.*;
import static com.company.ReaderFile.reading_from_file;
import static org.junit.Assert.*;

public class ProgramTest extends Program
{
	public static final String PATH = "input_file/files_urls.txt";

	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();

	@Test
	public void main_arguments_test()
	{
		System.out.println("\nTesting not valid arguments in main(args).\n");
		String[] args = new String[0];
		exit.expectSystemExitWithStatus(1);
		main(args);
		args = new String[]{"param1", "param2"};
		exit.expectSystemExitWithStatus(1);
		main(args);
		args = new String[]{"param1"};
		exit.expectSystemExitWithStatus(1);
		main(args);
		args = new String[]{"param=1"};
		exit.expectSystemExitWithStatus(1);
		main(args);
		System.out.println(GREEN + "\nSUCCESS.\n" + RESET);
	}

	@Test
	public void reader_file_test()
	{
		ConcurrentLinkedQueue<Line> urls;

		System.out.println("\nTesting opening file.\n");
		urls = reading_from_file("failed_path.txt");
		assertNull(urls);
		urls = reading_from_file(PATH);
		assertNotNull(urls);
		System.out.println(GREEN + "\nSUCCESS.\n" + RESET);
	}



}