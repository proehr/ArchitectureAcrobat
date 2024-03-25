SET JAVA_HOME=C:\Users\herec\.jdks\azul-17.0.6
RMDIR /S /Q "desktop\build\"
CALL gradlew clean desktop:dist
jpackage --type app-image --name "Architecture Acrobat" ^
    --input desktop\build\libs --main-jar desktop-1.0.jar ^
    --dest desktop\build
