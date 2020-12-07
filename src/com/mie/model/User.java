package com.mie.model;

import java.util.*;

public class User {
	/**
	 * This class contains all of the relevant information, and getter/setter
	 * methods for the User object. 
	 */
	private String username;
	private String email;
	private String password;
	private String name;
	private String bio;
	private String profilePic;
	
	private List<String> following;
	private List<String> followers;
	private List<String> tagFollow;
	private boolean valid;

    
	public List<String> getFollowing() {
		List<String> followingClone = new ArrayList<String>(following);
		return followingClone;
	}

	public void setFollowing(List<String> following) {
		this.following = following;
	}
	
	
	public List<String> getFollowers() {
		List<String> followersClone = new ArrayList<String>(followers);
		return followersClone;
	}
	
	public void setFollowers(List<String> followers) {
		this.followers = followers;
	}
	
	
	public List<String> getTagFollow() {
		List<String> tagFollowClone = new ArrayList<String>(tagFollow);
		return tagFollowClone;
	}

	public void setTagFollow(List<String> tagFollow) {
		this.tagFollow = tagFollow;
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
