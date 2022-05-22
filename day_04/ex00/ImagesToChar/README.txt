# удаляем дирректорию target если она осталась после предыдущих запусков
rm -rf target

# создаем новую дирректорию target
mkdir target

# компилируем *.java файлы в *.class с флагом -d который позволяет указать дирректорию куда положатся файлы *.class
javac -d ./target/ src/java/edu/school21/printer/*/*.java

# запускаем программу с аргументами
java -cp ./target edu.school21.printer.app.Program . 0 /Users/wyholger/Desktop/it.bmp