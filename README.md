# goose-game

The Goose Game written as a console java application.


## Implementation details

TODO
- state machine (similar to Akka FSM)
- unit tests
- case classes like (commands, state)
- rearrange the box positions and box numbers

## Usage

Requirements:

- **Java JDK 11**
- Maven 3.6.0

Development environment:

- Visual Studio Code
- OpenJDK 1.8
- Ubuntu Linux

Run unit tests:

    mvn test

Run console application:

    mvn compile
    java -cp ./target/classes icardi.goose.game.App