##hibernate 一对一  一对多  多对一  多对多的映射规则
###单向一对一
```xml
<!--Person.hbm.xml-->
<hibernate-mapping package="org.crazyit.app.domain">
	<!-- 映射Person持久化类-->
	<class name="Person" table="person_inf">
		<!-- 映射标识属性 -->
		<id name="id" column="person_id">
			<!-- 定义主键生成器策略 -->
			<generator class="identity"/>
		</id>
		<!-- 用于映射普通属性 -->
		<property name="name" type="string"/>
		<property name="age" type="int"/>
		<!-- 用于映射1-1关联实体，指定关联实体类为Address
		指定外键列名为address_id,并指定级联全部操作 -->
		<many-to-one name="address" cascade="all"
			unique="true" class="Address" column="address_id"/>
	</class>
</hibernate-mapping>

<!--映射主键-->
<generator class="foreign">
	<!-- 指定引用关联实体的属性名 -->
	<param name="property">address</param>
</generator>
<!-- 下面映射基于主键的1－1关联 -->
<one-to-one name="address"/>
```

###单向一对多
```xml
<!--Person.hbm.xml-->
<hibernate-mapping package="org.crazyit.app.domain">
	<!-- 映射Person持久化类-->
	<class name="Person" table="person_inf">
		<!-- 映射标识属性id -->
		<id name="id" column="person_id">
			<!-- 定义主键生成器策略 -->
			<generator class="identity"/>
		</id>
		<!-- 用于映射普通属性 -->
		<property name="name" type="string"/>
		<property name="age" type="int"/>
		<!-- 映射集合属性，集合元素是其他持久化实体
			没有指定cascade属性 -->
		<set name="addresses">
			<!-- 指定关联的外键列 -->
			<key column="person_id"/>
			<!-- 用以映射到关联类属性 -->
			<one-to-many class="Address"/>
		</set>
	</class>
</hibernate-mapping>
```

###单向多对一
```xml
<!--Person.hmb.xml-->
<hibernate-mapping package="org.crazyit.app.domain">
	<!-- 映射Person持久化类-->
	<class name="Person" table="person_inf">
		<!-- 映射标识属性 -->
		<id name="id" column="person_id">
			<!-- 定义主键生成器策略 -->
			<generator class="identity"/>
		</id>
		<!-- 用于映射普通属性 -->
		<property name="name" type="string"/>
		<property name="age" type="int"/>
		<!-- 用于映射N-1关联实体，指定关联实体类为Address
		指定外键列名为address_id,并指定级联全部操作 -->
		<many-to-one name="address" cascade="create"
			class="Address" column="address_id"/>
	</class>
</hibernate-mapping>
```

###单向多对多
```xml
<!--Person.hbm.xml>
<hibernate-mapping package="org.crazyit.app.domain">
	<!-- 映射Person持久化类-->
	<class name="Person" table="person_inf">
		<!-- 映射标识属性id -->
		<id name="id" column="person_id">
			<!-- 定义主键生成器策略 -->
			<generator class="identity"/>
		</id>
		<!-- 用于映射普通属性 -->
		<property name="name" type="string"/>
		<property name="age" type="int"/>
		<!-- 映射集合属性，集合元素是其他持久化实体
			指定连接表的表名-->
		<set name="addresses" table="person_address">
			<!-- 指定连接表中参照本表记录的外键列名 -->
			<key column="person_id" />
			<!-- 使用many-to-many来映射N－N关联 -->
			<many-to-many class="Address" column="address_id"/>
		</set>
	</class>
</hibernate-mapping>
```

