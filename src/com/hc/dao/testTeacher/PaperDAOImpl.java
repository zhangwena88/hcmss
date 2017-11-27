package com.hc.dao.testTeacher;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.hc.bean.testTeacher.Paper;

public class PaperDAOImpl extends HibernateDaoSupport implements PaperDAO {

	@Override
	public void insert(Paper paper) {
		this.getHibernateTemplate().save(paper);
		
	}

	@Override
	public List<Paper> findAll() {
		List<Paper> list = (List<Paper>) this.getHibernateTemplate().find("from Paper");
		return list;
	}

	@Override
	public void delect(Paper paper) {
		this.getHibernateTemplate().delete(paper);
		
	}

	@Override
	public void update(Paper paper) {
		this.getHibernateTemplate().update(paper);
		
	}

	@Override
	public Paper fineById(int id) {
		Paper paper = (Paper) this.getHibernateTemplate().find("from Paper where paper_id = ?", id);
		return paper;
	}

	@Override
	public List<Paper> findByIdList(List idlist) {
		List<Paper> list = (List<Paper>) this.getHibernateTemplate().find("from Paper where paper_id in ?",idlist);
		return list;
	}

}
