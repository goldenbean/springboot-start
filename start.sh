#!/bin/bash
mvn clean package -DskipTests
PROJECT=/Users/neishui/Coding/my-github/springboot-start
java -Dloader.path="$PROJECT/spi/target" -jar $PROJECT/server/target/springboot-start-server-0.0.1-SNAPSHOT.jar
