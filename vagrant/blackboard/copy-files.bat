vagrant plugin install vagrant-scp

If Not Exist "config/bb-config.properties" (
  vagrant scp :/usr/local/blackboard/config .
)



If Not Exist lib (
  mkdir lib 
  vagrant scp :/usr/local/blackboard/systemlib/bb-embedded.jar lib 
  vagrant scp :/usr/local/blackboard/systemlib/bb-exec.jar lib 
  vagrant scp :/usr/local/blackboard/systemlib/xythos/jlansrv.jar lib 
  vagrant scp :/usr/local/blackboard/systemlib/xythos/xss.jar lib 
  vagrant scp :/usr/local/blackboard/systemlib/jdbc/ojdbc6.jar lib
 
)



If Not Exist content (
  mkdir content\locale\en_US\messages 
  vagrant scp :/usr/local/blackboard/content/locale/en_US/messages/* content\locale\en_US\messages 
  vagrant scp :/usr/local/blackboard/content/locale/locales.properties content\locale 
)

vagrant ssh -c 'sudo chmod a+r /usr/local/blackboard/apps/xythos/xythos.properties' 
vagrant scp :/usr/local/blackboard/apps/xythos/xythos.properties . 
vagrant ssh -c 'sudo chmod 600 /usr/local/blackboard/apps/xythos/xythos.properties'


echo modify config/bb-config.sh and change the bbconfig.basedir and
echo bbconfig.base.shared.dir properties to respectively
echo vagrant/blackboard and vagrant/blackboard/content
