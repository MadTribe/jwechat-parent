#!/bin/bash

RET=0;

#
# Checks whether the named variable has been set and updates global RET if not. 
#
function checkVar {
  local TO_CHECK=$1

  if [ -z ${!TO_CHECK} ]; then
      echo "Please set environment variable $TO_CHECK";
      RET=1;
  fi

}

checkVar "SAMPLE_WECHAT_APP_ID"
checkVar "SAMPLE_WECHAT_APP_SECRET"
checkVar "SAMPLE_WECHAT_APP_TOKEN"

return $RET;
