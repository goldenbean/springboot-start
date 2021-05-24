#!/usr/bin/env bash

java -server -XX:+UseG1GC -XX:MaxGCPauseMillis=100 -Xmx1000m -jar /work/springboot-start-server-0.0.1-SNAPSHOT.jar
