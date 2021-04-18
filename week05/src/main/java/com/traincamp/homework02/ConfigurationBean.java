package com.traincamp.homework02;


public class ConfigurationBean {
	
	public ConfigurationBean() {
	}

	private String name = "configBean";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ConfigurationBean [name=" + name + "]";
	}
	
	
	
}
