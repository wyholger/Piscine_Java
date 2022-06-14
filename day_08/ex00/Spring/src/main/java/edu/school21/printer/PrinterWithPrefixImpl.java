package edu.school21.printer;

import edu.school21.render.Renderer;

public class PrinterWithPrefixImpl implements Printer
{

	private final Renderer renderer;
	private String prefix;

	public PrinterWithPrefixImpl(Renderer renderer)
	{
		this.renderer = renderer;
	}


	@Override
	public void setPrefix(Object object)
	{
		if (object instanceof String)
			prefix = (String) object;
		else
		{
			System.err.println("Error. Object " + object.toString() + " not cast to String.");
			prefix = "";
		}
	}

	@Override
	public void print(String str)
	{
		renderer.print(prefix + " " + str);
	}
}
