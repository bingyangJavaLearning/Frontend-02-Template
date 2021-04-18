package com.traincamp.spring.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchoolAutoConfiguration {
	
	@Bean
	public Student student() {
		Student student = new Student();
		student.setId(1);
		student.setName("学生1");
		return student;
	}
	
	@Bean
	public Klass klass() {
		Klass klass = new Klass();
		klass.setName("班级1");
		klass.setStudent(student());
		return klass;
	}

	@Bean
	public School school() {
		School school = new School();
		school.setName("学校1");
		school.setKlass(klass());
		return school;
	}
}
