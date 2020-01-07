package com.yq.model;

//实体类 对应数据库表
public class Student {

	private Integer id;
	private String name;
	private String sex;
	private int age;
	private double score;

	public Student() {
		super();
	}

	public Student(Integer id, String name, String sex, int age, double score) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.score = score;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex + ", age=" + age + ", score=" + score + "]";
	}

}
