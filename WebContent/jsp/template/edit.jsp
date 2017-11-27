<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>添加客户</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>

<style type="text/css">
input {
	border : 0;
}
</style>

</HEAD>
<BODY>
	<a href="template_findNameByPage.action">查看模板</a>
	<br/>
	<table border="1px" cellspacing="0" width="800px">
		<tr>
			${templateName }
		</tr>
		<tr>
			<td>周次</td>
			<td>计划内容</td>
		</tr>
		<s:iterator value="templateList" var="l">
			<form action="template_update.action" method="post">
				<tr>
					<input type="hidden" name="template_id" value="${l.template_id }"/>
					<input type="hidden" name="template_name" value="${l.template_name }"/>
					<input type="hidden" name="direction_id" value="${l.direction_id }">
					<td><input type="text" name="template_week" value="${l.template_week }" onfocus=this.blur() /></td>
					<td><textarea id="aaa" name="template_content" >${l.template_content }</textarea></td>
					<td><input type="submit" value="修改"/></td>
				</tr>
			</form>
		</s:iterator>
	</table>
</BODY>

<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript">
	$("#aaa").blur(function(){
		
		console.log($("#aaa").val());
	})
	
</script>

</HTML>
