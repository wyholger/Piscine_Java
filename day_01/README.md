##Introduction to exercises
An internal money transfer system is an integral part of many corporate applications.

Your today's task is to automate a business process associated with transfers of certain amounts between participants of our system.

Each system user can transfer a certain amount to another user. We need to make sure that even if we lose the history of incoming and outgoing transfers for a specific user, we shall still be able to recover this information.

Inside the system, all money transactions are stored in the form of debit/credit pairs. For example, John has transferred $500 to Mike. System saves the transaction for both users:
```
John -> Mike, -500, OUTCOME,  transactionID
Mike -> John, +500, INCOME,  transactionID
```
To recover the connection within such pairs, identifiers of each transaction should be used.

A transfer entry may obviously be lost in such a complex system—it may not be recorded for one of the users (to emulate and debug such a situation, a developer needs to be able to remove the transfer data from one of users individually). Since such situations are realistic, functionality is required for displaying all "unacknowledged transfers" (transactions recorded for one user only) and resolving such issues.

Below is a set of exercises you can do one by one to solve the task.