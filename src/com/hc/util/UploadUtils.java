package com.hc.util;

import java.util.UUID;

/**
 * 文件上传的工具类
 * 
 * @author Administrator
 */
public class UploadUtils {
	/**
	 * 传入文件的名称 ，返回唯一的名称 
	 * 例如：gril.jsp 返回 sds.jpg
	 * @return
	 */
	public static String getUUIDName(String fileName) {
		int index = fileName.lastIndexOf(".");
		String lastName = fileName.substring(index,fileName.length());
		System.out.println(fileName);
		//唯一的字符串
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid+lastName;
	}
	public static void main(String[] args) {
		System.out.println(getUUIDName("girl.jsp"));;
	}
}
