## Exercise 02 – Create/Save

Now you need to implement save(Message message) method for MessagesRepository.

Thus, we need to define the following subentities for the entity we are saving—a message author and a chatroom. It is also important to assign IDs that exist in the database to chatroom and author.

Example of save method use:

```
public static void main(String args[]) {
	...
  User creator = new User(7L, “user”, “user”, new ArrayList(), new ArrayList());
  User author = creator;
  Room room = new Room(8L, “room”, creator, new ArrayList());
  Message message = new Message(null, author, room, “Hello!”, LocalDateTime.now());
  MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(...);
  messagesRepository.save(message);
  System.out.println(message.getId()); // ex. id == 11
}
```

So, save method shall assign ID value for the incoming model after saving data in the database. If author and room have no ID existing in the database assigned, or these IDs are null, throw Runtimeexception NotSavedSubEntityException (implement this exception on your own).

Test the implemented code in Program.java class.

