<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/style.css" />
    <script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.js"></script>
    <script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.sorted.js"></script>
    <script type="text/javascript" src="${ pageContext.request.contextPath }/js/bootstrap.js"></script>
    <script type="text/javascript" src="${ pageContext.request.contextPath }/js/ckform.js"></script>
    <script type="text/javascript" src="${ pageContext.request.contextPath }/js/common.js"></script>

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
</head>
<body>
<form action="adminCategorySecond_update.action" method="post" class="definewidth m20">
<input type="hidden" name="csid" value="<s:property value="model.csid"/>">
    <table class="table table-bordered table-hover definewidth m10">
    
        <tr>
            <td width="10%" class="tableleft">二级分类名称</td>
            <td><input type="text" name="csname" value="<s:property value="model.csname"/>"></td>
            
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						所属的一级分类：
					</td>
					
					<td class="ta_01" bgColor="#ffffff" >
					<select name="category.cid">
						<s:iterator var="c" value="cList">
						
						<option value="<s:property value="#c.cid"/>" <s:if test="model.category.cid==#c.cid">selected</s:if>><s:property value="#c.cname" /></option>
						</s:iterator>
					</select>
					</td>
        </tr>
 

        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="submit" class="btn btn-primary" type="button">保存</button> &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
<script>
    $(function () {
		$('#backid').click(function(){
				window.location.href="adminCategorySecond_findAll.action?page=1";
		 });
    });
</script>