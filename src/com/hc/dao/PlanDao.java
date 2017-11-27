package com.hc.dao;

import java.util.List;

import com.hc.bean.Plan;

public interface PlanDao extends BaseDao<Plan>{

	public List<Plan> findByGroupId(Integer group_id);

	public List<Plan> findBiggerIdAndGroupId(Integer plan_id, Integer group_id);

}
