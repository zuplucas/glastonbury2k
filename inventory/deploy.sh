#!/bin/bash

mvn clean "install"

docker build -t inventory .

docker network create --driver bridge order-network || true

docker-compose up -d --build

docker logs -f inventory