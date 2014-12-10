package com.yn.client.text;

import java.io.IOException;

import org.eclipse.jetty.websocket.WebSocket.OnTextMessage;

import com.yn.util.L;

public class ClientWebSocket implements OnTextMessage {
	
	public ClientWebSocket() {
		
	}

	public void onOpen(Connection connection) {
		try {
			connection.sendMessage("我是terminal");
		} catch (IOException e) {
//			e.printStackTrace();
		}
	}

	public void onClose(int closeCode, String message) {
		L.d("onClose",  "closeCode="+closeCode, "message="+message);
	}

	public void onMessage(String data) {
		L.d("recevice Message", data);
	}

}
