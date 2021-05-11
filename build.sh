#!/usr/bin/env bash
mvn clean install
scp server/target/springboot-start-0.0.1-SNAPSHOT.jar admin@hadoop-3:
