##Exercise 04 – Business Logic

The business logic level of the application is located in service classes. Such classes contain basic algorithms of the system, automated processes, etc. These classes are usually designed based on the Facade pattern that can encapsulate behavior of several classes.

In this case, TransactionsService class must contain a field of UserList type for user interactions and provide the following functionality:

- Adding a user

- Retrieving a user's balance

- Performing a transfer transaction (user IDs and transfer amount are specified). In this case, two transactions of DEBIT/CREDIT types are created and added to recipient and sender. IDs of both transactions must be equal

- Retrieving transfers of a specific user (an ARRAY of transfers is returned). Removing a transaction by ID for a specific user (transaction ID and user ID are specified)

- Check validity of transactions (returns an ARRAY of unpaired transactions).

In case of an attempt to make a transfer of the amount exceeding user's residual balance, IllegalTransactionException runtime exception must be thrown.

An example of use of such classes shall be contained in Program file (creation, initialization, printing object content on a console).

