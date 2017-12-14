This project is used Spring framework building a sample web application which has embedded web server and memory database.
# How to build
Make sure you have gradle installed on your local
```
_./gradlew clean build_ 
```
# How to run
After building, please run
```
 java -jar build/libs/intuit-0.0.1.jar
```
to start application

## Try some command from browser
```
http://localhost:8080/init
http://localhost:8080/update?nid=1&text=helloworld
http://localhost:8080/findByUserId?uid=1

```