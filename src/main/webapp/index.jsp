<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>简单聊天室</title>
		<script type="text/javascript">
			var room = {
				connect : function() {
					var location = "ws://localhost:8080/ws/servlet/chat";
					this.ws = new WebSocket(this.location);
					this.ws.onopen = this.onopen;
					this.ws.onclose = this.onclose;
					this.ws.onmessage = this.onmessage;
					this.ws.onerror = this.onerror;
					
					return this;
				},
				onopen : function(evt) {
					//建立连接
					console.log(evt+"建立连接。。。。。。。。");
				},
				onclose : function(evt) {
					//关闭连接
					this.ws = null;
					console.log(evt+"关闭连接。。。。。。。。");
				},
				onerror : function(evt) {
					//错误处理
					console.log(evt+"错误处理。。。。。。。。");
				},
				onmessage : function(evt) {
					//接收消息
					var data = evt.data;
					console.log(evt+"接收消息。。。。。。。。");
				},
				onsend : function(message) {
					this.ws.send(message);
				} 
				
			};
			
		</script>
		
		<script type="text/javascript">
		
			var room;
			function send() {
				$("bjoin").disable = true;
				var msg = $("mext").value;
				room.send(msg);
			}
			
			function join() {
				$("bsend").disable = true;
				room = room.connect();
			}
			
			function $(id) {
				return document.getElementByid(id);
			}
		</script>
	</head>
	<body>
		<header>简单聊天室......................</header>
		<div id="message" style="height: 250px;width: 480px;background-color: red;float: left;">
		</div>
		
		<div id="room" style="height: 250px;width: 200px;background-color: green;float: left;">
		</div>
		
		<div id="send" style="height: 30px;width: 680px;background-color: green;clear: left;">
			<input id="mext" size="110"/>
			<input type="button" value="发送" disabled="disabled" id="bsend" onclick="send()"/>
			<input type="button" value="Join" onclick="join()" id="bjoin" />
		</div>
	</body>
</html>
