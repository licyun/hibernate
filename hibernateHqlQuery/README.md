# hibernateHqlQuery
##hibernate使用Hql来进行查询
###1.调用session对象的creqteQuery方法创建查询对象
###2.如果hql语句包含参数，则调用query的setXXX()方法动态赋值。
###3.调用Query对象的list等方法返回查询结果列表
```java
//获得Hibernate Session
		Session sess = HibernateUtil.currentSession();
		//开始事务
		Transaction tx = sess.beginTransaction();
		//以HQL语句创建Query对象.
		//执行setString方法为HQL语句的参数赋值
		//Query调用list方法访问查询的全部实例
		List pl = sess.createQuery("select distinct p from Person p "
			+ "join p.myEvents where title = :eventTitle")
			.setString("eventTitle","很普通的事情")
			.list();
		//遍历查询的全部结果
		for (Iterator pit = pl.iterator() ; pit.hasNext(); )
		{
			Person p = (Person)pit.next();
			System.out.println(p.getName());
		}
		//提交事务
		tx.commit();
		HibernateUtil.closeSession();
```
