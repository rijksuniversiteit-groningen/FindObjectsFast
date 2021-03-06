#!/usr/bin/env bash
vagrant plugin list | grep vagrant-scp
if [ ! $? ] ; then
  vagrant plugin install vagrant-scp
fi

if [ ! -f config/bb-config.properties ] ; then
  vagrant scp :/usr/local/blackboard/config .
fi

if [ ! -d lib ] ; then
  mkdir lib
  vagrant scp :/usr/local/blackboard/systemlib/bb-embedded.jar lib
  vagrant scp :/usr/local/blackboard/systemlib/bb-exec.jar lib
  vagrant scp :/usr/local/blackboard/systemlib/xythos/jlansrv.jar lib
  vagrant scp :/usr/local/blackboard/systemlib/xythos/xss.jar lib
  vagrant scp :/usr/local/blackboard/systemlib/xythos/bb-bbxythos.jar lib
  vagrant scp :/usr/local/blackboard/systemlib/jdbc/ojdbc6.jar lib
fi

if [ ! -d content ] ; then
  mkdir -p content/locale/en_US/messages
  vagrant scp :/usr/local/blackboard/content/locale/en_US/messages/\* content/locale/en_US/messages
  vagrant scp :/usr/local/blackboard/content/locale/locales.properties content/locale/
fi

vagrant ssh -c 'sudo chmod a+r /usr/local/blackboard/apps/xythos/xythos.properties' > /dev/null 2>&1
vagrant scp :/usr/local/blackboard/apps/xythos/xythos.properties . > /dev/null 2>&1
vagrant ssh -c 'sudo chmod 600 /usr/local/blackboard/apps/xythos/xythos.properties' > /dev/null 2>&1


echo modify config/bb-config.sh and change the bbconfig.basedir and
echo bbconfig.base.shared.dir properties to respectively
echo `pwd` and
echo `pwd`/content
