package com.hc.services;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.hc.bean.PageBean;
import com.hc.bean.Template;

public interface TemplateService {

	public void save(Template template);
	public List<Template> findAll();
	
	public List<Template> findTemplateName();
	
	public PageBean<Template> findNameByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
	
	public void delete(Template template);
	
	public List<Template> findByName(String name);
	
	public void update(Template template);
	
	public String findNameByDirectionId(Integer direction_id);
}
