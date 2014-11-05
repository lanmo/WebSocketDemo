package com.yn.util;

import org.apache.commons.lang.ArrayUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public final class L {
	
	public static void d(String str, Object... objs) {
		
		if(ArrayUtils.isEmpty(objs)) {
			System.out.println(str);
			return;
		}
		
		if(objs.length == 1) {
			System.out.println(str + ":" + JSONObject.toJSONString(objs[0]));
		} else {
			System.out.println(str + ":" +JSONArray.toJSONString(objs));
		}
	}
}
