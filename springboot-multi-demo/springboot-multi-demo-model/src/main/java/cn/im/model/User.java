package cn.im.model;

public class User {
	private String id;
	private String name;
	private String phoneNo;
	
	public User(){}
	
	public User(String id, String name, String phoneNo) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNo = phoneNo;
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
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", phoneNo=" + phoneNo + "]";
	}
	
	
}
