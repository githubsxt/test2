package com.yq.util;

import java.sql.ResultSet;
import java.sql.SQLException;
//行映射器  数据库数据转换 java对象
public interface RowMapper<T> {

	T getRow(ResultSet rs) throws SQLException;
}
