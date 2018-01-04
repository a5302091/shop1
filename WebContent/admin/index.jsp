<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>后台管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/main-min.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="header">

    <div class="dl-title" >
        <h1>商城管理系统</h1>
    </div>

    <div class="dl-log">欢迎您:<span class="dl-log-user">${ admin.username }</span><a href="/chinapost/index.php?m=Public&a=logout" title="退出系统" class="dl-log-quit">[退出]</a>
    </div>
</div>
<div class="content">
    <div class="dl-main-nav">
        <div class="dl-inform"><div class="dl-inform-title"><s class="dl-inform-icon dl-up"></s></div></div>
        <ul id="J_Nav"  class="nav-list ks-clear">
            <li class="nav-item dl-selected"><div class="nav-item-inner nav-home">系统管理</div></li>
		<li class="nav-item dl-selected"><div class="nav-item-inner nav-order">业务管理</div></li>
		<li class="nav-item dl-selected"><div class="nav-item-inner nav-order">基础信息</div></li>
        </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">

    </ul>
</div>
<script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/bui-min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/common/main-min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/config-min.js"></script>
<script>
    BUI.use('common/main',function(){
        var config = [{id:'1',menu:
            [{text:'系统管理',items:[{id:'12',text:'一级分类',href:'adminCategory_findAll.action'},
            {id:'3',text:'二级分类',href:'adminCategorySecond_findAll.action?page=1'},
            {id:'4',text:'商品管理',href:'adminProduct_findAll.action?page=1'},
            {id:'6',text:'订单管理',href:'Menu/index.jsp'}]}]},
            {id:'7',homePage : '9',menu:[{text:'订单管理',items:[{id:'9',text:'订单查询',href:'adminOrder_findAll.action?page=1'}]}]},
            {id:'17',homePage : '19',menu:[{text:'基础信息',items:[{id:'19',text:'查询业务',href:'Node/index.jsp'}]}]}];
        new PageUtil.MainPage({
            modulesConfig : config
        });
    });
</script>
</body>
</html>