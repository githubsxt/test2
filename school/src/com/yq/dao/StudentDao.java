package com.yq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.yq.model.Student;
import com.yq.util.JdbcUtil;
import com.yq.util.RowMapper;

public class StudentDao {

	public int insertStudent(Student student) {
		return JdbcUtil.update("insert into student(name,sex,age,score) values(?,?,?,?)", student.getName(),
				student.getSex(), student.getAge(), student.getScore());
	}

	public int updateStudent(Student student) {
		return JdbcUtil.update("update student set name=?,sex=?,age=?,score=? where id=?", student.getName(),
				student.getSex(), student.getAge(), student.getScore(), student.getId());
	}

	public int deleteStudent(int id) {
		return JdbcUtil.update("delete from student where id=?", id);
	}

	public Student findStudentById(int id) {
		return JdbcUtil.findObject("select * from student where id=?", new StudentRow(), id);
	}

	public List<Student> findAll() {
		return JdbcUtil.findList("select * from student", new StudentRow());
	}

}

class StudentRow implements RowMapper<Student> {
	@Override
	public Student getRow(ResultSet rs) throws SQLException {
		Student s = new Student();
		s.setId(rs.getInt("id"));
		s.setName(rs.getString("name"));
		s.setSex(rs.getString("sex"));
		s.setAge(rs.getInt("age"));
		s.setScore(rs.getDouble("score"));
		return s;
	}
}