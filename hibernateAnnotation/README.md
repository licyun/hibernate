##使用hibernate的Annotaion 完成持久化映射
```java
  //表示该类是一个持久化类
  @Entity    
  //指明数据表
  @Table(name="person_table") 
  //映射普通属性
  @Column(name="person_email") 
  private String email;
  //标注符合类型的标注属性，不指定length长度默认是255
  @EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name="first"
			, column=@Column(name="person_first")),
		@AttributeOverride(name="last"
			, column=@Column(name="person_last" , length=20))
	}) 
  //标注组件属性
  @Embedded
	@AttributeOverrides({
		@AttributeOverride(name="name" 
			, column=@Column(name="cat_name" , length=35)),
		@AttributeOverride(name="color" 
			, column=@Column(name="cat_color"))
	})                 
  
```
