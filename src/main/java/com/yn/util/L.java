package com.yn.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public final class L {
	
	static SerializerFeature[] features = {
		SerializerFeature.WriteDateUseDateFormat,
		SerializerFeature.WriteNullStringAsEmpty,
		SerializerFeature.WriteMapNullValue
	};
	
	public static void d(String str, Object... objs) {
		
		if(objs.length == 1) {
			System.out.println(str + ":" + JSONObject.toJSONString(objs[0], features));
		} else {
			System.out.println(str + ":" +JSONArray.toJSONString(objs, features));
		}
	}
	
	public static void dr(String str, Object... objs) {
		
		if(objs.length == 1) {
			System.err.println(str + ":" + JSONObject.toJSONString(objs[0], features));
		} else {
			System.err.println(str + ":" +JSONArray.toJSONString(objs, features));
		}
	}
	
	public static void d(Object obj) {
		System.out.println(JSONObject.toJSONString(obj, features));
	}
	
	public static void dr(Object obj) {
		System.err.println(JSONObject.toJSONString(obj, features));
	}
}
