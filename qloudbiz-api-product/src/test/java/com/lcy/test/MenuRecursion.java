package com.lcy.test;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class MenuRecursion {

	static List<Menu> menuList = new ArrayList<Menu>();
	static{
		Menu mu = new Menu();
		mu.setId("1");
		mu.setName("目录");
		mu.setPid(null);
		Menu mu1 = new Menu();
		mu1.setId("2");
		mu1.setName("目录1");
		mu1.setPid("1");
		Menu mu2 = new Menu();
		mu2.setId("3");
		mu2.setName("目录1.1");
		mu2.setPid("2");
		Menu mu3 = new Menu();
		mu3.setId("4");
		mu3.setName("目录1.2");
		mu3.setPid("2");
		Menu mu4 = new Menu();
		mu4.setId("5");
		mu4.setName("目录2");
		mu4.setPid("1");
		Menu mu5 = new Menu();
		mu5.setId("6");
		mu5.setName("目录2.1");
		mu5.setPid("5");
		Menu mu6 = new Menu();
		mu6.setId("7");
		mu6.setName("目录2.2");
		mu6.setPid("5");
		Menu mu7 = new Menu();
		mu7.setId("8");
		mu7.setName("目录2.2.1");
		mu7.setPid("7");
		menuList.add(mu);
		menuList.add(mu1);
		menuList.add(mu2);
		menuList.add(mu3);
		menuList.add(mu4);
		menuList.add(mu5);
		menuList.add(mu6);
		menuList.add(mu7);
	}
	

	public static void main(String[] args) {
		
		//1.查询所有根节点的数据
		List<Menu> rootNodes=new ArrayList<Menu>();
		Menu mu = new Menu();
		mu.setId("1");
		mu.setName("目录");
		rootNodes.add(mu);
		
		
		
		System.out.println(JSON.toJSONString(rootNodes));
		
		/*List<Menu> childList = treeMenuList(menuList, 1);
		for (Menu m : childList) {
			System.out.println(m.getId() + "   " + m.getName());
		}
*/
		
	}
	
	

}
