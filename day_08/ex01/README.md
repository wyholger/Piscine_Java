## Exercise 01 – JdbcTemplate

Exercise 01: JdbcTemplate ||
---|---
Turn-in directory |	ex01
Files to turn-in |	Service-folder

JdbcTemplate and its extension NamedParameterJdbcTemplate are convenient mechanisms for working with databases. These classes allow to eliminate writing template code for query execution and processing, as well as the need to intercept exceptions under check.

In addition, they provide a convenient RowMapper concept for ResultSet processing and converting resulting tables into objects.

Now, you need to implement the User model with the following fields:
- Identifier
- Email

You also need to implement `CrudRepository<T>` interface with the following methods:
- `Optional<T>` findById(Long id)
- `List<T>` findAll()
- void save(T entity)
- void update(T entity)
- void delete(Long id)

UsersRepository interface declared as UsersRepository extends CrudRepository<User> shall contain the following method:
- `Optional<T>` findByEmail(String email)

In addition, two implementations of UsersRepository are required:<br> UsersRepositoryJdbcImpl (uses standard Statements meachanisms) and UsersRepositoryJdbcTemplateImpl (is based on JdbcTemaplte/NamedParameterJdbcTemaple). Both classes shall accept DataSource object as a constructor argument.

In context.xml file, beans shall be declared for both repository types with different identifiers, as well as two beans of DataSource type: DriverManagerDataSource and HikariDataSource.

In addition, data for connecting to DB shall be specified in db.properties file and included in context.xml using `${db.url}`  placeholders.

Example of db.properties:
```
db.url=jdbc:postgresql://localhost:5432/database
db.user=postgres
db.password=qwerty007
db.driver.name=org.postgresql.Driver
```

In Main class, operation of findAll method shall be demonstrated using both repositories:
```java
ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
System.out.println(usersRepository.findAll());
usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
System.out.println(usersRepository.findAll());
```

**Project structure**:
- Service
    - src
        - main
            - java
                - school21.spring.service
                    - models
                        - User
                    - repositories
                        - CrudRepository
                        - UsersRepository
                        - UsersRepositoryJdbcImpl
                        - UsersRepositoryJdbcTemplateImpl
                    - application
                        - Main
            - resources
                -	db.properties
                -	context.xml
    -	pom.xml