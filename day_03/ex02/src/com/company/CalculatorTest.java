package com.company;

import org.junit.Assert;
import org.junit.Test;

import static com.company.Color.*;

public class CalculatorTest
{

//	@Test
//	public void calculate_test_13_13()
//	{
//		ArrayList<int[]> expected = new ArrayList<>();
//		ArrayList<int[]> actual;
//		Calculator calculator = new Calculator(13, 13);
//		calculator.calculate();
//		actual = calculator.getList_index_for_threads();
//		for (int i = 0; i < 13; i++)
//		{
//			expected.add(new int[]{i,i,i});
//		}
//		for (int i = 0; i < 13; i++)
//		{
//			int[] expected_arr = expected.get(i);
//			int[] actual_arr = actual.get(i);
//			Assert.assertArrayEquals(actual_arr, expected_arr);
//		}
//		Assert.assertEquals(calculator.getSum_without_thread(), calculator.getSum_threads());
//	}
//
//	@Test
//	public void calculate_test_13_12()
//	{
//		Integer number = 13;
//		ArrayList<int[]> expected = new ArrayList<>();
//		ArrayList<int[]> actual;
//		Calculator calculator = new Calculator(number, 12);
//		calculator.calculate();
//		actual = calculator.getList_index_for_threads();
//		for (int i = 0; i < 12; i++)
//		{
//			if (i == 11)
//				expected.add(new int[]{i,i,i+1});
//			else
//				expected.add(new int[]{i,i,i});
//		}
//
//		for (int i = 0; i < 12; i++)
//		{
//			int[] expected_arr = expected.get(i);
//			int[] actual_arr = actual.get(i);
//			Assert.assertArrayEquals(actual_arr, expected_arr);
//		}
//		Assert.assertEquals(calculator.getSum_without_thread(), calculator.getSum_threads());
//		Assert.assertEquals(calculator.getSum_threads(), number);
//	}

	@Test
	public void calculator_testing_normal()
	{
		Integer arr_size = 13;

		for (int i = 1; i <= arr_size; i++)
		{
			System.out.println(PURPLE + "Testing for arr_size = " + arr_size + " thread_size = " + i + RESET);
			Calculator calculator = new Calculator(arr_size, i, true);
			calculator.calculate();
			Assert.assertEquals(calculator.get_sum_without_thread(), calculator.get_sum_threads());
			Assert.assertEquals(calculator.get_sum_threads(), arr_size);
		}
	}
}