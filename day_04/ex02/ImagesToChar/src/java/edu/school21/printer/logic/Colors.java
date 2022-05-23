package edu.school21.printer.logic;

import com.diogonunes.jcolor.AnsiFormat;

import static com.diogonunes.jcolor.Attribute.*;

public enum Colors
{
	RED(new AnsiFormat(RED_TEXT())),
	BLUE(new AnsiFormat(BLUE_TEXT())),
	GREEN(new AnsiFormat(GREEN_TEXT())),
	YELLOW(new AnsiFormat(YELLOW_TEXT())),
	MAGENTA(new AnsiFormat(MAGENTA_TEXT()));

	private final AnsiFormat format;

	Colors(AnsiFormat ansiFormat)
	{
		this.format = ansiFormat;
	}

	public String format(String str)
	{
		return format.format(str);
	}
}
