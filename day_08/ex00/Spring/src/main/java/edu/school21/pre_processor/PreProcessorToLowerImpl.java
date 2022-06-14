package edu.school21.pre_processor;

public class PreProcessorToLowerImpl implements PreProcessor
{
	@Override
	public String convert(String str)
	{
		return str.toLowerCase();
	}
}
