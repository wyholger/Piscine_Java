## Exercise 00 – First Tests

Now you need to implement NumberWorker class that contains the following functionality:

```
public boolean isPrime(int number) {
  ...
}
```

This method determines if a number is prime and returns true/false for all natural (positive integer) numbers. For negative numbers, as well as 0 and 1, the program shall throw an unchecked exception. IllegalNumberException.

```
public int digitsSum(int number) {
  ...
}
```

This method returns the sum of digits of a source number.

We also need to create NumberWorkerTest class that implements the module testing logic. Methods of NumberWorkerTest class shall check the correct operation of NumberWorker methods for various input data:

1. isPrimeForPrimes method to check isPrime using prime numbers (at least three)
2. isPrimeForNotPrimes method to check isPrime using composite numbers (at least three)
3. isPrimeForIncorrectNumbers method to check isPrime using incorrect numbers (at least three)
4. a method to check digitsSum using a set of at least 10 numbers

#### Requirements:

- NumberWorkerTest class must contain at least 4 methods to test NumberWorker functionality
- Use of @ParameterizedTest and @ValueSource is mandatory for methods 1–3.
- Use of @ParameterizedTest and @CsvFileSource is mandatory for method 4.
- You need to prepare data.csv file for method 4 where you shall specify at least 10 numbers and their correct sum of digits. A file content example: 1234, 10

#### Project structure:

- Tests 
  - src
    - main
      - java
        - edu.school21.numbers
          - NumberWorker
      - resources
    - test
      - java
        - edu.school21.numbers
          - NumberWorkerTest
      - resources
        - data.csv
  - pom.xml