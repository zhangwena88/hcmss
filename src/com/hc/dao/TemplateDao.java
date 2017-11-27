package com.hc.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.hc.bean.PageBean;
import com.hc.bean.Template;

public interface TemplateDao extends BaseDao<Template>{

	/*public void save(Template template);
	public List<Template> findAll();*/
	
	public List<Template> findTemplateName();
	
	public PageBean<Template> findNameByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);

	public List<Template> findByName(String name);
	
	public String findNameByDirectionId(Integer direction_id);
}
