<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD id=Head1>
<TITLE>模板</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>

<style type="text/css">
	
</style>

</HEAD>
<BODY>
	<a href="template_add">添加教学计划模板</a>
	<form action="template_findNameByPage.action" method="post" id="templateForm" name="templateForm">
		教学方向：<select id="directions" name="direction_id"></select>
		教学计划模板名：<input type="text" name="template_name" id="template_name">
		<input type="submit" value="search" />
		<table>
			<tr>
				<td>模板</td>
				<td>修改</td>
				<td>删除</td>
			</tr>
			<c:forEach items="${page.beanList }" var="template">
				<tr>
					<td>${template.template_name }</td>
					<td>
						<a href="${pageContext.request.contextPath }/template_initUpdateByName.action?template_name=${template.template_name}">修改</a>
					</TD>
					<td>
						<a href="${pageContext.request.contextPath }/template_deleteByName.action?template_name=${template.template_name}" onclick="return window.confirm('确认删除吗?')">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<div style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
			共[<B>${page.totalCount}</B>]条记录，共[<B>${page.totalPage}</B>]页 ,每页显示
			 <select name="pageSize">
				<option value="5" <c:if test="${page.pageSize==5 }">selected</c:if>>5</option>
				<option value="10" <c:if test="${page.pageSize==10 }">selected</c:if>>10</option>
			</select> 
			条 
			<c:if test="${page.pageCode > 1 }">
			[<A href="javascript:to_page(${page.pageCode-1})">前一页</A>] 
			</c:if>
			<B>${page.pageCode}</B>
			<c:if test="${page.pageCode < page.totalPage }">
			[<A href="javascript:to_page(${page.pageCode+1})">后一页</A>] 
			</c:if>
			到 
			<input type="text" id="page" name="pageCode" /> 
			页
			<input type="button" value="Go" onclick="to_page()" />
		</div>
	</form>
</BODY>

<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript">
	function to_page(page) {
		if (page) {
			$("#page").val(page);
		}
		document.templateForm.submit();
	}
	
	$.ajax({
		type:"get",
		url:"${pageContext.request.contextPath}/direction_findAll",
		success:function(data){
			//console.log(data);
			$(data).each(function(i,n){
				$("#directions").append("<option value='"+ n.direction_id +"'>"+n.direction_name+"</option>");
			})
		}
	})
	
</script>

</HTML>


</body>
</html>