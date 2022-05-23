# удаляем дирректорию target если она осталась после предыдущих запусков
rm -rf target lib

# создаем новую дирректорию target
mkdir target lib

# копируем сторонние .jar библиотеки в созданную папку lib
cp ./src/resources/*.jar ./lib

# компилируем *.java файлы в *.class с флагом -d который позволяет указать дирректорию куда положатся файлы *.class
javac -cp ".:./lib/JColor-5.3.0.jar:./lib/jcommander-1.82.jar" -d ./target/ src/java/edu/school21/printer/*/*.java

# распаковываем файлы lib jar в целевую папку, сохраняя пакеты и подкаталоги
cd target ; jar xf ../lib/JColor-5.3.0.jar com ; jar xf ../lib/jcommander-1.82.jar com ; cd ..

# копируем дирректорию resources с изображением и библиотеками в target
cp -R src/resources target/.

# архивируем .class файлы в jar архив с укзав файл манифеста в котором описан стартовый класс
jar cfm ./target/images_to_char_printer.jar src/manifest.txt -C target .

# даем права на исполнение сгенерированному .jar файлу
chmod u+x target/images_to_char_printer.jar

# запускаем на исполнение программу через архив .jar
java -jar target/images_to_char_printer.jar --white=BLUE --black=YELLOW
