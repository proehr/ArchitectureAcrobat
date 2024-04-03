SET JAVA_HOME=C:\Users\herec\.jdks\azul-17.0.6
RMDIR /S /Q "lwjgl3\build\"
CALL gradlew clean lwjgl3:dist
jpackage --type app-image --name "Architecture Acrobat" ^
    --input lwjgl3\build\lib --main-jar ArchitectureAcrobat-1.0.0.jar ^
    --dest lwjgl3\build
