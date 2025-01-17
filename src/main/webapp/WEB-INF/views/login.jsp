<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@ include file="jstl.jsp"%>
<html>
<head>
<title>登录</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Internship Sign In & Sign Up Form Responsive Widget,Login form widgets, Sign up Web forms , Login signup Responsive web form,Flat Pricing table,Flat Drop downs,Registration Forms,News letter Forms,Elements" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Custom Theme files -->
<link href="../../css/font-awesome.min.css" rel="stylesheet" type="text/css" media="all">
<link href="../../css/snow.css" rel="stylesheet" type="text/css" media="all" />
<link href="../../css/loginStyle.css" rel="stylesheet" type="text/css" media="all" />
<!-- //web font -->
</head>
<script src="../../js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
    function login() {
		$('#mySubmit').submit();
    }
</script>
<body>
<div class="snow-container">
			  <div class="snow foreground"></div>
			  <div class="snow foreground layered"></div>
			  <div class="snow middleground"></div>
			  <div class="snow middleground layered"></div>
			  <div class="snow background"></div>
			  <div class="snow background layered"></div>
			</div>

<div class="top-buttons-agileinfo">
<a href="/login"  class="active">登录</a><a href="/register">注册</a>
</div>
<h1>
</h1>
<div class="main-agileits">
<!--form-stars-here-->
		<div class="form-w3-agile">
			<h2 class="sub-agileits-w3layouts">登录</h2>
			<form action="${ctx }/loginServlet/loginUser" method="post" id="mySubmit">
					<input type="text" id="userName" name="userName"  placeholder="用户"/>
					<input type="password" id="pwd"  name="pwd"  placeholder="密码"/>
				<div class="submit-w3l">
					<input type="button" value="登录" onclick="javascript:login()">
				</div>
				<p class="p-bottom-w3ls"><a href="/register">点击注册</a>如果你没有一个帐户。</p>
				<p class="p-bottom-w3ls">可自动判断您是否是管理员</p>
			</form>
		</div>
		</div>
<!--//form-ends-here-->
<!-- copyright -->
	<div class="copyright w3-agile">
		<p><a>© 2018</a></p>
	</div>
	<!-- //copyright --> 
	<script type="text/javascript" src="../../js/jquery-2.1.4.min.js"></script>

</body>
</html>