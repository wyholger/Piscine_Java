## Exercise 03 – Test for Service

Exercise 03: Test for Service ||
---|---
Turn-in directory |	ex03
Files to turn-in |	Tests

An important rule for module tests:  an individual system component shall be tested without calling its dependencies' functionality. This approach allows developers to create and test components independently, as well as postpone the implementation of specific application parts.

Now you need to implement the business logic layer represented by UsersServiceImpl class. This class contains a user authentication logic. It also has a dependency on UsersRepository interface (in this task, you do not need to implement this interface).

UsersRepository interface (that you have described) shall contain the following methods:
```java
User findByLogin(String login);
void update(User user);
```
It is assumed that findByLogin method returns a Userobject found via login, or throws EntityNotFoundException if no user is found with the login specified. Update method throws a similar exception when updating a user that does not exist in the database.

User entity shall contain the following fields:
- Identifier
- Login
- Password
- Authentication success status (true - authenticated, false - not authenticated)

In turn, UsersServiceImpl class calls these methods inside the authentication function:
```java
boolean authenticate(String login, String password)
```

This method:
1.	Checks if a user has been authenticated in the system using this login. If authentication was performed, AlreadyAuthenticatedException must be thrown.
2.	The user with this login is retrieved from UsersRepository.
3.	If the retrieved user password matches the specified password, the method sets the status of the authentication success for the user, updates its information in the database and returns true. If passwords mismatch, the method returns false.

Your goal is to:
1.	Create UsersRepository interface
2.	Create UsersServiceImpl class and authenticate method
3.	Create a module test for UsersServiceImpl class

Since your objective is to check correct operation of authenticate method independently of UsersRepository component, you should use mock object and stubs of findByLogin and update methods (see Mockito library).

Authenticate method shall be checked for three cases:
1.	Correct login/password (check calling update method using verify instruction of Mockito library)
2.	Incorrect login
3.	Incorrect password

**Project structure**:

- Tests
    - src
        - main
            - java
                - edu.school21
                    - exceptions
                        - AlreadyAuthenticatedException
                    - numbers
                        - NumberWorker
                    - models
                        - Product
                        - User
                    - services
                        - UsersServiceImpl
                    - repositories
                        - ProductsRepository
                        - ProductsRepositoryJdbcImpl
                        - UsersRepository
            - resources
        - test
            - java
                - edu.school21
                    - services
                        - UsersServiceImplTest
                    - numbers
                        - NumberWorkerTest
                    - repositories
                        - EmbeddedDataSourceTest
                        - ProductsRepositoryJdbcImplTest
            - resources
                -	data.csv
                -	schema.sql
                -	data.sql
    - pom.xml
