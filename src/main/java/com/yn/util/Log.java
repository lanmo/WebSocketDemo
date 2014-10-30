package com.yn.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public final class Log {
	
	public static void log(String str, Object... objs) {
		if(objs.length == 1) {
			System.out.println(str + ":" + JSONObject.toJSONString(objs[0]));
		} else {
			System.out.println(str + ":" +JSONArray.toJSONString(objs));
		}
	}
}
