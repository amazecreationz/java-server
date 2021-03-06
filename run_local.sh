#!/bin/bash

OPENSHIFT_DATA_DIR='/home/anandmoghan/Storage/workspace/data'
export OPENSHIFT_DATA_DIR
SERVER='/home/anandmoghan/Storage/workspace/servers/8080'
rm -r $SERVER'/webapps/ROOT'
mvn compile && mvn package
mv target/javaserver-1.0 $SERVER'/webapps/ROOT' 
sh $SERVER'/bin/catalina.sh' run
