package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;
import java.util.Vector;

import static com.company.Color.*;
import static java.io.StreamTokenizer.TT_EOF;


public class Program
{
	public static File[] file_input;
	public static TreeSet<String> set;
	public static ArrayList<String> words_a;
	public static ArrayList<String> words_b;
	public static Vector<Integer> vector_a;
	public static Vector<Integer> vector_b;

	public static void main(String[] args)
	{
		double result;

		if (msg_no_valid_number_arg(args))
			return;
		file_input = open_files_input(args);
		init_static_vars();
		if (!read_files())
			return;
		init_vector_words_a();
		init_vector_words_b();
		result = cosine_similarity(vector_a, vector_b);
		System.out.printf("Similarity = %.2f\n", result);
	}

	public static double cosine_similarity(Vector<Integer> vector_a, Vector<Integer> vector_b)
	{
		double dot_product = 0.0;
		double norm_a = 0.0;
		double norm_b = 0.0;

		for (int i = 0; i < vector_a.size(); i++) {
			dot_product += vector_a.get(i) * vector_b.get(i);
			norm_a += Math.pow(vector_a.get(i), 2);
			norm_b += Math.pow(vector_b.get(i), 2);
		}
		return dot_product / (Math.sqrt(norm_a) * Math.sqrt(norm_b));
	}

	private static void init_vector_words_a()
	{
		int find;

		for (String s: set)
		{
			find = words_a.indexOf(s);
			if (find != -1)
				vector_a.add(Collections.frequency(words_a, words_a.get(find)));
			else
				vector_a.add(0);
		}
	}

	private static void init_vector_words_b()
	{
		int find;

		for (String s: set)
		{
			find = words_b.indexOf(s);
			if (find != -1)
				vector_b.add(Collections.frequency(words_b, words_b.get(find)));
			else
				vector_b.add(0);
		}
	}

	private static void init_static_vars()
	{
		set = new TreeSet<>();
		vector_a = new Vector<>();
		vector_b = new Vector<>();
		words_a = new ArrayList<>();
		words_b = new ArrayList<>();
	}

	private static boolean read_file(File file, ArrayList<String> file_words)
	{
		FileReader file_reader;
		StreamTokenizer stream_tokenizer;
		int	tokenizer_status = 0;
		String buffer;

		try
		{
			file_reader = new FileReader(file);
		} catch (FileNotFoundException e)
		{
			System.out.println(RED + "File " + file.toString() + " not found." + RESET);
			return false;
		}
		stream_tokenizer = new StreamTokenizer(file_reader);
		stream_tokenizer.ordinaryChars('0', '9');
		stream_tokenizer.wordChars('0', '9');
		while (true)
		{
			try
			{
				tokenizer_status = stream_tokenizer.nextToken();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			if (tokenizer_status == TT_EOF)
				break;
			buffer = stream_tokenizer.sval;
			set.add(buffer);
			file_words.add(buffer);
		}
		return true;
	}

	private static boolean read_files()
	{

		if (!read_file(file_input[0], words_a))
			return false;
		if (!read_file(file_input[1], words_b))
			return false;
		return true;
	}

	private static File[] open_files_input(String[] args)
	{
		File[] file_input = new File[2];

		file_input[0] = new File(args[0]);
		file_input[1] = new File(args[1]);
		return file_input;
	}

	private static boolean msg_no_valid_number_arg(String[] args)
	{
		if (args.length != 2)
		{
			System.out.println(RED + "Enter 2 arguments - path to file_1 and path to file_2.\n"
					+ YELLOW + "Example:" + PURPLE + " java Program inputA.txt inputB.txt" + RESET);
			return true;
		}
		return false;
	}

}
