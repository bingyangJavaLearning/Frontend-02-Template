package com.traincamp.homework02;

public class XmlBean {
	
	public XmlBean() {
	}

	private Integer id;
	
	private String name;

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
		return "XmlBean [id=" + id + ", name=" + name + "]";
	}
	
	
	
	
	
	
}
