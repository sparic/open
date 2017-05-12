package cn.muye.user.domain;

/**
 * Created with IntelliJ IDEA.
 * Project Name : devCenter
 * User: Jelynn
 * Date: 2017/5/10
 * Time: 10:16
 * Describe:
 * Version:1.0
 */
public enum UserType {

	SUPER_ADMIN("超级管理员",1),ADMIN("管理员", 2),CUSTOMER("普通用户",3);

	private String name;
	private int value;

	private UserType(String name, int value){
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
