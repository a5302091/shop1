
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

		<link rel="stylesheet"
			href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">

		<script
			src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>

		<script
			src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

		<script type="text/javascript" src="js/jquery-2.0.3.min.js"></script>

		<script type="text/javascript">


	function change(){
		var img1=document.getElementById("checkImg");
		img1.src="${pageContext.request.contextPath}/check.action?time="+new Date().getTime();  
		}
			
	</script>


	</head>

	<body background="images/ws.jpg">

		<center style="padding-top: 120px">
			<h2 style="margin-bottom: 20px">
				管理员登录
			</h2>

			<!-- 登陆表单 -->
			<form id="ff" class="form-horizontal" method="post"
				action="${ pageContext.request.contextPath }/adminUser_login.action" style="margin-right: 80px">



				<div class="form-group">
					<label for="firstname" class="col-sm-5 control-label">
						用户名
					</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" id="firstname"
							name="username" placeholder="请输入用户名">
					</div>
				</div>
				<div class="form-group">
					<label for="lastname" class="col-sm-5 control-label">
						密码
					</label>
					<div class="col-sm-3">
						<input type="password" class="form-control" id="lastname"
							name="password" placeholder="请输入密码">
					</div>
				</div>
						<s:actionerror cssStyle="color :red"/>

				<div class="form-group">
					<label for="yanzheng" class="col-sm-5 control-label">
						验证码
					</label>

					<div class="col-sm-2">
						<input type="text" class="form-control" id="checkcode" name="checkcode"
							placeholder="输入验证码" />
					</div>
					
					
					
					<div align="center"  id="yan" style="margin-right:  410px">

					<img id="checkImg" class="captchaImage" 
					src="${pageContext.request.contextPath}/check.action" onclick="change()" title="点击更换验证码" style="cursor: pointer;">
					</div>
					
		
				</div>	

				<!-- 按钮 -->
				<div class="form-group" style="padding-top: 30px">
					<div class="col-sm-offset-4 col-sm-4">
						<button type="reset" id="butclean" class="btn btn-default">
							清空
						</button>
				
						<div class="col-sm-offset-3 col-sm-3">
							<button type="submit" id="sub1"
								class="btn btn-default">
								登陆
							</button>
						</div>
					</div>
				</div>



			</form>

		</center>
	</body>
</html>
    