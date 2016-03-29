#!/bin/bash

#if [ 'bbuser' != `whoami` ] ; then
#  echo ERROR this script must be run by user 'bbuser'
#  exit;
#fi

CMD=/usr/local/blackboard/apps/content-exchange/bin/content-exchange.sh

if [ ! -d /vagrant/courses ] ; then 
  exit;
fi
for zip in `find /vagrant/courses -name \*.zip` ; do
 course_id=`basename $zip .zip | sed -E 's/(Archive|Export)File_(.+)_[0-9]+$/\2/'`
 echo restoring ${course_id}

 # output to /dev/null as we're not interested in the stacktraces generated
 # by data that refers to building blocks that are not installed
 $CMD -o restore -c ${course_id} -f $zip &> /dev/null
done



