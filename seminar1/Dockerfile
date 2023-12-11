FROM openjdk:11
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN javac src/operations/MathOperations.java src/operations/ResultPrinter.java src/main.java
CMD ["java", "-cp", "/usr/src/app", "src.main"]
