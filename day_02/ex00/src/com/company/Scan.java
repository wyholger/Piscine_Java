package com.company;

import java.util.Scanner;

public class Scan
{
	private static Scanner scan;

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
}
