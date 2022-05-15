package com.company;

import java.util.Arrays;

public class ArrayGenerator
{
	public static Integer[] array_generator(Integer size)
	{
		Integer[] arr = new Integer[size];

		for (int i = 0; i < size; i++)
		{
			arr[i] = (int)(Math.random() * 1000);
		}
		return arr;
	}
}
