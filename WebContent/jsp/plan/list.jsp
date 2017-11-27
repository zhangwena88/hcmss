<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add</title>
</head>
<body>
	
	<form action="plan_setTeacher">
		<input type="hidden" name="group_id" value="${plan.group_id}">
		请设置负责的老师：<select id="teachers" name="teacher_id"></select>
		<input type="submit" value="设置">
	</form>
		
	<c:forEach items="${list }" var="plan">
			<div>
				<form action="plan_update.action" method="post">
					<input type="hidden" name="plan_id" value="${plan.plan_id}">
					开始时间：
					<input type="date" class="dateInput" name="plan_start_date" value="<fmt:formatDate value="${plan.plan_start_date}" type="date"/>">
					结束时间：
					<input type="date" class="dateInput" name="plan_end_date" value="<fmt:formatDate value="${plan.plan_end_date}" type="date"/>">
					<br/>
					教学计划内容：
					<textarea >
						${plan.plan_content}
					</textarea>
					<input type="hidden" name="group_id" value="${plan.group_id}">
					<input type="hidden" name="teacher_id" value="${plan.teacher_id}">
					<input type="submit" value="修改">
				</form>
			</div>
	</c:forEach>
		
</body>

<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8">
	
	$("#teachers").change(function(){
		console.log($("#teachers").val());
	})	
	
	$(".dateInput").change(function(){
		console.log($(".dateInput").val());
		console.log($(".dateInput").parent());
		
		$(this).parent().submit();
	})
	
	/* $.ajax({
		type:"get",
		url:"${pageContext.request.contextPath}/teacher_findAll",
		success:function(data){
			//console.log(data);
			
			$(data).each(function(i,n){
				$("#teachers").append("<option value='"+ n.teacher_id +"'>"+n.teacher_name+"</option>");
			})
		}
	}) */
	
	
</script>

</html>