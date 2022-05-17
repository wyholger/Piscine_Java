package com.company;

import java.util.ArrayList;

import static com.company.ReaderFile.*;

public class Loader
{
	public static final String PATH = "src/main/java/com/company/files_urls.txt";

	private ArrayList<Line> urls;

	public boolean loading()
	{
		urls = reading_from_file(PATH);
		if (urls == null)
			return false;
		return true;
	}

	public ArrayList<Line> getUrls()
	{
		return urls;
	}
}
