1. mvn clean package 
2. 在本机上创建~/.sandbox-module/目录
3. cp target/jvm-sandbox-example-1.0-SNAPSHOT-jar-with-dependencies.jar ~/.sandbox-module/
4. 从 https://ompc.oss.aliyuncs.com/jvm-sandbox/release/sandbox-stable-bin.zip 中下载jvm-sandbox
5. unzip sandbox-stable-bin.zip 
6. cd sandbox 
7. ./install-local.sh -p ~/opt，sandbox会安装到 ~/opt/sandbox 文件夹中
8. 运行要进行字节码增强的程序
9. 进入 ~/opt/sandbox/bin 目录
10. ./sandbox.sh -p 41280 启动沙箱，其中41280替换为运行的程序的pid号，可以用ps -ef|grep java命令查询对应的pid号
11. ./sandbox.sh -p 41280 -l 查询沙箱挂载了的模块
12. ./sandbox.sh -p 41280 -d 'base-aop/aop'，触发BaseModule#baseAop方法运行，进行字节码增强

参考：
1. https://github.com/alibaba/jvm-sandbox/wiki/MDEV-FIRST-MODULE
2. https://juejin.cn/column/7099445292600131614
3. https://xie.infoq.cn/article/c5be9834709f7eb48cfa683b1