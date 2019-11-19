# goose-game

The Goose Game written as a console java application.

[![asciicast](https://asciinema.org/a/Hfq5fxmgiV65GIEVYjdQGR1OF.svg)](https://asciinema.org/a/Hfq5fxmgiV65GIEVYjdQGR1OF)

## Implementation details

- written in plain Java
- implemented as a state machine

![state-machine](docs/state-machine.png)

- all operations are implemented with TDD (unit tests)
- allow to rearrange boxes (we can add more Gooses spaces, Bridges, ...)
  - see `GooseBoard.boxes`
  - For example to create a boards with just 14 spaces:
    ```
    private Box[] boxes = { 
        new StartBox(1), new BlankBox(2), new BlankBox(3), new BlankBox(4), new GooseBox(5),
        new BridgeBox(6, 12), new BlankBox(7), new BlankBox(8), new GooseBox(9), new BlankBox(10),
        new BlankBox(11), new BlankBox(12), new BlankBox(13), new FinishBox(14) };

    ```
- allow to add more than 2 players
  - see `WaitingForPlayersState.process` (currently configured with 2)
- allow to use more than 2 dices
  - see `PlayerTurnState.process` (currently configured with 2)
- game rules are all written inside `GooseBoard.java`, with the corresponding unit tests
- all rules implemented:
  - bridge
  - goose (single or multiple)
  - prank
  - bounce

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