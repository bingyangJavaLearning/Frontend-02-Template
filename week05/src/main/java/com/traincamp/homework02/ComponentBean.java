package com.traincamp.homework02;

import org.springframework.stereotype.Component;

@Component
public class ComponentBean {

	public ComponentBean() {

	}
	
	private String name = "component";

	@Override
	public String toString() {
		return "ComponentBean [name=" + name + "]";
	}
	
	
	
}
