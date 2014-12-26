package com.zyl.demo.ss1.entity;
/**
 * http userSession
 */
public class SessionInfo  implements java.io.Serializable{
	private User user;
	//还可以做记录IP ,登录情况, 状态等事情.

	//setter/getter
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
