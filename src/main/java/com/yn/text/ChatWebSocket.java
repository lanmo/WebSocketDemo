package com.yn.text;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket.OnTextMessage;

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
		Log.log("onOpen");
	}

	public void onMessage(String message) {
		Log.log("recevice message ", message);
		try {
			connection.sendMessage("我是服务器");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
