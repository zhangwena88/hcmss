package com.hc.services;

import java.util.List;

import com.hc.bean.Plan;

public interface PlanService {

	public void save(Plan plan);

	public List<Plan> findByGroupId(Integer group_id);

	public void update(Plan p);

	public List<Plan> findBiggerIdAndGroupId(Integer plan_id, Integer group_id);

	public Plan findById(Integer plan_id);

	public void deleteByGroupId(Integer group_id);
}
