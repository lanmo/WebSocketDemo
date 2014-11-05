package com.yn.client;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.WebSocket.Connection;
import org.eclipse.jetty.websocket.WebSocketClient;
import org.eclipse.jetty.websocket.WebSocketClientFactory;

import com.yn.client.text.ClientWebSocket;
import com.yn.constants.AppConstants;
import com.yn.util.ChatMessageUtil;

/**
 * 
 * @ClassName: ChatWebSocketClient   
 * @Description: 客户端
 * @author YangNan(杨楠)
 * @date 2014年11月5日 下午3:34:49
 */
public class ChatWebSocketClient  {

	public static void main(String[] args) {
		getConnection("yangnan");
	}
	
	public static Connection getConnection(String username) {

		WebSocketClientFactory clientFactory = new WebSocketClientFactory();
		
		URI uri = null;
		Connection connection = null;
		try {
		
			clientFactory.start();
			WebSocketClient wc = clientFactory.newWebSocketClient();
			String url = String.format(AppConstants.WEBSOCKET_CONSTANTS.URL, ChatMessageUtil.DOMAIN);
			uri = new URI( url + "?username=" + username);
			ClientWebSocket websocket = new ClientWebSocket(); 
			connection = wc.open(uri, websocket, 15L, TimeUnit.SECONDS);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
		
	}
}
