# 1. 安装tomcat
参考：https://www.bandwagonhost.net/12513.html
由于jdk8与tomcat-9.0.70兼容，所以选择tomcat-9.0.70（需要考虑版本兼容性问题）
以下命令均以root用户执行

1. `wget https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.70/bin/apache-tomcat-9.0.70.tar.gz`
2. 解压下载的安装包：`tar xzvf apache-tomcat-9.0.70.tar.gz`
3. 创建安装目录：`mkdir /opt/tomcat/`
4. 将解压的文件移动到安装目录：`mv apache-tomcat-9.0.70/* /opt/tomcat/`
5. 修改安装目录的所有权限：`chown -R www-data:www-data /opt/tomcat/`
6. 修改安装目录的访问权限：`chmod -R 755 /opt/tomcat/`
7. 编辑 conf/tomcat-users.xml 文件以配置 Apache Tomcat 的管理员和管理帐户。
   1. `vi /opt/tomcat/conf/tomcat-users.xml`
   2. 在 <tomcat-users> 标记中添加以下代码。通过使用高安全密码更改下面的值 StrongPassword 来更改管理员和经理访问的密码。
```bash
<!-- user manager can access only manager section -->
<role rolename="manager-gui" />
<user username="manager" password="StrongPassword" roles="manager-gui" />

<!-- user admin can access manager and admin section both -->
<role rolename="admin-gui" />
<user username="admin" password="StrongPassword" roles="manager-gui,admin-gui" />
```

8. 通过编辑管理器和主机管理器配置文件启用对 Apache Tomcat 的远程访问。编辑管理器应用程序 context.xml 文件：`vi /opt/tomcat/webapps/manager/META-INF/context.xml`
   1. 注释掉 IP 地址部分，如下所示。然后，保存并关闭文件。
```bash
<!-- <Valve className="org.apache.catalina.valves.RemoteAddrValve"          
	allow="127\.\d+\.\d+\.\d+|::1|0:0:0:0:0:0:0:1" /> -->
```

9. 编辑主机管理器应用程序 context.xml 文件：`vi /opt/tomcat/webapps/host-manager/META-INF/context.xml`
   1. 注释掉 IP 地址部分，如下所示。然后，保存并关闭文件。
```bash
<!-- <Valve className="org.apache.catalina.valves.RemoteAddrValve"          
	allow="127\.\d+\.\d+\.\d+|::1|0:0:0:0:0:0:0:1" /> -->
```

10. 修改`/opt/tomcat/conf/server.xml`中的端口:
   1. 以下是一个实例，需要修改两处地方，两个地方的端口需要不一样，在该实例中，tomcat会启动在8088端口
```bash
1. <Server port="9090" shutdown="SHUTDOWN">
2. <Connector port="8088" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" />
```

11. 进入`/opt/tomcat/bin`目录，执行`./shutdown.sh`、`./startup.sh`启动tomcat
12. 可以在`ip地址:8088`访问到tomcat页面
# 2. 通过访问URL直接下载服务器资源文件（Tomcat配置）
参考：https://codeantenna.com/a/zpfCQYnIW6

1. 修改`/opt/tomcat/conf/server.xml`文件，找到<Host/>标签，新增下面这行代码：`<Context path="/source" docBase="/root/klein/data" debug="0" privileged="true"/>`（其中项目路径path=“/source”，绝对路径docBase=“/root/klein/data”，资源文件存放在服务器的/root/klein/data文件夹中)
2. 修改好后保存，最后重启Tomcat，就可通过访问URL直接下载文件。举个例子，比如我要下载服务器data文件中的1.jpg，如果按上面的代码配置，则访问的地址应为：`ip地址:8088/source/1.jpg`
# 3. tomcat跨域
在vue项目中使用vue-pdf组件去展示服务器上的pdf时，遇到了tomcat的跨域问题。解决方法如下：

1. tomcat下的conf->web.xml，在该文件<web-app>标签下添加如下代码：
```bash
<filter>
<filter-name>CorsFilter</filter-name>
<filter-class>org.apache.catalina.filters.CorsFilter</filter-class>
<init-param>
<param-name>cors.allowed.origins</param-name>
<param-value>*</param-value>
</init-param>
</filter>
<filter-mapping>
<filter-name>CorsFilter</filter-name>
<url-pattern>/*</url-pattern>
</filter-mapping>
```

2. 进入`/opt/tomcat/bin`目录，执行`./shutdown.sh`、`./startup.sh`重新启动tomcat
