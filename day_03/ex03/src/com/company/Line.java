package com.company;

import java.net.URL;

public class Line
{
	private final URL url;

	private final int		num_line;

	public Line(URL url, int num_line)
	{
		this.url = url;
		this.num_line = num_line;
	}

	public URL getUrl()
	{
		return url;
	}

	public int getNum_line()
	{
		return num_line;
	}
}
