## Exercise 01 – Embedded DataBase

Do not use a heavy DBMS (like PostgreSQL) to implement integration testing of components that interact with the database. It is best to create a lightweight in-memory database with prearranged data.

Implement DataSource creation mechanism for HSQL DBMS. To do so, connect spring-jdbc and hsqldb dependencies to the project. Prepare schema.sql and data.sql files where you will describe product table structure and test data (at least five).

Product table structure:

- identifier
- name
- price

Also create EmbeddedDataSourceTest class. In this class, implement init() method marked with @BeforeEach annotation. In this class, implement functionality to create DataSource using EmbeddedDataBaseBuilder (a class in spring-jdbc library). Implement a simple test method to check the return value of getConnection() method created by DataSource (this value must not be null).

**Project structure**:

- Tests
    - src
        - main
            - java
                - edu.school21.numbers
                    - NumberWorker
            - resources
        - test
            - java
                - edu.school21
                    - numbers
                        - NumberWorkerTest
                    - repositories
                        - EmbeddedDataSourceTest
            - resources
                -	data.csv
                -	schema.sql
                -	data.sql
    - pom.xml
