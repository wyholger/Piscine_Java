### Exercise 00 – Spring Context

Exercise 00: Spring Context ||
---|---
Turn-in directory |	ex00
Files to turn-in |	Spring-folder

Let's implement a loosely-coupled system comprised of a set of components (beans) and compliant with IoC/DI principles.

Let's assume there is Printer interface designed to display a specific message.

This class has two implementations: PrinterWithDateTimeImpl and PrinterWithPrefixImpl. The first class outputs messages by specifying output date/time using LocalDateTime, while the second class can be used to set a text prefix for a message.

In turn, both Printer implementations have a dependency on Renderer interface that sends messages onto the console. Renderer also has two implementations: RendererStandardImpl (outputs a message via standard System.out) and RendererErrImpl (outputs messages via System.err).

Renderer also has a dependency on PreProcessor interface that pre-processes messages. Implementation of PreProcessorToUpperImpl translates all letters into upper case, while implementation of PreProcessorToLower translates all letters into lower case.

UML diagram of classes is shown below:

![Diagram of classes](misc/images/Diagram.png)

An example of code using these classes in a standard way:
```java
public class Main {
   public static void main(String[] args) {
       PreProcessor preProcessor = new PreProcessorToUpperImpl();
       Renderer renderer = new RendererErrImpl(preProcessor);
       PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
       printer.setPrefix("Prefix");
       printer.print("Hello!");
   }
}
```
Running this code will deliver the following result:

```
PREFIX HELLO!
```
You need to describe context.xml file for Spring, where all settings for each component and links between them will be specified.

Using these components with Spring looks as follows:
```java
public class Main {
   public static void main(String[] args) {
       ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
       Printer printer = context.getBean(“printerWithPrefix”, Printer.class);
       printer.print("Hello!");
   }
}
```

