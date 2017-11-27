package com.hc.action.testTeacher;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import com.hc.bean.testTeacher.Topic;
import com.hc.dao.testTeacher.TopicDAO;
import com.hc.services.testTeacher.TopicService;
import com.hc.util.FastJsonUtil;
import com.opensymphony.xwork2.ActionSupport;

public class TopicAction extends ActionSupport {
	private Topic topic;
	private TopicService topicService;
	
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	
	
	public TopicService getTopicService() {
		return topicService;
	}
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
	@Action("/topic/insert")
	public void insert() throws UnsupportedEncodingException
	{
		
		topic.setTopic_choice( new String(topic.getTopic_choice().getBytes("ISO-8859-1"),"utf-8"));
		System.out.println(topic);
		topicService.insert(topic);
	}
	
	@Action("/topic/findAll")
	public void findAll()
	{
		List<Topic> list = topicService.findByAll();
		System.out.println(list);
		String jsonString = FastJsonUtil.toJSONString(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
	}
	
	@Action("/topic/update")
	public void update() throws UnsupportedEncodingException
	{
		topic.setTopic_choice( new String(topic.getTopic_choice().getBytes("ISO-8859-1"),"utf-8"));
		System.out.println(topic);
		topicService.update(topic);
	}
	
	@Action("/topic/delect")
	public void delect() throws UnsupportedEncodingException
	{
		topic.setTopic_choice( new String(topic.getTopic_choice().getBytes("ISO-8859-1"),"utf-8"));
		System.out.println(topic);
		topicService.delect(topic);
	}
}
