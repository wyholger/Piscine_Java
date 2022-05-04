##Exercise 00 – Models

Your first task is to develop basic domain models—namely, User and Transaction classes.

It is quite likely for different users to have the same name in the system. This problem should be solved by adding a special field for a user's unique ID. This ID can be any integer number. Specific ID creation logic is described in the next exercise.

Thus, the following set of states (fields) is typical for User class:

- Identifier
- Name
- Balance

Transaction class describes a money transfer between two users. Here, a unique identifier should also be defined. Since the number of such transactions can be very large, let us define the identifier as an UUID string. Thus, the following set of states (fields) is typical for Transaction class:


- Identifier
- Recipient (User type)
- Sender (User type)
- Transfer category (debits, credits)
- Transfer amount

It is necessary to check the initial user balance (it cannot be negative), as well as the balance for the outgoing (negative amounts only) and incoming (positive amounts only) transactions (use of get/set methods).

An example of use of such classes shall be contained in Program file (creation, initialization, printing object content on a console). All data for class fields must be hardcoded in Program.