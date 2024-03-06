1. mvn clean package 
2. 在本机上创建~/.sandbox-module/目录
3. cp target/jvm-sandbox-example-1.0-SNAPSHOT-jar-with-dependencies.jar ~/.sandbox-module/
4. 运行要进行字节码增强的程序
5. ./sandbox.sh -p 41280 挂载沙箱，其中41280替换为运行的程序的pid号，可以用ps -ef|grep java命令查询对应的pid号
6. ./sandbox.sh -p 41280 -l查询沙箱挂载了的模块
7. ./sandbox.sh -p 41280 -d 'base-aop/aop'，触发BaseModule#baseAop方法运行，进行字节码增强