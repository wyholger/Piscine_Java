package edu.school21.printer;

import edu.school21.render.Renderer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrinterWithDateTimeImpl implements Printer
{
	private final Renderer renderer;

	public PrinterWithDateTimeImpl(Renderer renderer)
	{
		this.renderer = renderer;
	}


	@Override
	public void setPrefix(Object object)
	{

	}

	@Override
	public void print(String str)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
		LocalDateTime time = LocalDateTime.now();
		String time_string = time.format(formatter);
		renderer.print(time_string + " " + str);
	}
}
