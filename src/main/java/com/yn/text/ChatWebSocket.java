package com.yn.text;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket.OnTextMessage;

import com.yn.util.ChatMessageUtil;
import com.yn.util.Log;

public class ChatWebSocket implements OnTextMessage{
	
	private Connection connection;
	public ChatWebSocket(){}
	
	public ChatWebSocket(HttpServletRequest request) {
	}

	public void onClose(int arg0, String arg1) {
		Log.log("close:"+arg0+"", arg1);
	}

	public void onOpen(Connection con) {
		connection = con;
		try {
			con.sendMessage("connect");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onMessage(String message) {
		if(message.equals("connect")) {
		
			Map<String, ChatWebSocket> map = ChatMessageUtil.getRooms();
			 for(Entry<String, ChatWebSocket> entry : map.entrySet()) {
				 String name = entry.getKey();
				 ChatWebSocket socket = entry.getValue(); 
				 socket.sendMessage("join,"+ name);
			 }
			
			return;
		}
		
		Map<String, ChatWebSocket> map = ChatMessageUtil.getRooms();
		for(Entry<String, ChatWebSocket> entry : map.entrySet()) {
			StringBuffer sb = new StringBuffer();
			String name = entry.getKey();
			 ChatWebSocket socket = entry.getValue(); 
			 sb.append(name).append(",").append(message);
			 
			 socket.sendMessage(sb.toString());
	    }
			 
	}
	
	//发送消息
	public String sendMessage(String message) {
		if(connection != null && connection.isOpen()) {
			try {
				connection.sendMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
