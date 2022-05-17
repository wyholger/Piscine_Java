package com.company;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;


class ProgramTest extends Program
{
	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();

	@Test
	public void main_arguments_test()
	{
		String[] args = new String[]{"param1", "param2"};
		main(args);
	}
}