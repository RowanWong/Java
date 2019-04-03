<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />
<meta content="email=no" name="format-detection" />
<title>404 Error</title>
<style type="text/css">
html, body {
	margin: 0;
	padding: 0;
	width: 100%;
}

body {
	font-family: 'Arial', 'Microsoft YaHei';
	font-size: 18px;
	background: #ccc;
}

.tishi {
	color: #fff;
	text-align: center;
	line-height: 44px;
	box-shadow: 0 2px 3px rgba(0, 0, 0, 0.2);
	background-color: #84C101
}

.wrap {
	margin: 10px;
	background-color: #fff;
	border: 1px solid #eaeaea;
	text-align: center;
	border-radius: 5px;
}

#timer {
	font-size: 12px;
	color: #b3b3b3;
	text-align: right;
	padding-right: 5px;
}

.footer {
	margin: 0 auto;
	padding: 15px;
	line-height: 30px;
	position: relative;
}

.footer>footer {
	max-width: 580px;
	margin: 0 auto;
	background-color: #585858;
	text-align: center;
	color: #fff;
	font-size: 12px;
	border-radius: 90px;
}
</style>
</head>
<body>
	<div class="tishi">温馨提示</div>
	<div class="wrap">
		<p>您访问的页面不存在</p>
		<div id="timer"></div>
	</div>
	<div class="footer">
		<footer> &copy; 2015 xxxxxx公司 京ICP备xxxxxx号 </footer>
	</div>
</body>
<script type="text/javascript">
	var TSTimer = {
		/**
		 * 时间格式化：时间毫秒数、Date对象 ---> 时间字符串 默认格式化格式：yyyy-MM-dd HH:mm:ss
		 */
		date2str : function(str, fmt) {
			if (fmt === undefined) {
				fmt = 'yyyy-MM-dd HH:mm:ss';
			}
			this.isValidFormat(fmt);
			var date;
			if (typeof str === 'number') {
				date = new Date(str);
			} else if (this.isValidDate(str)) {
				date = str;
			}
			var o = {
				'M+' : date.getMonth() + 1, // 月
				'd+' : date.getDate(), // 日
				'H+' : date.getHours(), // 小时（24小时制）
				'm+' : date.getMinutes(), // 分
				's+' : date.getSeconds(), // 秒
				'S' : date.getMilliseconds()
			// 毫秒
			};
			if (/(y+)/.test(fmt)) {
				fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '')
						.substr(4 - RegExp.$1.length));
			}
			for ( var k in o) {
				if (new RegExp('(' + k + ')').test(fmt)) {
					fmt = fmt.replace(RegExp.$1,
							(RegExp.$1.length == 1) ? (o[k]) : (('00' + o[k])
									.substr(('' + o[k]).length)));
				}
			}
			return fmt;
		},
		/**
		 * 规定日期格式
		 * 比如：yyyy年MM月dd日 HH:mm:ss S、yyyy-MM-dd HH:mm:ss S......
		 */
		isValidFormat : function(fmt) {
			if (!/^y+[\u5e74-]?M+[\u6708-]?(d+)[\u65e5]? H+[\u65f6:]?m+[\u5206:]?(s+[\u79d2]?)?(( S)?)$/
					.test(fmt)) {
				throw new Error('"' + fmt + '" is not supported by the format.');
				return false;
			}
			return true;

		},
		/**
		 * 判断是否是Date对象
		 */
		isValidDate : function(date) {
			return Object.prototype.toString.call(date) === '[object Date]'
					&& !isNaN(date.getTime());
		}
	};
	function setTimer() {
		var timer = document.getElementById('timer');
		var dateStr = TSTimer.date2str(new Date(), 'yyyy-MM-dd HH:mm');
		timer.innerText = dateStr;
	}
	setTimer();
	setInterval(function() {
		setTimer();
	}, 60000);
</script>
</html>
