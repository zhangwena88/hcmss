package com.hc.services;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.hc.bean.PageBean;
import com.hc.bean.Template;
import com.hc.dao.TemplateDao;

@Transactional
public class TemplateServiceImpl implements TemplateService{

	private TemplateDao templateDao;
	public void setTemplateDao(TemplateDao templateDao) {
		this.templateDao = templateDao;
	}

	@Override
	public void save(Template template) {
		templateDao.save(template);
	}

	@Override
	public List<Template> findAll() {
		return templateDao.findAll();
	}

	@Override
	public List<Template> findTemplateName() {
		return templateDao.findTemplateName();
	}

	@Override
	public PageBean<Template> findNameByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		return templateDao.findNameByPage(pageCode, pageSize, criteria);
	}

	@Override
	public void delete(Template template) {
		templateDao.delete(template);
	}

	@Override
	public List<Template> findByName(String name) {
		return templateDao.findByName(name);
	}

	@Override
	public void update(Template template) {
		templateDao.update(template);
	}

	@Override
	public String findNameByDirectionId(Integer direction_id) {
		return templateDao.findNameByDirectionId(direction_id);
	}

	/*@Override
	public PageBean<Template> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		
		return templateDao.findByPage(pageCode, pageSize, criteria);
	}*/
}