###双向一对一
```xml
<!--Person.hbm.xml -->
<hibernate-mapping package="org.crazyit.app.domain">
	<!-- 映射Person持久化类-->
	<class name="Person" table="person_inf">
		<!-- 映射标识属性id -->
		<id name="id" column="person_id">
			<!-- 定义主键生成器策略 -->
			<generator class="identity"/>
		</id>
		<!-- 用于映射普通属性 -->
		<property name="name" type="string"/>
		<property name="age" type="int"/>
		<!-- one-to-one元素映射关联属性，外键列在对方的表内
			property-ref指定引用关联类的属性。
			即：在address属性所属的Address类内，
			必须有person属性的setter和getter方法 -->
		<one-to-one name="address" property-ref="person"/>
	</class>
</hibernate-mapping>
<!--映射主键改为-->
<one-to-one name="address"/>

<!--Address.hbm.xml-->
<hibernate-mapping package="org.crazyit.app.domain">
	<!-- Address持久化类-->
	<class name="Address" table="address_inf">
		<!-- 映射标识属性addressId -->
		<id name="addressId" column="address_id">
			<!-- 指定主键生成器策略 -->
			<generator class="identity"/>
		</id>
		<!-- 映射普通属性addressdetail -->
		<property name="addressDetail"/>
		<!-- 使用many-to-one映射1－1关联实体 
			unique="true"确定为1－1-->
		<many-to-one name="person" unique="true"
			column="person_id" not-null="true"/>
	</class>
</hibernate-mapping>
<!--主键映射则需改为-->
<generator class="foreign">
	<!-- 指定该主键值将根据person属性引用的
		关联实体的主键来生成-->
	<param name="property">person</param>
</generator>
<one-to-one name="person"/>
```

###双向一对多
```xml
<!--Person.hbm.xml-->
<hibernate-mapping package="org.crazyit.app.domain">
	<!-- 映射Person持久化类-->
	<class name="Person" table="person_inf">
		<!-- 映射标识属性id -->
		<id name="id" column="person_id">
			<!-- 定义主键生成器策略 -->
			<generator class="identity"/>
		</id>
		<!-- 用于映射普通属性 -->
		<property name="name" type="string"/>
		<property name="age" type="int"/>
		<!-- 映射集合属性，集合元素是其他持久化实体
			没有指定cascade属性，指定不控制关联关系 -->
		<set name="addresses" inverse="true">
			<!-- 指定关联的外键列 -->
			<key column="person_id"/>
			<!-- 用以映射到关联类属性 -->
			<one-to-many class="Address"/>
		</set>
	</class>
</hibernate-mapping>

<!--Address.hbm.xml-->
<hibernate-mapping package="org.crazyit.app.domain">
	<!-- Address持久化类-->
	<class name="Address" table="address_inf">
		<!-- 映射标识属性addressId -->
		<id name="addressId" column="address_id">
			<!-- 指定主键生成器策略 -->
			<generator class="identity"/>
		</id>
		<!-- 映射普通属性addressdetail -->
		<property name="addressDetail"/>
		<!-- 必须指定列名为person_id,
			与关联实体中key元素的column属性值相同 -->
		<many-to-one name="person" class="Person"
			column="person_id" not-null="true"/>
	</class>
</hibernate-mapping>
```

###双向多对多
```xml
<!--Person.hbm.xml-->
<hibernate-mapping package="org.crazyit.app.domain">
	<!-- 映射Person持久化类-->
	<class name="Person" table="person_inf">
		<!-- 映射标识属性id -->
		<id name="id" column="person_id">
			<!-- 定义主键生成器策略 -->
			<generator class="identity"/>
		</id>
		<!-- 用于映射普通属性 -->
		<property name="name" type="string"/>
		<property name="age" type="int"/>
		<!-- 映射N-N关联实体，两边的table属性值要相同 -->
		<set name="addresses" table="person_address">
			<!-- 指定关联的外键列 -->
			<key column="person_id"/>
			<!-- 用以映射到关联类属性 -->
			<many-to-many class="Address" column="address_id"/>
		</set>
	</class>
</hibernate-mapping>

<!--Address.hbm.xml-->
<hibernate-mapping package="org.crazyit.app.domain">
	<!-- Address持久化类-->
	<class name="Address" table="address_inf">
		<!-- 映射标识属性addressId -->
		<id name="addressId" column="address_id">
			<!-- 指定主键生成器策略 -->
			<generator class="identity"/>
		</id>
		<!-- 映射普通属性addressdetail -->
		<property name="addressDetail"/>
		<!-- 映射N-N关联实体，两边的table属性值要相同 -->
		<set name="persons" table="person_address">
			<!-- 指定关联的外键列 -->
			<key column="address_id"/>
			<!-- 用以映射到关联类属性 -->
			<many-to-many class="Address"
				column="person_id"/>
		</set>
	</class>
</hibernate-mapping>
```
