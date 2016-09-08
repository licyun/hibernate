#使用hibernate及连接池操作数据库
##hibernate说明：由于java语言是面向对象的，而mysql是关系型数据库。无法用面向对象的方法来操作mysql数据库，所以引入hibernate这一中间插件，程序员无需知道底层数据库是什么以及如何操作，只需用面向对象的方法操作hibernate的持久化类，hibernate自动将这些方法转化为数据库的操作。
###1.引入连接池包在hibernate.cfg.xml中配置数据库连接信息
```xml
  <hibernate-configuration>
	<session-factory>
		<!-- 指定连接数据库所用的驱动 -->
		<property name="connection.driver_class">com.mysql.jdbc.Driver</property>
		<!-- 指定连接数据库的url，hibernate连接的数据库名 -->
		<property name="connection.url">jdbc:mysql://localhost/hibernate</property>
		<!-- 指定连接数据库的用户名 -->
		<property name="connection.username">root</property>
		<!-- 指定连接数据库的密码 -->
		<property name="connection.password">123456</property>
		<!-- 指定连接池里最大连接数 -->
		<property name="hibernate.c3p0.max_size">20</property>
		<!-- 指定连接池里最小连接数 -->
		<property name="hibernate.c3p0.min_size">1</property>
		<!-- 指定连接池里连接的超时时长 -->
		<property name="hibernate.c3p0.timeout">5000</property>
		<!-- 指定连接池里最大缓存多少个Statement对象 -->
		<property name="hibernate.c3p0.max_statements">100</property>
		<property name="hibernate.c3p0.idle_test_period">3000</property>
		<property name="hibernate.c3p0.acquire_increment">2</property>
		<property name="hibernate.c3p0.validate">true</property>
		<!-- 指定数据库方言 -->
		<property name="dialect">org.hibernate.dialect.MySQLInnoDBDialect</property>
		<!-- 根据需要自动创建数据表 -->
		<property name="hbm2ddl.auto">update</property>
		<!-- 显示Hibernate持久化操作所生成的SQL -->
		<property name="show_sql">true</property>
		<!-- 将SQL脚本进行格式化后再输出 -->
		<property name="hibernate.format_sql">true</property>
		<!-- 罗列所有的映射文件 -->
		<mapping resource="org/crazyit/app/domain/News.hbm.xml"/>
	</session-factory>
</hibernate-configuration>
```
###2.建立一个类及该类对应的xml持久化映射News.hbm.xml
```xml
  <hibernate-mapping package="org.crazyit.app.domain">
	<!-- 每个class元素对应一个持久化对象 -->
	<class name="News" table="news_table">
		<!-- id元素定义持久化类的标识属性 -->
		<id name="id">
			<!-- 指定主键生成策略 -->
			<generator class="identity"/>
		</id>
		<!-- property元素定义常规属性 -->
		<property name="title"/>
		<property name="content"/>
	</class>
</hibernate-mapping>
```
###3.java中Configuration调用hibernate方法
```java
  //实例化Configuration，
		Configuration conf = new Configuration()
		//下面方法默认加载hibernate.cfg.xml文件
			.configure();
		//以Configuration创建SessionFactory
		SessionFactory sf = conf.buildSessionFactory();
		//创建Session
		Session sess = sf.openSession();
		//开始事务
		Transaction tx = sess.beginTransaction();
		//创建消息实例
		News n = new News();
		//设置消息标题和消息内容
		n.setTitle("疯狂Java联盟成立了");
		n.setContent("疯狂Java联盟成立了，"
			+ "网站地址http://www.crazyit.org");
		//保存消息
		sess.save(n);
		//提交事务
		tx.commit();
		//关闭Session
		sess.close();
		sf.close();
```
