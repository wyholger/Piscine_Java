package com.company;

import java.util.Arrays;

public class ArrayGenerator
{
	public static Integer[] array_generator(Integer size, boolean flag_testing_mode)
	{
		Integer[] arr = new Integer[size];

		for (int i = 0; i < size; i++)
		{
			if (flag_testing_mode)
				arr[i] = 1;
			else
				arr[i] = (int)(Math.random() * 1000);
		}
		return arr;
	}
}
