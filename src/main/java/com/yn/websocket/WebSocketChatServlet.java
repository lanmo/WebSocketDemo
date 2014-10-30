package com.yn.websocket;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

import com.yn.text.ChatWebSocket;
import com.yn.util.ChatMessageUtil;
import com.yn.util.Log;

public class WebSocketChatServlet extends WebSocketServlet {

	private static final long serialVersionUID = 3359740816561131161L;
	
	

	public WebSocket doWebSocketConnect(HttpServletRequest request, String protocol) {
		Log.log("protocol", protocol);
		ChatWebSocket chatWebSocket = new ChatWebSocket(request);
		String username = request.getParameter("username");
		if(username != null && "".equals(username.trim())) {
			ChatMessageUtil.getRooms().put(username, chatWebSocket);
		}
		
		return chatWebSocket;
	}

}
