#!/bin/sh

export SERVER_HOME=$(cd `dirname $0`; pwd)

# service discovery
export QLOUD_DISCOVERY_NAME="discovery"
export QLOUD_DISCOVERY_HOST="192.168.211.101"
export QLOUD_DISCOVERY_PORT="8888"

export QLOUD_CONFIG_USED="false"
export QLOUD_CONFIG_NAME="configuration"
export QLOUD_CONFIG_NAMESPACE=
export QLOUD_CONFIG_ENVIROMENT=
export QLOUD_CONFIG_TOPIC=

export QLOUD_SECURITY_USED="false"
export QLOUD_SECURITY_HOST="192.168.211.101"
export QLOUD_SECURITY_PORT="8888"

export JAVA_OPTS="-Xms512M -Xmx512M"

$JAVA_HOME/bin/java -Xms512M -Xmx512M -Dlog4j.configuration="file:$SERVER_HOME/agent.properties" -cp "$SERVER_HOME:$SERVER_HOME/classes:$SERVER_HOME/lib/*:$SERVER_HOME/*:$SERVER_HOME/extra/*" com.qloudfin.qloudbus.sdk.agent.QloudServiceAgent $1
