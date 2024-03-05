# Mybatis

## mybatis的优缺点

优点:

1. 基于 SQL 语句编程，相当灵活，不会对应用程序或者数据库的现有设计造成任何影响，SQL 写在 XML 里，解除 sql 与程序代码的耦合，便于统一管理;提供 XML 标签， 支持编写动态 SQL 语句， 并 可重用。
2. 与 JDBC 相比，减少了 50%以上的代码量，消除了 JDBC 大量冗余的代码，不需要手动开关连接; 
3. 很好的与各种数据库兼容( 因为 MyBatis 使用 JDBC 来连接数据库，所以只要JDBC 支持的数据库MyBatis 都支持)。
4. 能够与 Spring 很好的集成;
5. 提供映射标签， 支持对象与数据库的 ORM 字段关系映射; 提供对象关系映射标签， 支持对象关 系组件维护。

缺点:

1. SQL 语句的编写工作量较大， 尤其当字段多、关联表多时， 对开发人员编写SQL 语句的功底有一 定要求。
2. SQL 语句依赖于数据库， 导致数据库移植性差， 不能随意更换数据库。



## MyBatis 与Hibernate 有哪些不同? 

Hibernate使用ORM（对象关系映射）框架，可以通过操作java bean来直接操作数据库，而MyBatis使用SQL语句来操作数据库。

SQL 和 ORM 的争论，永远都不会终止

**开发速度的对比:** 
Hibernate的真正掌握要比Mybatis难些。Mybatis框架相对简单很容易上手，但也相对简陋些。 比起两者的开发速度，不仅仅要考虑到两者的特性及性能，更要根据项目需求去考虑究竟哪一个更适合 项目开发，比如:一个项目中用到的复杂查询基本没有，就是简单的增删改查，这样选择hibernate效 率就很快了，因为基本的sql语句已经被封装好了，根本不需要你去写sql语句，这就节省了大量的时 间，但是对于一个大型项目，复杂语句较多，这样再去选择hibernate就不是一个太好的选择，选择 mybatis就会加快许多，而且语句的管理也比较方便。



**开发工作量的对比:** 
Hibernate和MyBatis都有相应的代码生成工具。可以生成简单基本的DAO层方法。针对高级查询， Mybatis需要手动编写SQL语句，以及ResultMap。而Hibernate有良好的映射机制，开发者无需关心 SQL的生成与结果映射，可以更专注于业务流程



**sql优化方面:**
Hibernate的查询会将表中的所有字段查询出来，这一点会有性能消耗。Hibernate也可以自己写SQL来 指定需要查询的字段，但这样就破坏了Hibernate开发的简洁性。而Mybatis的SQL是手动编写的，所以 可以按需求指定查询的字段。
 Hibernate HQL语句的调优需要将SQL打印出来，而Hibernate的SQL被很多人嫌弃因为太丑了。 MyBatis的SQL是自己手动写的所以调整方便。但Hibernate具有自己的日志统计。Mybatis本身不带日 志统计，使用Log4j进行日志记录。



**对象管理的对比:**
 Hibernate 是完整的对象/关系映射解决方案，它提供了对象状态管理(state management)的功能， 使开发者不再需要理会底层数据库系统的细节。也就是说，相对于常见的 JDBC/SQL 持久层方案中需要 管理 SQL 语句，Hibernate采用了更自然的面向对象的视角来持久化 Java 应用中的数据。 换句话说，使用 Hibernate 的开发者应该总是关注对象的状态(state)，不必考虑 SQL 语句的执行。 这部分细节已经由 Hibernate 掌管妥当，只有开发者在进行系统性能调优的时候才需要进行了解。而 MyBatis在这一块没有文档说明，用户需要对对象自己进行详细的管理。



**缓存机制对比:**
相同点:都可以实现自己的缓存或使用其他第三方缓存方案，创建适配器来完全覆盖缓存行为。
不同点:Hibernate的二级缓存配置在SessionFactory生成的配置文件中进行详细配置，然后再在具体 的表-对象映射中配置是哪种缓存。
MyBatis的二级缓存配置都是在每个具体的表-对象映射中进行详细配置，这样针对不同的表可以自定义 不同的缓存机制。并且Mybatis可以在命名空间中共享相同的缓存配置和实例，通过Cache-ref来实现。



**两者比较:**
因为Hibernate对查询对象有着良好的管理机制，用户无需关心SQL。所以在使用二级缓存 时如果出现脏数据，系统会报出错误并提示。
而MyBatis在这一方面，使用二级缓存时需要特别小心。如果不能完全确定数据更新操作的波及范围， 避免Cache的盲目使用。否则，脏数据的出现会给系统的正常运行带来很大的隐患。
Hibernate功能强大，数据库无关性好，O/R映射能力强，如果你对Hibernate相当精通，而且对 Hibernate进行了适当的封装，那么你的项目整个持久层代码会相当简单，需要写的代码很少，开发速 度很快，非常爽。
Hibernate的缺点就是学习门槛不低，要精通门槛更高，而且怎么设计O/R映射，在性能和对象模型之 间如何权衡取得平衡，以及怎样用好Hibernate方面需要你的经验和能力都很强才行。
MyBATIS入门简单，即学即用，提供了数据库查询的自动对象绑定功能，而且延续了很好的SQL使用经 验，对于没有那么高的对象模型要求的项目来说，相当完美。
MyBATIS的缺点就是框架还是比较简陋，功能尚有缺失，虽然简化了数据绑定代码，但是整个底层数据库 查询实际还是要自己写的，工作量也比较大，而且不太容易适应快速数据库修改。





## #{}和${}的区别是什么?

\#{}是预编译处理、是占位符， ${}是字符串替换、是拼接符。

Mybatis 在处理#{}时，会将 sql 中的#{}替换为?号，调用 PreparedStatement 来赋值; 

Mybatis 在处理${}时， 就是把${}替换成变量的值，调用 Statement 来赋值;

#{} 的变量替换是在DBMS 中、变量替换后，#{} 对应的变量自动加上单引号

${} 的变量替换是在 DBMS 外、变量替换后，${} 对应的变量不会加上单引号 

使用#{}可以有效的防止 SQL 注入， 提高系统安全性。



## **简述** **Mybatis** 的插件运行原理，如何编写一个插件。

mybatis的插件指的就是mybatis的拦截器

答: Mybatis 只支持针对 ParameterHandler、ResultSetHandler、StatementHandler、Executor 这 4 种接口的插件， Mybatis 使用 JDK 的动态代理， 为需要拦截的接口生成代理对象以实现接口方法拦 截功能， 每当执行这 4 种接口对象的方法时，就会进入拦截方法，具体就是 InvocationHandler 的 invoke() 方法， 拦截那些你指定需要拦截的方法。

编写插件: 实现 Mybatis 的 Interceptor 接口并复写 intercept()方法， 然后在给插件编写注解， 指定 要拦截哪一个接口的哪些方法即可， 在配置文件中配置编写的插件。

```java
@Intercepts({@Signature(type = StatementHandler.class, method = "query", args =
{Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args =
{Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {
Statement.class })})
@Component
invocation.proceed()执行具体的业务逻辑
```

