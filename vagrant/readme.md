Vagrant for integration tests
===

You need to install the Blackboard developer virtual machine first. This
includes installing:
 * VirtualBox 5.x  https://www.virtualbox.org/wiki/Downloads
 * Vagrant 1.7+  https://www.vagrantup.com/downloads.html
 * Learn Image https://behind.blackboard.com/System-Administrator/Learn/Downloads/download.aspx?d=1736 
    (you need an login on behind.blackboard.com)
    
For instructions see https://community.blackboard.com/docs/DOC-1649
skip the `vagrant up` step for now.

Run `vagrant box add --name 2015q4_test-target BOXFILE`
where BOXFILE is the actual location of the downloaded Learn image, in my case
/opt/vbox/bb-learn-9.1.201510.1171702.box

Recommended: put your public ssh key in ./shared/etc/authorized_keys
(where . is the directory where this readme.md file is)
 
In this (where this readme.md file is) directory run `vagrant up` This can
take a while (5+ minutes). When it's done, run 
`vagrant snapshot save 2015q4_test-target clean-install`

