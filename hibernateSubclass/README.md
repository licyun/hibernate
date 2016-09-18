# hibernateSubclass
###hibernate采用subclass元素的继承映射
###person类的各个子类关联关系如下
![image](https://github.com/mzkwy/hibernateSubclass/raw/master/personUML.jpg)
###person.hbm.xml映射规则如下
```xml
<hibernate-mapping package="org.crazyit.app.domain">
	<!-- 映射Person类 -->
	<class name="Person" table="person_inf" discriminator-value="普通人">
		<!-- 映射标识属性 -->
		<id name="id" column="person_id">
			<!-- 使用identity的主键生成器策略 -->
			<generator class="identity"/>
		</id>
		<!-- 映射辨别者列 -->
		<discriminator column="wawa" type="string"/>
		<!-- 如下映射两个基本属性 -->
		<property name="name" length="80"/>
		<property name="gender"/>
	   	<!-- 下面映射了一个组件属性 -->
		<component name="address">
			<!-- 映射组件属性的三个成员属性 -->
			<property name="detail"/>
			<property name="zip"/>
			<property name="country"/>
		</component>
		<!-- 使用subclass元素映射Person类的子类Employee -->
		<subclass name="Employee" discriminator-value="雇员">
			<!-- 映射两个基本属性 -->
			<property name="title" />
			<property name="salary" />
			<!-- 映射N－1的关联映射 -->
			<many-to-one name="manager" column="manager_id"/>
			<!-- 映射与Customer类之间的1－N关联 -->
			<set name="customers" inverse="true">
				<key column="empoyee_id"/>
				<one-to-many class="Customer"/>
			</set>
			<!-- 使用subclass元素映射Employee类的子类Manager -->
			<subclass name="Manager" discriminator-value="经理">
				<!-- 映射Manager类的基本属性department -->
				<property name="department"/>
				<!-- 映射Manager类的关联实体：Employee -->
				<set name="employees" inverse="true">
					<key column="manager_id"/>
					<one-to-many class="Employee"/>
				</set>
			</subclass>
		</subclass>
		<!-- 使用subclass映射Person的Customer子类 -->
		<subclass name="Customer" discriminator-value="顾客">
			<!-- 映射Customer类的comments属性 -->
			<property name="comments"/>
			<!-- 映射Customer和Employee的关联关系 -->
			<many-to-one name="employee" column="empoyee_id"/>
		</subclass>
	</class>
</hibernate-mapping>
```
