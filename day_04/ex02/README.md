## Exercise 02 – JCommander & JCDP

Now you should use external libraries:

- JCommander for the command line.
- JCDP or JColor for using colored output
Archives with these libraries shall be downloaded and included in the previous task's project.

Now application startup parameters shall be processed with JCommander tools. The image should be displayed using the "colored" output option of JCDP library.

Required project structure:

- ImagesToChar - project folder
  - lib - external library folder 
    - jcommander-*.**.jar
    - JCDP-...jar/JCOLOR-...jar
  - src - source files
  - target - compiled .class files and archive
    - edu.school21.printer
    - com/beust ... - .class files of JCommander library
    - com/diogonunes ... - .class files of JCDP library
    - resources
    - images-to-chars-printer.jar
  - README.txt

README.txt file shall also contain the information about including external libraries in the final assembly.

Example of program operation:

```
$ java -jar images-to-chars-printer.jar --white=RED --black=GREEN
```