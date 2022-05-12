package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Program
{

	public static Map<String, String> map;
	public static List<String> list;

    public static void main(String[] args) throws IOException
	{
		read_signature();
		main_cycle();
		record_result();
    }

	private static void record_result() throws IOException
	{
		FileOutputStream file_output_stream = new FileOutputStream("result.txt", false);
		byte[] bytes;

		for (String s : list)
		{
			bytes = s.getBytes(StandardCharsets.UTF_8);
			file_output_stream.write(bytes);
			file_output_stream.write('\n');
		}
		file_output_stream.close();
	}

	private static void read_signature() throws IOException
	{
		map = new HashMap<>();
		FileInputStream file_input_stream = new FileInputStream("src/com/company/signatures.txt");
		String k = "";
		String v = "";
		int	bite;
		boolean flag_read_now_key_or_value = false;

		while (true)
		{
			if (file_input_stream.available() > 0)
			{
				bite = file_input_stream.read();
				if (!flag_read_now_key_or_value)
				{
					if (bite != '\n' && bite != ',')
						v += (char)bite;
				}
				else
				{
					if (bite != '\n' && bite != ',')
						k += (char)bite;
				}
				if (bite == ',')
				{
					flag_read_now_key_or_value = true;
					continue;
				}
				if (bite == '\n')
				{
					map.put(k, v);
					k = "";
					v = "";
					flag_read_now_key_or_value = false;
				}
			}
			else
				break;
		}
		if (flag_read_now_key_or_value)
			map.put(k, v);
		file_input_stream.close();
	}

	private static void main_cycle()
	{
		list = new LinkedList<>();
		String file_path;
		Scan scan = new Scan();
		String result;

		while (true)
		{
			file_path = scan.scan_word();
			if (file_path.equals("42"))
			{
				System.out.println("Bye-Bye");
				break;
			}
			try
			{
				result = read_file(file_path);
				if (result == null)
					System.out.println("UNDEFINED");
				else
				{
					System.out.println("PROCESSED");
					list.add(result);
				}
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
		}
	}

	private static String read_file(String file_path) throws FileNotFoundException
	{
		FileInputStream file_input_stream;
		try
		{
			file_input_stream = new FileInputStream(file_path);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found.");
			return null;
		}
		int i = 0;
		int	count = 0;
		String result = "";
		String find = null;

		while (true)
		{
			try
			{
				if ((i = file_input_stream.read()) == -1 || count >= 12)
					break;
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			result += String.format(" %02X", i);
			find = map.get(result);
			if (find != null)
				break;
			count++;
		}
		return find;
	}
}
