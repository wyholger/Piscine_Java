##Exercise 03 – List of Transactions

Unlike users, a list of transactions requires a special implementation approach. Since the number of transaction creation operations can be very large, we need a storage method to avoid a costly array size extension.

In this task, we offer you to create TransactionsListinterface describing the following behavior:

- Add a transaction
- Remove a transaction by ID (in this case, UUID string identifier is used)
- Transform into array (ex. Transaction[] toArray())

A list of transactions shall be implemented as a linked list (LinkedList) in TransactionsLinkedList class. Therefore, each transaction shall contain a field with a link to the next transaction object. If an attempt is made to remove a transaction with non-existent ID, TransactionNotFoundException runtime exception must be thrown. An example of use of such classes shall be contained in Program file (creation, initialization, printing object content on a console).

Note:

- We need to add transactions field of TransactionsList type to User class so that each user can store the list of their transactions.
- A transaction must be added with a SINGLE operation (O(1))
- LinkedList nested Java class has the same structure, a bidirectional linked list.