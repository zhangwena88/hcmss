package com.hc.dao.testTeacher;

import java.util.List;

import com.hc.bean.testTeacher.Topic;

public interface TopicDAO {

	void insert(Topic topic);

	void delect(Topic topic);

	void update(Topic topic);

	List<Topic> findByAll();

}
