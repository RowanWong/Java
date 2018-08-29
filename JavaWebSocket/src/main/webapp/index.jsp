<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!-- 声明doctype，让浏览器按照w3c标准解析渲染页面 -->
<!DOCTYPE html>
<html>
<head>
	<title>Java后端WebSocket的Tomcat实现</title>
	
	<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
	<script src="js/websocket.js"></script>
</head>
<body>
Welcome<br/><input id="text" type="text"/>
<button id="smsgbtn">发送消息</button>
<hr/>
<button id="closeWebSocket">关闭websocket连接</button>
<hr/>
<div id="message"></div>
</body>

<script type="text/javascript">
	
//发送消息
	$(document).on("click","#smsgbtn",function(){
		var sendmsg = $("#text").val();
		websocket.send(sendmsg);
		setMessageInnerHTML(1,sendmsg);
	});
	
	//将消息显示在网页上
	function setMessageInnerHTML(pos,innerHTML){
		if(pos == 1){//显示发送消息			
			$("#message").html($("#message").html()+ "Me:" + innerHTML + '<br/>');	
		}else if(pos == 2){//显示接收消息
			$("#message").html($("#message").html()+ "Server:" + innerHTML  + '<br/>');	
		}else if(pos == 0){//系统消息
			$("#message").html($("#message").html() + "系统消息：" +innerHTML + '<br/>');	
		}
	}
	
	
</script>
</html>
