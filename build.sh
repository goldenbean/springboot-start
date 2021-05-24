#!/usr/bin/env bash

mvn clean install -DskipTests && \
  docker build -t 10.58.10.201:55000/livy-server . && \
  docker push 10.58.10.201:55000/livy-server && \
  kubectl get pods -n gateway | grep livy-server | awk '{print $1}' | xargs kubectl -n gateway delete pods


