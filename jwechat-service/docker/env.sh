#!/bin/bash

RET=0;

if [ -z ${SAMPLE_WECHAT_APP_ID+x} ]; then
    echo "Please set environment variable SAMPLE_WECHAT_APP_ID";
    RET=1;
fi

if [ -z ${SAMPLE_WECHAT_APP_SECRET+x} ]; then
    echo "Please set environment variable SAMPLE_WECHAT_APP_SECRET";
    RET=1;
fi

if [ -z ${SAMPLE_WECHAT_APP_TOKEN+x} ]; then
    echo "Please set environment variable SAMPLE_WECHAT_APP_TOKEN";
    RET=1;
fi

return $RET;