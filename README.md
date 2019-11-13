# goose-game

A Java 11 exercise. Implement a console goose-game.


## Implementation details

TODO
- state machine (similar to Akka FSM)
- unit tests
- case classes like (commands, state)
- over enginered

## Usage

Requirements:

- **Java JDK 11**
- Maven 3.6.0

Development environment:

- Visual Studio Code
- OpenJDK 11
- Ubuntu Linux

Run unit tests:

    mvn test

Run console application:

    mvn compile
    $JAVA_HOME/bin/java -cp ./target/classes icardi.goose.game.App