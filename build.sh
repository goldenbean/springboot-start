#!/usr/bin/env bash
mvn clean install -DskipTests
scp server/target/springboot-start-server-0.0.1-SNAPSHOT.jar admin@hadoop-3:
