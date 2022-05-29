## Exercise 03 – Update

Now we need to implement update method in MessageRepository. This method shall fully update an existing entity in the database. If a new value of a field in an entity being updated is null, this value shall be saved in the database.

An example of update method use:

```
public static void main(String args[]) {
  MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(...);
  Optional<Message> messageOptional = messagesRepository.findById(11);
  if (messageOptinal.isPresent()) {
    Message message = messageOptional.get();
    message.setText(“Bye”);
    message.setDateTime(null);
    messagesRepository.update(message);
  }
  ...
}
```

In this example, the value of the column storing the message text will be altered, whereas message time will be null.

