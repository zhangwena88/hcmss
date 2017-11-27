package com.hc.dao;

import java.util.List;

import com.hc.bean.Plan;

public class PlanDaoImpl extends BaseDaoImpl<Plan> implements PlanDao{

	@Override
	public List<Plan> findByGroupId(Integer group_id) {
		
		return (List<Plan>) this.getHibernateTemplate().find("from Plan where group_id = ? order by plan_start_date asc", group_id);
	}

	@Override
	public List<Plan> findBiggerIdAndGroupId(Integer plan_id, Integer group_id) {
		
		return (List<Plan>) this.getHibernateTemplate().find("from Plan where plan_id > ? and group_id = ?",plan_id , group_id);
	}

}
