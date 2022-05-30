package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

class NumberWorkerTest
{

	NumberWorker number_worker;

	@BeforeEach
	void before_each_method()
	{
		number_worker = new NumberWorker();
	}

	@DisplayName("Should return true on all cases")
	@ParameterizedTest
	@ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19, 23, 199, 2887, 6221, 6551, 7177, 8017, 9013, 9923})
	void isPrimeForPrimes(int argument)
	{
		Assertions.assertTrue(number_worker.isPrime(argument));
	}


	@DisplayName("Should return false on all cases")
	@ParameterizedTest
	@ValueSource(ints = {4, 6, 8, 9, 15, 21, 77, 195, 6087, 7563, 7579, 9903})
	void isPrimeForNotPrimes(int argument)
	{
		Assertions.assertFalse(number_worker.isPrime(argument));
	}

	@DisplayName("Should throw exception on all cases")
	@ParameterizedTest
	@ValueSource(ints = {-4, 0, 1})
	void isPrimeForIncorrectNumbers(int argument)
	{
		NumberWorker.IllegalNumberException ex;

		ex = Assertions.assertThrows(NumberWorker.IllegalNumberException.class, () -> number_worker.isPrime(argument));

		Assertions.assertEquals(ex.toString(), "Error. Illegal number. A positive number greater than 1 is expected.");
	}

	@DisplayName("Should return the correct value for each case")
	@ParameterizedTest
	@CsvFileSource(resources = "/data.csv", delimiter = ',')
	void checkDigitsSum(int number, int expected)
	{
		Assertions.assertEquals(number_worker.digitsSum(number), expected);
	}

}