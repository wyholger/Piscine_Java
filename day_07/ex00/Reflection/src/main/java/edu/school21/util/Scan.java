package edu.school21.util;

import java.util.Scanner;

public class Scan
{
	private final Scanner scan;

	public Scan()
	{
		scan = new Scanner(System.in);
	}

	public String scan_word()
	{
		String str;
		str = scan.next();
		return str;
	}

	public String scan_line()
	{
		String str;
		str = scan.nextLine();
		return str;
	}
}
