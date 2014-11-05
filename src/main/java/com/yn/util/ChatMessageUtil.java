package com.yn.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.yn.text.ChatWebSocket;

public final class ChatMessageUtil {
	
	private static final Map<String, ChatWebSocket> rooms = new HashMap<String, ChatWebSocket>();
	
	private static Lock lock = new ReentrantLock();
	
	public static final String DOMAIN = "localhost:8080";
	
	public static Map<String, ChatWebSocket> getRooms() {
		
		//枷锁
		lock.lock();
		try {
			return rooms;
		} finally {
			//释放锁
			lock.unlock();
		}
		
	}
}
