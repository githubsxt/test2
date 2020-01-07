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
	// ʹ������Դ�������ݿ�
	private static final DataSource ds;

	static {
		BasicDataSource source = new BasicDataSource();
		// �������Ӳ���
		source.setDriverClassName(driver);
		source.setUrl(url);
		source.setUsername(username);
		source.setPassword(password);
		ds = source;
	}

	// ��ѯ ����һ�����󼯺�
	public static <T> List<T> findList(String sql, RowMapper<T> mapper, Object... params) {
		// ��ȡ����
		Connection conn = null;
		PreparedStatement st = null;// Ԥ�������
		ResultSet rs = null;
		List<T> list = new ArrayList<T>();
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			// �󶨲���
			setParams(st, params);
			// ִ��
			rs = st.executeQuery();
			while (rs.next()) {
				list.add(mapper.getRow(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// ����ִ��
			release(rs, st, conn);
		}
		return list;
	}

	// ��ѯ ����һ������
	public static <T> T findObject(String sql, RowMapper<T> mapper, Object... params) {
		// ��ȡ����
		Connection conn = null;
		PreparedStatement st = null;// Ԥ�������
		ResultSet rs = null;
		T t = null;
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			// �󶨲���
			setParams(st, params);
			// ִ��
			rs = st.executeQuery();
			if (rs.next()) {
				t = mapper.getRow(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// ����ִ��
			release(rs, st, conn);
		}
		return t;
	}

	// ִ��cud
	public static int update(String sql, Object... params) {
		// ��ȡ����
		Connection conn = null;
		PreparedStatement st = null;// Ԥ�������
		System.out.println("sql = " + sql);
		int result = 0;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			st = conn.prepareStatement(sql);
			// �󶨲���
			setParams(st, params);
			// ִ��
			result = st.executeUpdate();
			// �ύ����
			conn.commit();
		} catch (SQLException e) {
			// �ع�����
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {// ����ִ��
			release(null, st, conn);
		}
		return result;
	}

	private static void setParams(PreparedStatement st, Object[] params) throws SQLException {
		// �жϲ���
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
//				System.out.println(params[i]);
				st.setObject(i + 1, params[i]);
			}
		}

	}

	// �ͷ����ݿ����ӷ���
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
