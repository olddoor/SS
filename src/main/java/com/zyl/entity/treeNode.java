package com.zyl.entity;

import java.util.ArrayList;
import java.util.List;
/**
 * 使用easyui tree的值对象模型
 */
public class treeNode {
	    private String id;          //要显示的子节点的ID
	    private String text;        //要显示的子节点的 Text
	    private String iconCls;     //节点的图标
	    private String parentId;    //父节点的ID
	    private List<treeNode>  children;   //孩子节点的List

	    public treeNode(){}

	    public treeNode(String id, String text, String iconCls, String parentId,

	    List<treeNode>children) {
	       super();
	       this.id= id;
	       this.text= text;
	       this.iconCls= iconCls;
	       this.parentId= parentId;
	       this.children= children;
	    }

	   

	    public String getId() {

	       return id;

	    }

	    public void setId(String id) {
	       this.id= id;
	    }

	    public String getText() {
	       return text;
	    }

	    public void setText(String text) {
	       this.text= text;
	    }

	    public String getIconCls() {
	       return iconCls;
	    }

	    public void setIconCls(String iconCls) {
	       this.iconCls= iconCls;
	    }

	    public String getParentId() {
	       return parentId;
	    }

	    public void setParentId(String parentId) {
	       this.parentId= parentId;
	    }

	    public List<treeNode> getChildren() {
	       return children;
	    }

	    public void setChildren(List<treeNode> children) {
	       this.children= children;
	    }

	    //添加孩子的方法

	    public void addChild(treeNode node){
	       if(this.children == null){
	           children= new ArrayList<treeNode>();
	           children.add(node);
	       }else{
	           children.add(node);
	       }
	          
	    }
}
