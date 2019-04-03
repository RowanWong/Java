package cn.im.domain;

/**
 * 用户对象
 * @author SYSTEM
 *
 */
public class User {
	
	private String userId;
	private String userName;
	private int age;
	private String email;
	private String phoneNo;
	
	public User(){}
	
	public User(String userId, String userName, int age, String email, String phoneNo) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.age = age;
		this.email = email;
		this.phoneNo = phoneNo;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	
}
