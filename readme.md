# Gilded Rose Kata

My take on Gilded rose kata.

## Motivations

For testing I picked Java/Spock version of kata. Spock is groovy based
testing framework that personally blows all other java testing frameworks
out of the water mainly for readability, ease of use and syntactic sugar
which reduces unnecessary code noise that haunts plain JAVA.

## Prerequisits

* JAVA verion 1.8.0_121
* Docker version 18.03.1-ce
* docker-compose version 1.21.0, build 5920eb08

## How to Run

| Command                           | Description                                           |
| -------------                     |:-------------:                                        |
| `.gradlew build`                  | builds and runs tests                                 |
| `.docker-compose up --build`      | builds and runs app in a docker container (port 8080) |

**Optionally:** You can run this from your IDE
