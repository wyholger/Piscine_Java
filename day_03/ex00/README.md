##Exercise 00 – Egg, Hen... or Human?

Chicken or egg

The truth is born in a dispute—let's assume that each thread provides its own answer. The thread that has the last word is right.

You need to implement the operation of two threads. Each of them must display its answer a few times, for example, 50:

```
$ java Program --count=50
Egg
Hen
Hen
Hen
...
Egg
```

In this case, the egg thread wins. However, the program also contains main thread. Inside the thread, public static void main(String args[]) method is executed. We need this thread to display all its responses at the end of program execution. Thus, the ultimate variant is as follows:

```
$ java Program --count=50
Egg
Hen
Hen
...
Egg
Hen
...
Human
...
...
Human
```

It means that the program outputs Human message 50 times, which main thread prints.
