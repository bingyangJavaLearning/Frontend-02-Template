package com.traincamp.spring.boot;

public class Klass {
	
	private String name;
	
	private Student student;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Klass [name=" + name + ", student=" + student + "]";
	}
	
	
	

}
