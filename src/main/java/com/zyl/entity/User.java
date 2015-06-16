package com.zyl.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;


@Entity //声明为实体
@Table(name="T_User")  //修改表名
public class  User implements Serializable{
	/**
	 * //在JPA中指定使用Hibernate的主键实现策略:uuid
	 */
	@Id  //标记为主键
	@GeneratedValue(generator="paymentableGenerator") 
	@GenericGenerator(name = "paymentableGenerator",strategy="uuid")
	private String id;
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
	
	private String company;
	
	private String company_address;
	
	private String fax;
	
	private String email;
	
	private String photo;
	
	@ManyToOne
	@JoinColumn(name="group_id")
	private Group group;
	
	private String QRphoto;
	
	// setter/getter
	
	public Boolean getEnabled() {
		if (enabled == null)
			return null;
		return enabled == 'Y' ? Boolean.TRUE : Boolean.FALSE;
	}
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompany_address() {
		return company_address;
	}

	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}

	public String getFax() {
		return fax;
	}

	@Column(name = "PHOTO", length = 200)
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "QRPHOTO", length = 200)
	public String getQRphoto() {
		return QRphoto;
	}

	public void setQRphoto(String qRphoto) {
		QRphoto = qRphoto;
	}

	public void setEnabled(Boolean enabled) {
		if (enabled == null) {
			this.enabled = null;
		} else {
			this.enabled = enabled == true ? 'Y' : 'N';
		}
	}
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
