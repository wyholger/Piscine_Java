package com.company;

import static com.company.Color.*;

public enum Type
{
	CHICKEN(BLUE + "Hen" + RESET),
	EGG(PURPLE + "Egg" + RESET),
	HUMAN(YELLOW + "Human" + RESET);
	private final String code;
	Type(String code)
	{
		this.code = code;
	}
	@Override
	public String toString()
	{
		return code;
	}
}
