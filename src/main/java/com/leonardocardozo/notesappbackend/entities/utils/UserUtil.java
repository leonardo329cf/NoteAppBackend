package com.leonardocardozo.notesappbackend.entities.utils;

import java.io.Serializable;


public class UserUtil implements Serializable{
	private static final long serialVersionUID = 1L;


	private String username;
	
	private String name;
	private String picUrl;
	
	public UserUtil() {
		
	}
	public UserUtil(String username, String name, String picUrl) {
		super();
		this.username = username;
		this.name = name;
		this.picUrl = picUrl;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
}
