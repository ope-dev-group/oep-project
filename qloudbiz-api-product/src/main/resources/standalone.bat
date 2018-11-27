@echo off

if not defined SERVER_HOME goto set_server_home

:cont
if not defined JAVA_HOME goto no_java_home

set QLOUD_DISCOVERY_NAME=discovery
set QLOUD_DISCOVERY_HOST=192.168.211.101
set QLOUD_DISCOVERY_PORT=8888

set QLOUD_CONFIG_USED=false
set QLOUD_CONFIG_NAME=configuration
set QLOUD_CONFIG_NAMESPACE=
set QLOUD_CONFIG_ENVIROMENT=
set QLOUD_CONFIG_TOPIC=

set QLOUD_SECURITY_USED=false
set QLOUD_SECURITY_HOST=192.168.211.101
set QLOUD_SECURITY_PORT=8888

set JAVA_OPTS=-Xms512M -Xmx512M

"%JAVA_HOME%\bin\java" -Xms512M -Xmx512M -Dlog4j.configuration="file:%SERVER_HOME%\agent.properties" -cp "%SERVER_HOME%;%SERVER_HOME%\classes;%SERVER_HOME%\lib\*;%SERVER_HOME%\*;%SERVER_HOME%\extra\*" com.qloudfin.qloudbus.sdk.agent.QloudServiceAgent %1

goto end

:no_java_home
echo ERROR: Set JAVA_HOME to the path where the J2SE 1.8 is installed
goto end 

:set_server_home
set SERVER_HOME=%~dp0
goto cont

:end
