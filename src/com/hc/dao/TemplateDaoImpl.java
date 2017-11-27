package com.hc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import com.hc.bean.PageBean;
import com.hc.bean.Template;

public class TemplateDaoImpl extends BaseDaoImpl<Template> implements TemplateDao {

	/*@Override
	public void save(Template template) {
		
		this.getHibernateTemplate().save(template);
	}

	@Override
	public List<Template> findAll() {
		
		return (List<Template>) this.getHibernateTemplate().find("from Template");
	}*/

	@Override
	public List<Template> findTemplateName() {
		
		return (List<Template>) this.getHibernateTemplate().find("select distinct t.template_name from Template t");
		
	}

	
	public PageBean<Template> findNameByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		
		//创建分页对象
		PageBean<Template> page = new PageBean<Template>();
		
		//一个一个设置属性
		page.setPageCode(pageCode);
		page.setPageSize(pageSize);
		
		//distinct唯一查询 select count( distinct template_name) from template;
		criteria.setProjection(Projections.countDistinct("template_name"));
		
		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list != null && list.size() > 0){
			int totalCount = list.get(0).intValue();
			//总记录数
			page.setTotalCount(totalCount);
		}
		//System.out.println(page.getTotalCount());
		
		//清除 sql 把聚合函数变为 select * from
		criteria.setProjection(null);
		
		String str = "template_name";
		criteria.setProjection(Projections.distinct(Projections.property(str)));
		//System.out.println(criteria);
		
		List<Template> beanList = new ArrayList<>();
		List<String> li = (List<String>) this.getHibernateTemplate().findByCriteria(criteria, (pageCode-1)*pageSize,pageSize);
		for (String s : li) {
			if (s != null && s.trim().length() > 0) {
				Template t = new Template();
				t.setTemplate_name(s);
				beanList.add(t);
			}
		}
		
		//每页显示的数据
		page.setBeanList(beanList);
		return page;
	}


	@Override
	public List<Template> findByName(String name) {
		List<Template> list = (List<Template>) this.getHibernateTemplate().find("from Template where template_name = ?", name);
		return list;
	}
	
	
	public String findNameByDirectionId(Integer direction_id){
		List<Template> list = (List<Template>) this.getHibernateTemplate().find("from Template where direction_id = ?", direction_id);
		return list.get(0).getTemplate_name();
	}
}
