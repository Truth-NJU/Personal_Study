# MySQL & ES

# mysql如何与es数据同步

[https://www.zhihu.com/question/47600589/answer/2843488695](https://www.zhihu.com/question/47600589/answer/2843488695)，有四种方式，数据订阅方式是最好的。

MySQL 通过 binlog 订阅实现主从同步，各路数据订阅框架比如 canal 就依据这个原理，将 client 组件伪装成从库，来实现数据订阅。

![Untitled](MySQL%20&%20ES%206ef53aa7dcfc491b8b1848955d2af7ae/Untitled.png)

**MySQL主备复制原理**

[https://camo.githubusercontent.com/38002224d5437594118230edcd6f38dd48c8f574d19bd099eb1d76967e71bd4a/687474703a2f2f646c2e69746579652e636f6d2f75706c6f61642f6174746163686d656e742f303038302f333038362f34363863316131342d653761642d333239302d396433642d3434616335303161373232372e6a7067](https://camo.githubusercontent.com/38002224d5437594118230edcd6f38dd48c8f574d19bd099eb1d76967e71bd4a/687474703a2f2f646c2e69746579652e636f6d2f75706c6f61642f6174746163686d656e742f303038302f333038362f34363863316131342d653761642d333239302d396433642d3434616335303161373232372e6a7067)

- MySQL master 将数据变更写入二进制日志( binary log, 其中记录叫做二进制日志事件binary log events，可以通过 show binlog events 进行查看)
- MySQL slave 将 master 的 binary log events 拷贝到它的中继日志(relay log)
- MySQL slave 重放 relay log 中事件，将数据变更反映它自己的数据

**canal 工作原理**

- canal 模拟 MySQL slave 的交互协议，伪装自己为 MySQL slave ，向 MySQL master 发送dump 协议
- MySQL master 收到 dump 请求，开始推送 binary log 给 slave (即 canal )
- canal 解析 binary log 对象(原始为 byte 流)

多张表数据聚合时，canal 的支持没那么好，所以还是得回查。这时候用 canal-adapter 就不合适了，需要自己实现 canal-client，监听和[聚合数据](https://www.zhihu.com/search?q=%E8%81%9A%E5%90%88%E6%95%B0%E6%8D%AE&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra=%7B%22sourceType%22%3A%22answer%22%2C%22sourceId%22%3A2843488695%7D)，写入 ES：

![Untitled](MySQL%20&%20ES%206ef53aa7dcfc491b8b1848955d2af7ae/Untitled%201.png)

canal的各个组件的用途各不相同：

- canal-server（canal-deploy）：可以直接监听MySQL的binlog，把自己伪装成MySQL的从库，只负责接收数据，并不做处理。
- canal-adapter：相当于canal的客户端，会从canal-server中获取数据，然后对数据进行同步，可以同步到Redis、Elasticsearch中去。
- canal-admin：为canal提供整体配置管理、节点运维等面向运维的功能，提供相对友好的WebUI操作界面，方便更多用户快速和安全的操作。

# Canal使用

[https://juejin.cn/post/7244407068097576997](https://juejin.cn/post/7244407068097576997)

[https://blog.csdn.net/m0_68681879/article/details/132832701](https://blog.csdn.net/m0_68681879/article/details/132832701)

## Canal如何将Mysql表和ES索引对应起来？

在实现EntryHandler接口时，会打上@CanalTable("document")注解，其中的参数就是需要监听的数据表的名称。所以mysql表和es索引（es中索引的概念就相当于数据库中的一张表）中的内容最好一一对应。

```java
@CanalTable("document")
@Component
public class DocumentHandler implements EntryHandler<Document> {
```

todo：

1. canal、es、mysql配置步骤，利用docker配置
2. 写下整个代码编写的流程