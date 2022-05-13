##Exercise 01 – Words

In addition to classes designed to handle byte flows, Java has classes to simplify handling of character flows (char). These include abstract classes Reader/Writer, as well as their implementations (FileReader/FileWriter, etc.). Of special interest are BufferedReader/BufferedWriter classes which accelerate flow handling via buffering mechanisms.

Now you need to implement an application that will determine the level of similarity between texts. The simplest and most obvious method to do this is to analyze the frequency of occurrence of the same words.

Let's assume that we have two following texts:

```
1. aaa bba bba a ссс
2. bba a a a bb xxx
```

Let's create a dictionary that contains all words in these texts:

```
a, aaa, bb, bba, ccc, xxx
```

Now let's create two vectors with length equal to that of the dictionary. In i-th position of each vector, we shall reflect the frequency of occurrence of the i-th word in our dictionary in the former and latter texts:

```
A = (1, 1, 0, 2, 1, 0)
B = (3, 0, 1, 1, 0, 1)
```

Thus, each of these vectors characterizes the text in terms of frequency of occurrence of words from our dictionary. Let's determine the similarity between vectors using the following formula:

![](img/formula.png)

Thus, similarity value for these vectors is:

```
Numerator A. B = (1 * 3 + 1 * 0 + 0 * 1 + 2 * 1 + 1 * 0 + 0 * 1) = 5
Denominator ||A|| * ||B|| = sqrt(1 * 1 + 1 * 1 + 0 * 0 + 2  * 2 + 1 * 1 + 0 * 0) * sqrt(3 * 3 + 0 * 0 + 1 * 1 + 1 * 1  + 0 * 0 + 1 * 1) = sqrt(7) * sqrt(12) = 2.64 * 3.46 = 9.1
similarity = 5 / 9.1 = 0.54
```

Your goal is to implement an application that accepts two files as an input (both files are passed as command-line arguments) and displays their similarity comparison outcome (cosine measure).

The program shall also create dictionary.txt file containing a dictionary based on these files.

Example of program operation:

```
$ java Program inputA.txt inputB.txt
Similarity = 0.54
```

####Notes:

1. Maximum size of these files is 10 MB.
2. Files may contain non-letter characters.