package com.zyl.demo.ss1.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * EasyUI DataGrid模型
 * @author 孙宇
 */
public class Grid {
	private Long total = 0L; // 总记录数
	private List rows = new ArrayList(); // 行数

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}
	
	public void write(HttpServletResponse response,String json) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(json);
		response.getWriter().flush();
		response.getWriter().close();
	}
}
