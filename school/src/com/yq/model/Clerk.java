package com.yq.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.yq.util.RowMapper;

//实体类
public class Clerk {

	private Integer id;
	private String name;
	private String job;
	private double salary;
	//内部类实现rowmapper
	public static class ClerkMapper implements RowMapper<Clerk>{

		@Override
		public Clerk getRow(ResultSet rs) throws SQLException {
			Clerk c = new Clerk();
			c.setId(rs.getInt("id"));
			c.setName(rs.getString("name"));
			c.setJob(rs.getString("job"));
			c.setSalary(rs.getDouble("salary"));
			return c;
		}
		
	}

	public Clerk() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Clerk(Integer id, String name, String job, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.job = job;
		this.salary = salary;
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

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Clerk [id=" + id + ", name=" + name + ", job=" + job + ", salary=" + salary + "]";
	}

}
