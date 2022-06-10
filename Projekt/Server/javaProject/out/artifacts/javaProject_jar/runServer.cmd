REM Cofniecie do foledru Projekt
cd ..
cd .. 
cd .. 
cd .. 
cd ..
REM Wejscie do folderu z potrzebnymi komponentami javafx
cd Server\javafx-sdk-11.0.2\lib
REM Zapisanie sciezki do folederu lib w zmiennej LIB
set LIB="%cd%"
REM Przejscie do folderu Projekt
cd .. 
cd .. 
cd ..
REM Przejscie do folderu z plikiem jar Hotelu
cd Server\javaProject\out\artifacts\javaProject_jar

REM Uruchomienie pliku jar
java -jar --module-path %LIB% --add-modules javafx.controls,javafx.fxml 	javaProject.jar

pause