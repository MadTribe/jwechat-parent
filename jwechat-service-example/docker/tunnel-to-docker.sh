if [ -z ${WECHAT_REQUESTED_SDOMAIN+x} ]; then
    echo "WECHAT_REQUESTED_SDOMAIN not set so will use random one";
    WECHAT_REQUESTED_SDOMAIN="";
else 
    WECHAT_REQUESTED_SDOMAIN="-s $WECHAT_REQUESTED_SDOMAIN";
fi

#DOCKER_IP=$(echo $DOCKER_HOST | cut -d":" -f2 | cut -d"/" -f3 )
DOCKER_IP=127.0.0.1

echo $DOCKER_IP

echo "Configure Wechat callback to point to <tunnel url below>/wxcallback"

alias node=nodejs

lt -s $WECHAT_REQUESTED_SDOMAIN -l $DOCKER_IP -p 8080

