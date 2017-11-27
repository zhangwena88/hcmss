package com.hc.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.hc.bean.PageBean;


/**
 *  以后所有的Dao层的实现类都可以继承
 * @author Administrator
 * @param <T>
 */
@SuppressWarnings("all")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	//定义成员属性
	private Class clazz;
	
	public BaseDaoImpl(){
		//this表示的是子类  ,加载子类的时候父类也会被加载 ，所以这里的this代表子类
		Class c = this.getClass();
		
		//获取父类    Type是一个接口 他的实现类就是class对象
		//type 是一个空接口，他有很多子接口，我们操作的是子接口
		Type type = c.getGenericSuperclass();
		
		//把type接口转成子接口
		if(type instanceof ParameterizedType){
			
			ParameterizedType ptype = (ParameterizedType) type;
			
			//获取Customer 因为泛型可以有多个所以返回一个数组
			Type[] types = ptype.getActualTypeArguments();
			this.clazz = (Class) types[0];
		}
	}
	
	
	//增
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	//删
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}
	
	//改
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}
	
	//通过主键来查询
	public T findById(Integer id) {
		
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	//查询所有
	public List<T> findAll() {
		
		return (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());
	}
	
	//分页查询
	public PageBean<T> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		
		//创建分页对象
		PageBean<T> page = new PageBean<T>();
		
		//一个一个设置属性
		page.setPageCode(pageCode);
		page.setPageSize(pageSize);
		
		//查询 select count(*)
		criteria.setProjection(Projections.rowCount());
		
		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list != null && list.size() > 0){
			int totalCount = list.get(0).intValue();
			//总记录数
			page.setTotalCount(totalCount);
		}
		System.out.println(page.getTotalCount());
		
		//清除 sql 把聚合函数变为 select * from
		criteria.setProjection(null);
		List<T> beanList = (List<T>) this.getHibernateTemplate().findByCriteria(criteria, (pageCode-1)*pageSize,pageSize);
		
		//每页显示的数据
		page.setBeanList(beanList);
		return page;
	}


}
