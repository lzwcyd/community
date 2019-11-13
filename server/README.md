# 快速开发文档

## 开发工具
* [jdk](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html):本说明提供的是java jdk8（一个经典的java版本）但是现实开发环境为OPENJDK-12;

* [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)【可选】：
> 同时安装Lombok插件，此插件是为了自动生成`get`和`set`方法

* [Node JS](https://nodejs.org/en/)
>请下载最新 LTS 版本

* [maven](http://maven.apache.org/download.html):
> maven 需要以下配置： 
> 1. 当前系统是否配置JAVA_HOME的环境变量;
> 2. 下载[maven](http://maven.apache.org/download.html)，解压maven放在一个非中文无空格的路径下;
> 3. 配置maven的相关环境变量：一、在环境变量增加·M2_HOME·，路径是maven解压后的根目录；二、在环境变量里的`path`中增加maven/bin的目录
> 4. 配置阿里云镜像【可选】：在maven的根目录下`conf`文件夹下`settings.xml`文件，用任一文本编辑器打开，找到 ``<mirror>``
添加以下代码：
>```   
><mirrors>
>  <mirror>
>    <id>alimaven</id>
>    <mirrorOf>central</mirrorOf>
>    <name>aliyun maven</name>
>    <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
>   </mirror>
></mirrors>
>```                                                                     
* [MySQL 5.7](https://dev.mysql.com/downloads/mysql/5.7.html#downloads)