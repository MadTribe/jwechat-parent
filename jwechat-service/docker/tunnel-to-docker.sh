
DOCKER_IP=$(echo $DOCKER_HOST | cut -d":" -f2 | cut -d"/" -f3 )

echo $DOCKER_IP

lt -l $DOCKER_IP -p 8080