/**
 * websocket 操作
 */
var websocket = null;
var isSptS = false;

//判断浏览器是否支持websocket  目前IE 10+ Edge Firefox 4+ Chrome 4+ Safari 5+ Opera 11.5+支持
if('WebSocket' in window){
//	websocket = new WebSocket("ws://localhost:8080/websocket");
	websocket = new WebSocket("ws://localhost:8080/JavaWebSocket/websocket");
	isSptS = true;
}else{
	alert('current browser is not support websocket!');
}

//连接发生错误的回调方法
websocket.onerror = function(){
	setMessageInnerHTML(0,"WebSocket连接发生错误");
}

//连接成功建立的回调方法
websocket.onopen = function(){
	setMessageInnerHTML(0,"WebSocket连接 success.");
}

//接收消息的处理方法
websocket.onmessage = function(event){
	setMessageInnerHTML(2,event.data);
}

//连接关闭的回调方法
websocket.onclose = function(){
	setMessageInnerHTML(0,"WebSocket closed.");
}

//关闭websocket连接
window.onbeforeload = function(){
	closeWebSocket();
}


//关闭websocket连接
function closeWebSocket(){
	websocket.close();
}

//将消息显示在网页上
function setMessageInnerHTML(pos,innerHTML){
	alert(pos);
	if(pos == 1){//显示发送消息			
		$("#message").innerHTML += 'Me:'+innerHTML + '<br/>';
	}else if(pos == 2){//显示接收消息
		$("#message").innerHTML += 'Server:' + innerHTML + '<br/>';
	}else if(pos == 0){//系统消息
		$("#message").innerHTML += "系统消息:" + innerHTML + '<br/>';			
	}
}