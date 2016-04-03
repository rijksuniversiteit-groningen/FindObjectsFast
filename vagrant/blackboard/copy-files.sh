vagrant plugin install vagrant-scp

vagrant scp :/usr/local/blackboard/config .

mkdir lib
vagrant scp :/usr/local/blackboard/systemlib/bb-embedded.jar lib
vagrant scp :/usr/local/blackboard/systemlib/bb-exec.jar lib
vagrant scp :/usr/local/blackboard/systemlib/jlansrv.jar lib
vagrant scp :/usr/local/blackboard/systemlib/xythos/jlansrv.jar lib
vagrant scp :/usr/local/blackboard/systemlib/xythos/xss.jar lib
vagrant scp :/usr/local/blackboard/systemlib/xythos/bb-bbxythos.jar lib

mkdir -p content/locale/en_US/messages
vagrant scp :/usr/local/blackboard/content/locale/en_US/messages/\* content/locale/en_US/messages
vagrant scp :/usr/local/blackboard/content/locale/locales.properties content/locale/
