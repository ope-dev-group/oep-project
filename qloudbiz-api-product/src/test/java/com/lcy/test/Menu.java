package com.lcy.test;

import java.util.List;

import lombok.Data;

@Data
public class Menu {
	
	private String id;
	private String name;
	private String pid;
	private List<Menu> childrens;

}
