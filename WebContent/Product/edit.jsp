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
<form action="adminProduct_update.action" method="post" class="definewidth m20" enctype="multipart/form-data">
<input type="hidden" name="pid" value="<s:property value="model.pid"/>">
<input type="hidden" name="image" value="<s:property value="model.image"/>">
<table class="table table-bordered table-hover definewidth m10">


 
    <tr>
    
    
         <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						所属的二级分类：
					</td>
					
					<td class="ta_01" bgColor="#ffffff" >
					<select name="categorySecond.csid">
						<s:iterator var="cs" value="csList">
						
						<option value="<s:property value="#cs.csid"/>" <s:if test="#cs.csid==model.categorySecond.csid">selected</s:if>><s:property value="#cs.csname" /></option>
						</s:iterator>
					</select>
					</td>
    
    
        <td class="tableleft">商品名称</td>
        <td><input type="text" name="pname" value="<s:property value="model.pname"/>"/></td>
    </tr>
    
      <tr>
        <td class="tableleft">市场价格</td>
        <td><input type="text" name="market_price" value="<s:property value="model.market_price"/>"/></td>
    </tr>
    
    <tr>
        <td class="tableleft">商品价格</td>
        <td><input type="text" name="shop_price" value="<s:property value="model.shop_price"/>"/></td>
    </tr>
    <tr>
        <td class="tableleft">商品图片</td>
        <td><input type="file" name="upload"/></td>
    </tr>
    <tr>
        <td class="tableleft">是否热门</td>
        <td> 
     		<select name="is_hot">
     		<s:if test="model.is_hot==1">
     			
			<option value="1">是</option>
     		</s:if>
     		
     		<s:else>
     		<option value="0">否</option>
     		</s:else>
   			
     		</select>
        </td>
    </tr>
    
     <tr>
        <td class="tableleft">商品描述</td>
        <td>
        	<textarea rows="5" cols="28" name="pdesc"/><s:property value="model.pdesc"/></textarea>
        </td>
    </tr>
    
    
    
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary" type="button">修改</button> &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form>
</body>
</html>
<script>
    $(function () {       
		$('#backid').click(function(){
				window.location.href="adminProduct_findAll.action?page=1";
		 });

    });
</script>