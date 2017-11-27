package com.hc.services.testTeacher;

import java.util.List;

import com.hc.bean.testTeacher.Topic;

public interface TopicService {
	public void insert(Topic topic);
	public void delect(Topic topic);
	public void update(Topic topic);
	public List<Topic> findByAll();
}
