#!/bin/bash

main() {
    docker-compose up -d

 sleep 30

    npm install -g newman
    newman run ./tests/Tests-collection.json --timeout 18000000 --timeout-request 18000000 --timeout-script 18000000 --bail
    status=$?
    exit $status
}

main

