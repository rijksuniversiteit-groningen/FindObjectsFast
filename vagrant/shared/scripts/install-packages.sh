#!/bin/bash

if [ 'root' != `whoami` ] ; then
  echo ERROR this script must be run by user 'root'
  exit;
fi

yum -y -q install zip unzip mlocate screen telnet patch

cat /etc/updatedb.conf | sed 's/\/afs/\/afs \/vagrant/' > /tmp/updatedb.conf
mv /tmp/updatedb.conf /etc

# update 'locate' database after removing building blocks
#updatedb

echo additional packages installed
