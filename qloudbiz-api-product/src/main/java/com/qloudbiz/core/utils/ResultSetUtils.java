package com.qloudbiz.core.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultSetUtils {

	public ResultSetUtils() {
		// TODO Auto-generated constructor stub
	}

	
	public static List<Map<String,Object>> processResultSet(ResultSet rs) throws SQLException {
		List list = new ArrayList();
		ResultSetMetaData md = rs.getMetaData();// 获取键名
		int columnCount = md.getColumnCount();// 获取行的数量		
		
		while (rs.next()) {
			Map<String,Object> rowData = new HashMap<String,Object>();// 声明Map
			for(int i = 1; i <= columnCount; i++) {
				String colname = md.getColumnName(i);				
				Object colvalue = rs.getObject(i);				
				rowData.put(colname,colvalue );// 获取键名及值
			}
			list.add(rowData);
		}
		return list;
	}
}
