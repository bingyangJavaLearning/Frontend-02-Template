package com.traincamp.homework02;

public class UserService {
	
	private Integer id;
	
	private String name;
	
	public UserService() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserService [id=" + id + ", name=" + name + "]";
	}

	
	
}
