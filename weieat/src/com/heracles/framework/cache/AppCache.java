package com.heracles.framework.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 静态cache的缓存类，实现数据缓存到Map中，对于大数据及频繁变化的数据不推荐使用.
 * @author 31307@sohu.com
 * 
 */

public abstract class AppCache {
	
	private static Map<Object, Object> objectMap = new HashMap<Object, Object>();

	public static void setObject(String objectName, List<?> list) {
		if (objectName != null && !objectName.equals("")){
			objectMap.put(objectName+"list", list);
		}
	}

	public static void setObject(String objectName, Map<?, ?> map) {
		if (objectName != null && !objectName.equals("")){
			objectMap.put(objectName+"map", map);
		}	
	}

	public static List<?> getObjectList(String listName) {
		if (listName != null && !listName.equals("")){
			return (List<?>) objectMap.get(listName + "list");
		}else	
			return null;
	}

	public static Map<?, ?> getObjectMap(String mapName) {
		if (mapName != null && !mapName.equals("")){
			return (Map<?, ?>) objectMap.get(mapName + "map");
		}else	
			return null;
	}

}
