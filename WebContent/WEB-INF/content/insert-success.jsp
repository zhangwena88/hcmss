<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	*{margin:0;padding:0;}
	#selectQuestionBox
	{
		margin-left:50px;
		margin-top:50px
	}
	#questionTable
	{
		
		border:1px solid black;
	}
	#questionTable td
	{
		width: 100px;
		text-align: center;
		font-size: 18px;
		border:1px solid black;
		border-left: none;
		border-bottom:none;
	}
	#questionTable th
	{
	
		border-right:1px solid black;
	}
	td>textarea
	{
		width: 90px;
		border:none;
		margin:0 auto;
		text-align: center;
	}
</style>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
		<script src="https://unpkg.com/vue" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$(function(){
				var vm = new Vue({
					el:"#app",
					data:{
						showaddQuestionBox:true,
						showselectQuestionBox:false,
						questionList:[],
						topicList:[]
					},
					methods:
					{
						clickAddQuestion:function(){
							console.log(1);
							this.showaddQuestionBox = true;
							this.showselectQuestionBox = false;
						},
						
						clickSelectQuestion:function(){
							console.log(2);
							this.showaddQuestionBox = false;
							this.showselectQuestionBox = true;
							var url = "${pageContext.request.contextPath}/test-teacher/findAll.action";
							$.get(url,function(data){
								console.log(data);
								vm.questionList=data;
								console.log(vm.questionList[0].paper_a);
							},"json");
						},
						
						update:function(index){
							var url = "${pageContext.request.contextPath}/test-teacher/update.action";
							var item = vm.questionList[index];
							console.log(item);
							var param = {"paper.paper_subject":item.paper_subject,
										 "paper.paper_topic":item.paper_topic,
										 "paper.paper_a":item.paper_a,
										 "paper.paper_b":item.paper_b,
										 "paper.paper_c":item.paper_c,
										 "paper.paper_d":item.paper_d,
										 "paper.paper_e":item.paper_e,
										 "paper.paper_correct":item.paper_correct,
										 "paper.paper_id":item.paper_id
										};
							
							$.get(url,param,function(data){
								console.log(data);
								if(data ==  "更新完成")
									{
										confirm("更新已成功,请刷新！");
									}
							},"json");
						
							confirm("更新已成功请刷新！");
							/* var url2 = "${pageContext.request.contextPath}/test-teacher/findAll.action";
							$.get(url2,function(data){
								console.log(data);
								vm.questionList=data;
								console.log(vm.questionList[0].paper_a);
							},"json"); */
						},
						
						delect:function(index){
							var url = "${pageContext.request.contextPath}/test-teacher/delect.action";
							var item = vm.questionList[index];
							console.log(item);
							var param = {"paper.paper_subject":item.paper_subject,
										 "paper.paper_topic":item.paper_topic,
										 "paper.paper_a":item.paper_a,
										 "paper.paper_b":item.paper_b,
										 "paper.paper_c":item.paper_c,
										 "paper.paper_d":item.paper_d,
										 "paper.paper_e":item.paper_e,
										 "paper.paper_correct":item.paper_correct,
										 "paper.paper_id":item.paper_id
										};
							$.post(url,param,function(data){
								console.log(data);
								if(data ==  "删除完成")
								{
									confirm("删除已成功请刷新！");
								}
							},"json");
							
							confirm("删除已成功请刷新！");
						},
						
						addTopic:function(){
							var checkboxs = $("input:checkbox");
							$.each(checkboxs,function(index,value){
								if(value.checked)
								{
									 vm.topicList.push(value.value); 	
								}
							});
							var teacher_id = $("#teacher_id").val();
							var topic_subject = $("#topic_subject").val();
							console.log(teacher_id);
							console.log(topic_subject);
							var param = {"topic.topic_choice":vm.topicList.join(","),
									 "topic.teacher_id":teacher_id,
									 "topic.topic_subject":topic_subject
									};
							var url = "http://localhost:8080/hcms/topic/insert.action";
							$.post(url,param,function(){
								
							},"json");
							
						},
						renovate:function(){
							 var url = "${pageContext.request.contextPath}/test-teacher/findAll.action";
								$.get(url,function(data){
									console.log(data);
									vm.questionList=data;
									
								},"json");
						}
						
					}
				});
				
				
				
			});
		</script>
</head>
<body>
	<div id="app">
			<button id="addQuestion" @click="clickAddQuestion()" >新增试题</button>
			<button id="selectQuestion" @click="clickSelectQuestion()">选则试题并生成试卷</button>
			<div id="showBox">
				<div id="addQuestionBox" v-show="showaddQuestionBox">
					<form action="${pageContext.request.contextPath}/test-teacher/insert.action">
						考试科目：<input type="text" name="paper.paper_subject" placeholder="请输入考试科目"/>
						<br />
						试题题目：<textarea name="paper.paper_topic" placeholder="请输入试题题目" style=""></textarea>
							
						<br />
						选项A的描述：<textarea type="text" name="paper.paper_a" placeholder="请输入选项A的描述" ></textarea>
						<br />
						选项B的描述：<textarea type="text" name="paper.paper_b" placeholder="请输入选项B的描述" ></textarea>
						<br />
						选项C的描述：<textarea type="text" name="paper.paper_c"  placeholder="请输入选项C的描述" ></textarea>
						<br />
						选项D的描述：<textarea type="text" name="paper.paper_d"  placeholder="请输入选项D的描述" ></textarea>
						<br />
						选项E的描述：<textarea type="text" name="paper.paper_e"  placeholder="请输入选项E的描述" ></textarea>
						<br />
						正确答案：<input type="text" name="paper.paper_correct"  placeholder="请输入正确答案" />
						<input type="submit" value="提交">
					</form>
				</div>
				<div id="selectQuestionBox" v-show="showselectQuestionBox">
					<table id="questionTable" cellspacing="0">
						<tr>
							<th>考试科目</th>
							<th>试题题目</th>
							<th>选项A</th>
							<th>选项B</th>
							<th>选项C</th>
							<th>选项D</th>
							<th>选项E</th>
							<th>正确答案</th>
							<th>修改</th>
							<th>删除</th>
							<th style="border-right: none;">加入题库</th>
						</tr>
						<tr v-for="item,index in questionList">
							<td><textarea type="text" v-model="item.paper_subject" ></textarea></td>
							<td><textarea type="text" v-model="item.paper_topic" ></textarea></td>
							<td><textarea type="text" v-model="item.paper_a" ></textarea></td>
							<td><textarea type="text" v-model="item.paper_b" ></textarea></td>
							<td><textarea type="text" v-model="item.paper_c" ></textarea></td>
							<td><textarea type="text" v-model="item.paper_d" ></textarea></td>
							<td><textarea type="text" v-model="item.paper_e" ></textarea></td>
							<td><textarea type="text" v-model="item.paper_correct" ></textarea></td>
							<td><a @click="update(index)">修改</a></td>
							<td><a @click="delect(index)">删除</a></td>
							<td style="border-right: none;"><input type="checkbox"  :value="item.paper_id" name="addResult"/></td>
						</tr>
					</table>
					<form >
						试卷唯一标识：<input type="text" placeholder="请输入试卷唯一标识" id="topic_subject"/>(建议使用年月日时分的整形形式，如：1711231605)
						<br>
						教师ID：<input type="text" placeholder="请输入设题教师ID" id="teacher_id"/>
						<br>
						<input type="button" id="addToTopic" value="添加到试题" @click="addTopic()"/>
					</form>
					<input type="button" value="刷新" @click="renovate()"/>
				</div>
			</div>
		</div>
</body>
</html>