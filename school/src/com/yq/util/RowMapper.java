package com.yq.util;

import java.sql.ResultSet;
import java.sql.SQLException;
//��ӳ����  ���ݿ�����ת�� java����
public interface RowMapper<T> {

	T getRow(ResultSet rs) throws SQLException;
}
