package com.zyl.entity;

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
	private List rows = new ArrayList(); // 数据
	
	//构造函数
	public Grid(){
		
	}
	public  Grid(Long total,List rows){
		this.total=total;
		this.rows=rows;
	}

	
	//getter/setter
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
}
