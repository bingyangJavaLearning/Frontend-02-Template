package com.traincamp.spring.boot;

public class School {
	
	@Override
	public String toString() {
		return "School [name=" + name + ", klass=" + klass + "]";
	}

	private String name;
	
	private Klass klass;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Klass getKlass() {
		return klass;
	}

	public void setKlass(Klass klass) {
		this.klass = klass;
	}
	
	

}
