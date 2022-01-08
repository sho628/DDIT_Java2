package kr.or.ddit.reflection;

import java.io.Serializable;

import kr.or.ddit.basic.PrintAnnotation;

public class SampleVO implements Serializable, Comparable<String> {

	
	public String id;		// 아이디
	protected String name;	// 이름
	private int age;		// 나이
	
	// 생성자1
	public SampleVO() {
		
	}
	
	// 생성자2
	public SampleVO(String id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}
	@PrintAnnotation
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	

	@Override
	public String toString() {
		return "SampleVO [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

	@Override
	public int compareTo(String o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
