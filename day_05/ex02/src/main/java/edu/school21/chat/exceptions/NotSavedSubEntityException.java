package edu.school21.chat.exceptions;

public class NotSavedSubEntityException extends RuntimeException
{
	@Override
	public String toString()
	{
		return "No such entry exception!";
	}
}
