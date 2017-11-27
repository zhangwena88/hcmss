<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add</title>
</head>
<body>

	<form action="template_save.action" method="POST" enctype="multipart/form-data" onsubmit="return check()">
		教学方向：<select id="directions" name="direction_id"></select>
		<br/>
		教学计划模板名：<input type="text" name="template_name" id="template_name"><span id="checkP" style="color:red"></span>
		<br/>
		文件：<input type="file" name="upload" id="files"><span id="checkF" style="color:red"></span>
		<br/>
		<input type="submit" value="提交" />
	</form>
	
</body>

<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8">
	
	function checkName(){
		var name = $("#template_name").val();
		if ($("#template_name").val().length < 1) {
			$("#checkP").text("请填写模板名");
			return;
		}else{
			$("#checkP").text("");
		}
		
		$.ajax({
			type:"get",
			url:"${pageContext.request.contextPath}/template_findTemplateName",
			async:true,
			success:function(data){
				for (var i = 0; i < data.length; i++) {
					if (data[i] == name) {
						$("#checkP").text("已有此模板");
						return;
					}else if(data[i] != name && i == data.length-1){
						$("#checkP").text("");
					}
				}
			}
		});
	}

	function checkFile(){
		//console.log($("#files").val().length);
		if ($("#files").val().length < 1) {
			$("#checkF").text("请选择文件");
		}else{
			$("#checkF").text("");
		}
	}
	
	$("#template_name").blur(function(){
		checkName();
	})
	
	function check() {
		checkName();
		checkFile();
		if ($("#checkF").text().length < 1) {
			if ($("#checkP").text().length > 1) {
				return false;
			}else{
				return true;
			}
		}else{
			return false;
		}
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

</html>