package edu.school21.printer.logic;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class ArgParser
{
	@Parameter(names = "--white", validateWith = ValidatorArgs.class)
	private String white;
	@Parameter(names = "--black", validateWith = ValidatorArgs.class)
	private String black;

	public String getWhite()
	{
		return white;
	}

	public String getBlack()
	{
		return black;
	}
}
