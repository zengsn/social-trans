# Social Translation 

## src - 源代码

## bin - 可部署文件

## doc - 项目文档


1、下载MyEclipse 2015/2014

可以使用MyEclipse 2014 破解工具

2、下载JDK 1.8.0_121

JDK下载地址：http://www.oracle.com/technetwork/java/javase/downloads/index.html

新建变量名：JAVA_HOME   变量值：C:\Program Files\Java\jdk1.8.0_121（这里是刚刚安装JDK的路径）
新建变量名：CLASSPATH 变量值： .;%JAVA_HOME%\lib;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar
编辑变量名：Path  在后面加上 ;%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin

3、下载Tomcat 8(可使用myeclipse自带服务器)

Tomcat下载：http://tomcat.apache.org/
安装：https://jingyan.baidu.com/article/4853e1e57e61711909f726ec.html
MyEclipse配置Tomcat 
如何配置2014：首先进入Window—>Preference->myclipse->server->tomcat->configeTomcat(根据自己的版本选择)—>选择路径—>设置为disable->apply;
2015:首先进入Window—>Preference->myclipse->server->runtime enviroment->add;

4、下载MAVEN

MAVEN下载：http://maven.apache.org/
下载Maven包并且解压，放到指定路径
配置环境变量 （配置Maven与配置JDK环境变量相同）
新建变量名：MAVEN_HOME  变量值：D:\maven\apache-maven-3.2.1（这是我的MAVEN路径）
编辑变量名：Path  在最前面加上：;%MAVEN_HOME%\bin;（注意，最后要有个";"作为分隔符）

如何配置Maven；首先进入Window—>Preference->myeclipse->maven4Myeclipse->Installations->add->自己安装的maven
配置xml文件：首先进入Window—>Preference->myeclipse->maven4Myeclipse->user settings ->自己安装的配置文件（conf下的settings.xml）


5、项目导入 file ->import->选择项目;
	右键项目->maven4Myeclipse->update project;
