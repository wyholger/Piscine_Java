##Exercise 00 – File Signatures

Input/output classes in Java are represented by a broad hierarchy. The key classes describing byte input/output behavior are abstract classes InputStream and OutputStream. They do not implement specific mechanisms for byte flows processing, rather delegate them to their subclasses, such as FileInputStream/FileOutputStream.

To understand the use of this functionality, you should implement an application for analyzing signatures of arbitrary files. This signature allows to define file content type and consists of a set of "magic numbers." These numbers are usually located in the beginning of the file. For example, a signature for PNG file type is represented by first eight bytes of a file that are equal for all PNG images:
```
89 50 4E 47 0D 0A 1A 0A
```
You need to implement an application that accepts the signatures.txt as an input (you should describe it on your own; the file name is explicitly stated in the program code). It contains a list of file types and their respective signatures in the HEX format. Example (specified format of this file must be adhered to):

```
PNG, 89 50 4E 47 0D 0A 1A 0A
GIF, 47 49 46 38 37 61
```

During execution, your program shall accept full paths to files on hard disk and keep the type which file signature corresponds to. The result of program execution should be written to result.txt file. If a signature cannot be defined, the execution result is UNDEFINED (no information should be written into the file).

Example of program operation:

```
$java Program
-> C:/Users/Admin/images.png
PROCESSED
-> C/Users/Admin/Games/WoW.iso
PROCESSED
-> 42
```

Contents of result.txt file (there is no need to load this file as a result):

```
PNG
GIF
```

####Notes:

We can accurately determine the content type by analyzing the file signature, since the file extension contained in the name (e. g. image.jpg) can be changed by simply renaming the file.

The signatures file shall contain at least 10 different formats for analysis.

