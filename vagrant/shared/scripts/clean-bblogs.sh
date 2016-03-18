#!/bin/bash

if [ 'bbuser' != `whoami` ] ; then
  echo ERROR this script must be run by user 'bbuser'
  exit;
fi

LOG_DIR=/usr/local/blackboard/logs

rm -f ${LOG_DIR}/*.txt
rm -f ${LOG_DIR}/tomcat/*
rm -rf ${LOG_DIR}/collab-server
rm -rf ${LOG_DIR}/httpd
rm -rf ${LOG_DIR}/snapshot

