package main.models.operation;

public class UserId {
	
	private String space;
	private String email;
	
	public UserId() {
		space="2021a.demo";
		email="demo@maildomain.com";
	}
	public UserId(String space, String email) {
		this.space=space;
		this.email=email;
	}
	public String getSpace() {
		return space;
	}
	public void setSpace(String space) {
		this.space = space;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
