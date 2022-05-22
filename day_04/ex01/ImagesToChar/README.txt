# удаляем дирректорию target если она осталась после предыдущих запусков
rm -rf target

# создаем новую дирректорию target
mkdir target

# компилируем *.java файлы в *.class с флагом -d который позволяет указать дирректорию куда положатся файлы *.class
javac -d ./target/ src/java/edu/school21/printer/*/*.java

# копируем дирректорию resources с изображением в target
cp -R src/resources target/.

# архивируем .class файлы в jar архив с укзав файл манифеста в котором описан стартовый класс
jar cfm ./target/images_to_char_printer.jar src/manifest.txt -C target .

# даем права на исполнение сгенерированному .jar файлу
chmod u+x target/images_to_char_printer.jar

# запускаем на исполнение программу через архив .jar
java -jar target/images_to_char_printer.jar . 0
