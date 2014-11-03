package com.yn.websocket;


import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

import com.yn.text.ChatWebSocket;

public class WebSocketChatServlet extends WebSocketServlet {

	private static final long serialVersionUID = 3359740816561131161L;
	
	public WebSocket doWebSocketConnect(HttpServletRequest request, String protocol) {
		ChatWebSocket chatWebSocket = new ChatWebSocket(request);
		return chatWebSocket;
	}

}
