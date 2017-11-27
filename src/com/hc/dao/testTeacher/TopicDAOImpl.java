package com.hc.dao.testTeacher;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.hc.bean.testTeacher.Topic;

public class TopicDAOImpl extends HibernateDaoSupport implements TopicDAO{

	@Override
	public void insert(Topic topic) {
		this.getHibernateTemplate().save(topic);
		
	}

	@Override
	public void delect(Topic topic) {
		this.getHibernateTemplate().delete(topic);
		
	}

	@Override
	public void update(Topic topic) {
		this.getHibernateTemplate().update(topic);
		
	}

	@Override
	public List<Topic> findByAll() {
		List<Topic> list = (List<Topic>) this.getHibernateTemplate().find("from Topic");
		return list;
	}
	
	
	
}
