package com.zyl.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;

/**
 * 用户组
 * @author Z10
 */
@Entity //声明为实体
@Table(name="T_Group")  //修改表名
public class Group {
	
	//配合前台对datatree的修改.支持平滑数据
	@Transient
	private String pid;// 虚拟属性，用于获得当前机构的父机构ID
	
	@Id  //标记为主键
	@GeneratedValue(generator="GroupGenerator") 
	@GenericGenerator(name = "GroupGenerator",strategy="uuid")//在JPA中使用Hibernate的uuid主键策略
	private String id;
	private String name;
	private String address;
	private String code;
	private String iconCls;
	
	@Column(nullable=false) //不可为空,类型为Time
	private Date updateDate;
	

	
	@ManyToOne
	@JoinColumn(name="fatherGroup_id")
	private Group fatherGroup;
	
	/**
	 *1控制关系的权限交给对方.一般都是配置在一的一方,将维护关系交给多的一端.mappedby值为多的一段中一对象的属性名.
	 * 
	 *2级联操作仅仅用来节省代码,一般可配置在一的一端.在操作一的一段时自动根据配置操作多的一端.
	 */
	@OneToMany(mappedBy = "fatherGroup", cascade = CascadeType.ALL)//级联操作
	private Set<Group> children;
	
	@OneToMany(mappedBy="group") //维护关系交给User
	private Set<User> users;

	
	
	
	//getter&&setter
	
	
	/**
	 * 用于业务逻辑的字段，注解@Transient代表不需要持久化到数据库中
	 * 获取父节点的id
	 * @return
	 */
	public String getPid() {
		if (fatherGroup != null && !StringUtils.isBlank(fatherGroup.getId())) {
			return fatherGroup.getId();
		}
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Group getFatherGroup() {
		return fatherGroup;
	}
	public void setFatherGroup(Group fatherGroup) {
		this.fatherGroup = fatherGroup;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Group> getChildren() {
		return children;
	}

	public void setChildren(Set<Group> children) {
		this.children = children;
	}
	
}
