package com.heracles.framework.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListDirFile {
	
	public static List<String> list(String path, String ext){
		File file = new File(path);
		String[] fileList = file.list();
		List<String> result = new ArrayList<String>();
		if (fileList != null && fileList.length >0){
			for (int i = 0; i < fileList.length; i++){
				if (fileList[i].indexOf("." + ext) > -1) {
					String fileName = fileList[i];
 					result.add(fileName.substring(0, fileName.indexOf("." + ext)));  
				}
			}
		}
		return result;
	}

}
