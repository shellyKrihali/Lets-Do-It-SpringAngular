package dts.data;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

//import javax.persistence.Entity;
//import javax.persistence.Table;
//import javax.persistence.Id;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

//@Entity
//@Table(name = "USERS")
@Document
public class UserEntity {

	private String userId;
	private UserRole role;
	private String username;
	private String avatar;

	public UserEntity() {

	}

	public UserEntity(String userId, UserRole role, String username, String avatar) {
		super();
		this.userId = userId;
		this.role = role;
		this.username = username;
		this.avatar = avatar;
	}

	@Id
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Enumerated(EnumType.STRING)
	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
