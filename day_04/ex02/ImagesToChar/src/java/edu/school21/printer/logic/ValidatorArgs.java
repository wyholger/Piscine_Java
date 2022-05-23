package edu.school21.printer.logic;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import com.diogonunes.jcolor.Attribute;

import java.util.HashMap;

public class ValidatorArgs implements IParameterValidator
{
	public static final HashMap<String, Attribute> colors_back = new HashMap<String, Attribute>()
	{
		{
			put("BLACK", Attribute.BLACK_BACK());
			put("RED", Attribute.RED_BACK());
			put("GREEN", Attribute.GREEN_BACK());
			put("YELLOW", Attribute.YELLOW_BACK());
			put("BLUE", Attribute.BLUE_BACK());
			put("MAGENTA", Attribute.MAGENTA_BACK());
			put("CYAN", Attribute.CYAN_BACK());
			put("WHITE", Attribute.WHITE_BACK());
			put("BRIGHT_BLACK", Attribute.BRIGHT_BLACK_BACK());
			put("BRIGHT_RED", Attribute.BRIGHT_RED_BACK());
			put("BRIGHT_GREEN", Attribute.BRIGHT_GREEN_BACK());
			put("BRIGHT_YELLOW", Attribute.BRIGHT_YELLOW_BACK());
			put("BRIGHT_BLUE", Attribute.BRIGHT_BLUE_BACK());
			put("BRIGHT_MAGENTA", Attribute.BRIGHT_MAGENTA_BACK());
			put("BRIGHT_CYAN", Attribute.BRIGHT_CYAN_BACK());
			put("BRIGHT_WHITE", Attribute.BRIGHT_WHITE_BACK());
		}
	};

	@Override
	public void validate(String name, String value) throws ParameterException
	{
		Attribute attribute = colors_back.get(value);
		if (attribute == null)
			throw new ParameterException("Error. Color " + value + " not found.");
	}
}
