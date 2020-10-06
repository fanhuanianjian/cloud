package cn.com.yuuuu.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bhl
 */
@Data
public class Student implements Serializable {

	public Student() {}

	public Student(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	private int id;
	
	private String name;
	
	private int age;

}
