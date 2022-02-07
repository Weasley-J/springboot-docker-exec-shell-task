#!/bin/bash

DOCKER_FILE="./Dockerfile"
CONTAINER="springboot-exec-shell-task"
PORT="10001"
docker stop ${CONTAINER} && docker rm -f ${CONTAINER}
docker rmi -f ${CONTAINER}:latest
docker build -f ${DOCKER_FILE} -t ${CONTAINER}:latest .

docker stop ${CONTAINER} && docker rm -f ${CONTAINER}
docker run --name ${CONTAINER} --restart=always \
  -p ${PORT}:10001 \
  -e JAVA_OPTS="-Xms256m -Xmx256m" \
  -e SERVER_PORT="${PORT}" \
  -v /etc/timezone:/etc/timezone \
  -v /etc/localtime:/etc/localtime \
  -d ${CONTAINER}:latest

clear && docker logs -f ${CONTAINER}
