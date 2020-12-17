package boundaries;

import dts.data.UserRole;
import models.users.UserId;

public class UserBoundary {
	private UserId userId;
	private UserRole role;
	private String username;
	private String avatar;

	public UserBoundary() {
		// default arguments
		this.userId = new UserId();
		this.role = UserRole.PLAYER;
		this.username = "Demo User";
		this.avatar = "ooOO_()_OOoo";
	}

	public UserId getUserId() {
		return userId;
	}

	public void setUserId(UserId userId) {
		this.userId = userId;
	}

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
