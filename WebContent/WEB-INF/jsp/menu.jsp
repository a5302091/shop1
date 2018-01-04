<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
			<s:if test="#session.u==null">
				<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/user_loginPage.action">登录</a>|
				</li>
				<li id="headerRegister" class="headerRegister" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/user_RegistPage.action">注册</a>|
				</li>
				</s:if>
				<s:else>
				
				<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<s:property value="#session.u.name"/>
				</li>
				
				<li id="headerRegister" class="headerRegister" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/order_findByUid.action?page=1">我的订单</a>|
				</li>
				<li id="headerRegister" class="h eaderRegister" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/user_quit.action">退出</a>|
				</li>
				
				</s:else>
				
				<li id="headerUsername" class="headerUsername"></li>
				<li id="headerLogout" class="headerLogout">
					<a>[退出]</a>|
				</li>
						<li>
							<a>会员中心</a>
							|
						</li>
						<li>
							<a>购物指南</a>
							|
						</li>
						<li>
							<a>关于我们</a>
							
						</li>
			</ul>
		</div>
		<div class="cart">
			<a  href="${ pageContext.request.contextPath }/car_goCar.action">购物车</a>
		</div>
			<div class="phone">
				客服热线:
				<strong>18374861150</strong>
			</div>
	</div>
	
	
	<div class="span24">
		<ul class="mainNav">
					<li>
						<a href="${pageContext.request.contextPath}/index.action">首页</a>
						|
					</li>
					
					<s:iterator var="c" value="#session.clist">
							
					<li>
						<a href="${ pageContext.request.contextPath }/product_findByCid.action?cid=<s:property value="#c.cid"/>&page=1"><s:property value="#c.cname"/></a>
						|
					</li>
					</s:iterator>
					
					
		</ul>
	</div>


</body>
</html>