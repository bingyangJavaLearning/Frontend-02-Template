package com.traincamp.homework02;

import org.springframework.stereotype.Controller;

@Controller(value="controllerBean")
public class ControllerBean {
	
	public ControllerBean() {
	}
	
	private String name = "controller";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ControllerBean [name=" + name + "]";
	}
	
	
	
}
