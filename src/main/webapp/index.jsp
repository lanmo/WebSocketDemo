<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>简单聊天室</title>
		<script type="text/javascript">
			var wsStatus;
			
			var room = {
				connect : function(username) {
					var location = "ws://localhost:8080/ws/servlet/chat?username=" + username;
					this.ws = new WebSocket(location);
					this.ws.onopen = this.onopen;
					this.ws.onclose = this.onclose;
					this.ws.onmessage = this.onmessage;
					this.ws.onerror = this.onerror;
					
					return this;
				},
				onopen : function(evt) {
					//建立连接
					console.log(this);
					console.log("建立连接。。。。。。。。。。。");
					wsStatus = WebSocket.OPEN;
				},
				onclose : function(evt) {
					//关闭连接
					this.ws = null;
					console.log("关闭连接。。。。。。。。");
					console.log(this);
					wsStatus = WebSocket.CLOSE;
				},
				onerror : function(evt) {
					//错误处理
					console.log("错误处理。。。。。。。。");
					console.log(this);
					wsStatus = -1;
				},
				onmessage : function(evt) {
					//接收消息
					var data = evt.data;
					if(data.indexOf("join") > -1) {
						var usernames = data.split(",");
						var html = "";
						console.log("data:"+usernames.length);
						for(var i=1; i<usernames.length; ++i) {
							html += usernames[i] + "   加入了聊天室............" + "<br/>";
						}

						$("room").innerHTML = html;
						
					} else {
						var mess = data.split(",");
						$("message").innerHTML += mess[0] +":"+ "<br/>  &nbsp;&nbsp;" + mess[1] + "<br/>";
					}
				},
				onsend : function(message) {
						if(wsStatus === WebSocket.OPEN) {
							this.ws.send(message);
						}
				} 
				
			};
			
			var roomChat;
			function send() {
				$("bjoin").disabled = true;
				$("bsend").disabled = false;
				var msg = $("mext").value;
				roomChat.onsend(msg);
				$("mext").value = '';
			}
			
			function join() {
				$("bsend").disabled = false;
				$("bjoin").disabled = true;
				var username = $("mext").value;
				roomChat = room.connect(username);
				$("mext").value = '';
			}
			
			function $(id) {
				return document.getElementById(id);
			}
		</script>
	</head>
	<body>
		<header>简单聊天室......................</header>
		<div id="message" style="height: 250px;width: 480px;background-color: red;float: left;">
			<div style="height: 20px;width:90%; border: 1px solid white;" >abc</div>
			<div style="height: 20px;border: 1px solid white;" >ddsa</div>
		</div>
		
		<div id="room" style="height: 250px;width: 200px;background-color: green;float: left;">
		</div>
		
		<div id="send" style="height: 30px;width: 680px;background-color: green;clear: left;">
			<input id="mext" size="70"/>
			<input type="button" value="发送" disabled="disabled" id="bsend" onclick="send()"/>
			<input type="button" value="Join" onclick="join()" id="bjoin" />
		</div>
	</body>
</html>
