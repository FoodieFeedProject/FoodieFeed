package com.mie.model;

import java.util.Date;
import java.util.List;

public class User {
	private String username;
	private String email;
	private String password;
	private String name;
	private String bio;
	private String profilePic;
	private List<String> following;
	private List<String> followed;
	private List<String> tagFollow;
	private boolean valid;

    
	public List<String> getFollowing() {
		return following;
	}

	public void addFollowing(String username) {
		this.following.add(username);
	}
	
	public void removeFollowing(String username) {
		this.following.remove(username);
	}
	
	public List<String> getFollowed() {
		return followed;
	}
	
	public void addFollowed(String username) {
		this.followed.add(username);
	}
	
	public void removeFollowed(String username) {
		this.followed.remove(username);
	}
	
	public List<String> getTagFollow() {
		return tagFollow;
	}

	public void addTagFollow(String tag) {
		this.tagFollow.add(tag);
	}
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public void setValid(boolean newValid) {
		valid = newValid;
	}
	
	public boolean isValid() {
		return valid;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email
				+ ", password=" + password + ", name=" + name + ", bio="
				+ bio + ", profilePic=" + profilePic + "]";
	}
}
