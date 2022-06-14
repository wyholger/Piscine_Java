package edu.school21.pre_processor;

public class PreProcessorToUpperImpl implements PreProcessor
{
	@Override
	public String convert(String str)
	{
		return str.toUpperCase();
	}
}
