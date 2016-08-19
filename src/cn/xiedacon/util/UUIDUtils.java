package cn.xiedacon.util;

import java.util.Date;
import java.util.UUID;

public class UUIDUtils {

	public static String uuid(String value){
		StringBuilder result = new StringBuilder(value);
		
		result.append(new Date().getTime());
		result.append(Thread.currentThread().getId());
		result.append(UUID.randomUUID());
		
		return result.toString().replace("-", "").substring(0, 32);
	}
	
	public static String randomUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
