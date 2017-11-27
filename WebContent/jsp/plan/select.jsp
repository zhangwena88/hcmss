<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add</title>
</head>
<body>
	<form action="plan_lists.action" method="POST" >
		<select id="group_id" name="group_id"></select>
		<br/>
		<input type="submit" value="search">
	</form>
</body>

<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8">
	
	$("#group_id").change(function(){
		console.log($("#group_id").val());
	})	
	
	//写一个获取group_id的方法
	/*
	$.ajax({
		type:"get",
		url:"${pageContext.request.contextPath}/group_findAll",
		success:function(data){
			//console.log(data);
			
			$(data).each(function(i,n){
				$("#group_id").append("<option value='"+ n.group_id +"'>"+n.group_name+"</option>");
			})
		}
	}) */
	
	
	
</script>

</html>