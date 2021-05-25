#!/usr/bin/env bash

kubectl get pods -n gateway | grep livy-server | awk '{print $1}' | xargs kubectl -n gateway logs -f