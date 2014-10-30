package com.yn.websocket;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

import com.yn.text.ChatWebSocket;
import com.yn.util.ChatMessageUtil;

public class WebSocketChatServlet extends WebSocketServlet {

	private static final long serialVersionUID = 3359740816561131161L;
	
	public WebSocket doWebSocketConnect(HttpServletRequest request, String protocol) {
		ChatWebSocket chatWebSocket = new ChatWebSocket(request);
		String username = request.getParameter("username");
		
		if(StringUtils.isNotBlank(username)) {
			ChatMessageUtil.getRooms().put(username, chatWebSocket);
		}
		
		return chatWebSocket;
	}

}
