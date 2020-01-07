package com.yq.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class JdbcUtil {

	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/test";
	private static final String username = "root";
	private static final String password = "root";
	// 使用数据源访问数据库
	private static final DataSource ds;

	static {
		BasicDataSource source = new BasicDataSource();
		// 设置连接参数
		source.setDriverClassName(driver);
		source.setUrl(url);
		source.setUsername(username);
		source.setPassword(password);
		ds = source;
	}

	// 查询 返回一个对象集合
	public static <T> List<T> findList(String sql, RowMapper<T> mapper, Object... params) {
		// 获取连接
		Connection conn = null;
		PreparedStatement st = null;// 预编译对象
		ResultSet rs = null;
		List<T> list = new ArrayList<T>();
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			// 绑定参数
			setParams(st, params);
			// 执行
			rs = st.executeQuery();
			while (rs.next()) {
				list.add(mapper.getRow(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// 总是执行
			release(rs, st, conn);
		}
		return list;
	}

	// 查询 返回一个对象
	public static <T> T findObject(String sql, RowMapper<T> mapper, Object... params) {
		// 获取连接
		Connection conn = null;
		PreparedStatement st = null;// 预编译对象
		ResultSet rs = null;
		T t = null;
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			// 绑定参数
			setParams(st, params);
			// 执行
			rs = st.executeQuery();
			if (rs.next()) {
				t = mapper.getRow(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// 总是执行
			release(rs, st, conn);
		}
		return t;
	}

	// 执行cud
	public static int update(String sql, Object... params) {
		// 获取连接
		Connection conn = null;
		PreparedStatement st = null;// 预编译对象
		System.out.println("sql = " + sql);
		int result = 0;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			st = conn.prepareStatement(sql);
			// 绑定参数
			setParams(st, params);
			// 执行
			result = st.executeUpdate();
			// 提交事务
			conn.commit();
		} catch (SQLException e) {
			// 回滚事务
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {// 总是执行
			release(null, st, conn);
		}
		return result;
	}

	private static void setParams(PreparedStatement st, Object[] params) throws SQLException {
		// 判断参数
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
//				System.out.println(params[i]);
				st.setObject(i + 1, params[i]);
			}
		}

	}

	// 释放数据库连接方法
	public static void release(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
