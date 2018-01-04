package com.hql.utils;

import java.util.UUID;

/**
 * 生产随机激活码
 * @author Administrator
 *
 */

public class UUIDUtils {
	
	//生成激活码的方法
	public static  String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}

}
