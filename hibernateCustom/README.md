##hibernate映射组件属性
###当持久化类的属性非基本数据属性，而是用户自定义类时使用复合组件映射
###对应类的持久化映射xml如下,其中Name为自定义类,多层的复合组件映射规则可以对应嵌套。
```java
  <hibernate-mapping package="org.crazyit.app.domain">
    <class name="Person" table="person_inf">
    	<!-- 映射标识属性 -->
    	<id name="id" column="person_id">
    		<!-- 指定主键生成器策略 -->
    		<generator class="identity"/>
    	</id>
    	<!-- 映射普通属性 -->
    	<property name="age" type="int"/>
    	<!-- 映射组件属性name，组件属性的类型为Name -->
    	<component name="name" 
    		class="Name" unique="true">
    		<!-- 指定owner属性代表容器实体 -->
    		<parent name="owner"/>
    		<!-- 映射组件属性的first属性 -->
    		<property name="first"/>
    		<!-- 映射组件属性的last属性 -->
    		<property name="last"/>
    	</component>
    </class>
  </hibernate-mapping>
```
