package com.zyl.demo.ss1.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity //声明为实体
@Table(name="T_User")  //修改表名
public class User {
	@Id  //设置为主键
	@GeneratedValue  //设置主键增长方式默认为native
	private Long id;
	/**
	 * 登录名
	 */
	@Column(nullable=false)
	private String loginName;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 手机号码
	 */
	private String cellNO;
	
	/**
	 * password
	 */
	@Column(nullable=false)
	private String password;
	/**
	 * update时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false) //不可为空,类型为Time
	private Date updateDate;
	
	@Column(name = "SEX", length = 1)
	private String sex;
	/**
	 * 是否可用
	 * [JPA没有boolean类型,在get/set方法中修改. 持久导的实体属性使用Character类型，则领域层可直接使用boolean类型。]
	 */
	private Character enabled;
	
	// setter/getter
	
	public Boolean getEnabled() {
		if (enabled == null)
			return null;
		return enabled == 'Y' ? Boolean.TRUE : Boolean.FALSE;
	}

	public void setEnabled(Boolean enabled) {
		if (enabled == null) {
			this.enabled = null;
		} else {
			this.enabled = enabled == true ? 'Y' : 'N';
		}
	}
	
	public String getLoginName() {
		return loginName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCellNO() {
		return cellNO;
	}

	public void setCellNO(String cellNO) {
		this.cellNO = cellNO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
