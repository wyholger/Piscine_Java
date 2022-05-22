mkdir target

javac -d ./target/ src/java/edu/school21/printer/*/*.java

# Launch app
    # Usage: app <char for white pixels> <char for black pixels> <full path to black & white bmp image>
# java -cp ./target edu.school21.printer.app.Main

# Example use:
java -cp ./target edu.school21.printer.app.Program . 0 /Users/wyholger/Desktop/it.bmp