package com.yn.text;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.websocket.WebSocket.OnTextMessage;

import com.yn.util.ChatMessageUtil;
import com.yn.util.Log;

public class ChatWebSocket implements OnTextMessage{
	
	private Connection connection;
	private String username;
	public ChatWebSocket(){}
	public ChatWebSocket(HttpServletRequest request) {
		this.username = request.getParameter("username");
	}

	public void onClose(int arg0, String arg1) {
		
		Log.log("close", "username="+username, "arg0="+arg0, "arg1="+arg1);
		if(StringUtils.isNotBlank(username)) {
			ChatMessageUtil.getRooms().remove(username);
		}
		
	}

	public void onOpen(Connection con) {
		connection = con;
		Log.log("open", username);
		if(StringUtils.isNotBlank(username)) {
			ChatMessageUtil.getRooms().put(username, this);
		}
		
		//给每个在线的用户发送加入聊天室的消息
		Map<String, ChatWebSocket> map = ChatMessageUtil.getRooms();
		StringBuilder sb = new StringBuilder("join,");
		for(String name : map.keySet()) {
			sb.append(name).append(",");
		}
		Log.log("username", sb);
		
		 for(Entry<String, ChatWebSocket> entry : map.entrySet()) {
			 ChatWebSocket socket = entry.getValue(); 
			 socket.sendMessage(StringUtils.chomp(sb.toString(), ","));
		 }
		
	}

	public void onMessage(String message) {
		
		if(message.equals("heartbeat"))  {
			Log.log("心跳", username);
			return;
		}
		
		Map<String, ChatWebSocket> map = ChatMessageUtil.getRooms();
		for(Entry<String, ChatWebSocket> entry : map.entrySet()) {
			
			StringBuffer sb = new StringBuffer();
			 ChatWebSocket socket = entry.getValue(); 
			 sb.append(username).append(",").append(message);
			 
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
