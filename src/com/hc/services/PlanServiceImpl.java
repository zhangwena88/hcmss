package com.hc.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hc.bean.Plan;
import com.hc.dao.PlanDao;

@Transactional
public class PlanServiceImpl implements PlanService{

	private PlanDao planDao;
	public void setPlanDao(PlanDao planDao) {
		this.planDao = planDao;
	}
	
	@Override
	public void save(Plan plan) {
		planDao.save(plan);
	}

	@Override
	public List<Plan> findByGroupId(Integer group_id) {
		return planDao.findByGroupId(group_id);
	}

	@Override
	public void update(Plan p) {
		planDao.update(p);
	}

	@Override
	public List<Plan> findBiggerIdAndGroupId(Integer plan_id, Integer group_id) {
		return planDao.findBiggerIdAndGroupId(plan_id,group_id);
	}

	@Override
	public Plan findById(Integer plan_id) {
		return planDao.findById(plan_id);
	}

	@Override
	public void deleteByGroupId(Integer group_id) {
		
		List<Plan> list = planDao.findByGroupId(group_id);
		for (Plan plan : list) {
			planDao.delete(plan);
		}
	}

	
}
