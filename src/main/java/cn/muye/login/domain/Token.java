package cn.muye.login.domain;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Project Name : devCenter
 * User: Jelynn
 * Date: 2017/5/5
 * Time: 17:12
 * Describe:
 * Version:1.0
 */
public class Token {

	private String tokenCode;

	private Date expireTime;

	private User user;

	public Token() {
	}

	public Token(String tokenCode, Date expireTime) {
		this.tokenCode = tokenCode;
		this.expireTime = expireTime;
	}

	public String getTokenCode() {
		return tokenCode;
	}

	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
