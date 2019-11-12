#!/bin/bash

main() {
    docker-compose up -d

    until curl --output /dev/null --silent --fail -d -H "Content-Type: application/json" -X GET http://localhost:8080/clients; do
 printf 'Waiting ...'
 sleep 5
done
    npm install -g newman
    newman run ./backend/tests/Tests-collection.json --timeout 18000000 --timeout-request 18000000 --timeout-script 18000000 --bail
    status=$?
    exit $status
}

main

