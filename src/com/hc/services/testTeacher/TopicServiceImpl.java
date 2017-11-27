package com.hc.services.testTeacher;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hc.bean.testTeacher.Topic;
import com.hc.dao.testTeacher.TopicDAO;
@Transactional
public class TopicServiceImpl implements TopicService {
	private TopicDAO topicDao ; 
	
	
	public TopicDAO getTopicDao() {
		return topicDao;
	}


	public void setTopicDao(TopicDAO topicDao) {
		this.topicDao = topicDao;
	}


	@Override
	public void insert(Topic topic) {
		topicDao.insert(topic);
		
	}


	@Override
	public void delect(Topic topic) {
		topicDao.delect(topic);
		
	}


	@Override
	public void update(Topic topic) {
		topicDao.update(topic);
		
	}


	@Override
	public List<Topic> findByAll() {
		// TODO Auto-generated method stub
		return topicDao.findByAll();
	}

}
