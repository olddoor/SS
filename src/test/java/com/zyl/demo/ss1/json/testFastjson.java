package com.zyl.demo.ss1.json;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.zyl.entity.Group;
import com.zyl.util.JsonUtils;

public class testFastjson {

	public static void main(String[] args) {
		Group g1=new Group();
		g1.setName("福建");
		g1.setCode("059");
		
		Group g11=new Group();
		g11.setName("福州");
		g11.setCode("0591");
		g11.setFatherGroup(g1);
		
		Group g22=new Group();
		g22.setName("厦门");
		g22.setCode("0592");
		g22.setFatherGroup(g1);
		
		Group g111=new Group();
		g111.setName("鼓楼");
		g111.setCode("059101");
		g111.setFatherGroup(g11);
		
		Group g222=new Group();
		g222.setName("台江");
		g222.setCode("059102");
		g222.setFatherGroup(g11);
		
		Set<Group> s=new HashSet();
		s.add(g11);
		s.add(g22);
		g1.setChildren(s);
		
		Set<Group> s2=new HashSet();
		s2.add(g111);
		s2.add(g222);
		g11.setChildren(s2);
		
		List<Group> gs=new ArrayList();
		gs.add(g1);
		gs.add(g11);
		gs.add(g22);
		gs.add(g111);
		gs.add(g222);
		
		String[] excludesProperties=new String[]{"fatherGroup"};
		String str=JsonUtils.obj2Str_ByFilter(gs,null,excludesProperties,null,null);
		System.out.println(str);
		
	}

}
