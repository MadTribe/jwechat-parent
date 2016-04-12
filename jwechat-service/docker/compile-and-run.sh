#!/bin/bash
set -e

PWD="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

pushd "$PWD/.." 1>/dev/null
export SERVICE_HOME=`pwd`
popd 1>/dev/null

PWD

export SERVICE_NAME=jwechat-service
export SERVICE_VERSION=1.0-SNAPSHOT
export JAR_PATH=$SERVICE_HOME/target/
export JAR_NAME="$SERVICE_NAME-$SERVICE_VERSION.jar"
export CONFIG_FILE=dev-config.yml
export CONFIG_PATH=$SERVICE_HOME
export DOCKER_FOLDER=$SERVICE_HOME/docker


#pushd $SERVICE_HOME
#mvn package
#popd

docker-compose -f $DOCKER_FOLDER/docker-compose.yml stop
docker-compose -f $DOCKER_FOLDER/docker-compose.yml  rm -f
docker-compose -f $DOCKER_FOLDER/docker-compose.yml  up -d
