# goose-game

The Goose Game written as a console java application.

[![asciicast](https://asciinema.org/a/Hfq5fxmgiV65GIEVYjdQGR1OF.svg)](https://asciinema.org/a/Hfq5fxmgiV65GIEVYjdQGR1OF)

## Implementation details

- written in plain Java
- implemented as a state machine

![state-machine](docs/state-machine.png)

- all operations are implemented with TDD (unit tests)
- allow to rearrange boxes (we can add more Gooses spaces, Bridges, ...)
- allow to add more than 2 players (currently configured with 2)
- allow to use more than 2 dices (currently configured with 2)
- game rules are all written inside `GooseBoard.java`, with the corresponding unit tests

## Requirements

- Java 1.8
- Maven 3.6.0

## Development environment

- Visual Studio Code
- OpenJDK 1.8
- Ubuntu Linux

## Usage

Clone the repository.

Compile:

    mvn compile

Run unit tests:

    mvn test

Run console application:

    java -cp ./target/classes icardi.goose.game.App