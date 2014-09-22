package com.heracles.eat.helper;

import java.util.List;

import com.heracles.framework.file.ConfigureHelper;
import com.heracles.framework.tools.Unit;

public class Categories {
	
	public static List<String> getTableCate(){
		return Unit.arrayToList(ConfigureHelper.getValue("table_cat").split(","));
	}
	
	public static List<String> getFoodCate(){
		return Unit.arrayToList(ConfigureHelper.getValue("food_cat").split(","));
	}
	
	public static List<String> getFoodClasses(){
		return Unit.arrayToList(ConfigureHelper.getValue("food_claess").split(","));
	}
	
	public static List<String> getTableType(){
		return Unit.arrayToList(ConfigureHelper.getValue("table_type").split(","));
	}
	
}
