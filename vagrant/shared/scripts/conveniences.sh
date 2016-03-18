#!/bin/sh
cd
ln -s /usr/local/blackboard
ln -s /usr/local/blackboard/logs
ln -s /usr/local/blackboard/config
ln -s /usr/local/blackboard/content/vi/BBLEARN/plugins
ln -s /usr/local/blackboard/apps/tomcat
ln -s /usr/local/blackboard/system/database/vi db
if [ ! -d '.ssh' ] ; then mkdir .ssh ; fi
cat /vagrant/etc/authorized_keys >> ~/.ssh/authorized_keys

