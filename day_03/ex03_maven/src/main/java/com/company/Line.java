package com.company;

public class Line
{
	private final String	url;

	private final int		num_line;

	public Line(String url, int num_line)
	{
		this.url = url;
		this.num_line = num_line;
	}

	public String getUrl()
	{
		return url;
	}

	public int getNum_line()
	{
		return num_line;
	}
}
