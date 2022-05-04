##Exercise 02 – List of Users

Now we need to implement a functionality for storing users while the program runs.

At the moment, your application has no persistent storage (such as a file system or a database). However, we want to avoid the dependence of your logic on user storage implementation method. To ensure more flexibility, let us define UsersList interface that describes the following behavior:

- Add a user
- Retrieve a user by ID
- Retrieve a user by index
- Retrieve the number of users

This interface will enable to develop the business logic of your application so that a specific storage implementation does not affect other system components.

We shall also implement UsersArrayList class that implements UsersList interface.

This class shall use an array to store user data. The default array size is 10. If the array is full, its size is increased by half. The user-adding method puts an object of User type in the first empty (vacant) cell of the array.

In case of an attempt to retrieve a user with a non-existent ID, an unchecked UserNotFoundException must be thrown.

An example of use of such classes shall be contained in Program file (creation, initialization, printing object content on a console).

Note:
Nested ArrayList Java class has the same structure. By modeling behavior of this class on your own, you will learn how to use mechanisms of this standard library class.

