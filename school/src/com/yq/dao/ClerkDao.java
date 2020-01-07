package com.yq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.yq.model.Clerk;
import com.yq.util.JdbcUtil;
import com.yq.util.RowMapper;

public class ClerkDao {

	public int insertClerk(Clerk clerk) {
		return JdbcUtil.update("insert into Clerk(name,job,salary) values(?,?,?)", clerk.getName(),
				clerk.getJob(), clerk.getSalary());
	}

	public int updateClerk(Clerk clerk) {
		return JdbcUtil.update("update Clerk set name=?,job=?,salary=? where id=?", clerk.getName(),
				clerk.getJob(),clerk.getSalary(), clerk.getId());
	}

	public int deleteClerk(int id) {
		return JdbcUtil.update("delete from Clerk where id=?", id);
	}

	public Clerk findClerkById(int id) {
		return JdbcUtil.findObject("select * from Clerk id=?", new ClerkRow(), id);
	}

	public List<Clerk> findAll() {
		return JdbcUtil.findList("select * from Clerk", new ClerkRow());
	}

}

class ClerkRow implements RowMapper<Clerk> {
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