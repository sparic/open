package cn.muye.user.domain;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Project Name : devCenter
 * User: Jelynn
 * Date: 2017/5/11
 * Time: 11:48
 * Describe:
 * Version:1.0
 */
public class LoginInfo implements Serializable{

	private String userName;
	private int type;
	private String md5;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}
}
