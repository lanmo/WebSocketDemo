package com.yn.util;

import java.util.HashMap;
import java.util.Map;

import com.yn.text.ChatWebSocket;

public final class ChatMessageUtil {
	
	private static Map<String, ChatWebSocket> rooms = new HashMap<String, ChatWebSocket>();
	
	public static Map<String, ChatWebSocket> getRooms() {
		return rooms;
	}
}
