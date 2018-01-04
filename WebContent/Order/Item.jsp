<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table border="1" width="100%">
<s:iterator var="orderItem" value="list">
  <tr>
    <td><img width="40" height="45" src="${ pageContext.request.contextPath }/<s:property value="#orderItem.product.image"/>"></td>
    <td><s:property value="#orderItem.count"/></td>
    <td><s:property value="#orderItem.subtotal"/></td>
  </tr>
  </s:iterator>
</table>

