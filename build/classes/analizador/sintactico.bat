SET JAVA_HOME="C:\Program Files\Java\jdk1.8.0_111\bin"
SET PATH=%JAVA_HOME%;%PATH%
cd C:\Users\oscar\Documents\NetBeansProjects\Coline\src\analizador
java -jar C:\Fuente\java-cup-11b.jar -parser Sintactico -symbols Sym sintactico.cup
pause