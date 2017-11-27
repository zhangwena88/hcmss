package com.hc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.hc.bean.Plan;
import com.hc.bean.Template;
import com.hc.services.PlanService;
import com.hc.services.TemplateService;
import com.hc.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class PlanAction extends ActionSupport implements ModelDriven<Plan>{

	private Plan plan = new Plan();
	public Plan getModel() {
		return plan;
	}

	private PlanService planService;
	public void setPlanService(PlanService planService) {
		this.planService = planService;
	}
	
	private TemplateService templateService;
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

	private DateUtil dateUtil;
	public void setDateUtil(DateUtil dateUtil) {
		this.dateUtil = dateUtil;
	}
	
	//班级生成的时候调用的方法
	//班级生成的时候要根据班级生成表   要拿到班级ID
	//老师ID在改的时候再加
	public void save(Date startDate,Integer direction_id,Integer group_id){
		
		String name = templateService.findNameByDirectionId(direction_id);
		List<Template> list = templateService.findByName(name);
		
		Date date = startDate;
		
		for (Template template : list) {
			
			Plan plan = new Plan();
			
			plan.setPlan_content(template.getTemplate_content());
			plan.setGroup_id(group_id);
			
			plan.setPlan_start_date(date);
			date = dateUtil.getEndDate(date);
			plan.setPlan_end_date(date);
			
			System.out.println(plan);
			planService.save(plan);
			
			date = dateUtil.getNextStartDate(date);
		}
	}
	
	public String select(){
		return "select";
	}
	
	public String lists(){
		
		System.out.println("进来lists的group_id："+plan.getGroup_id());
		
		Integer group_id = plan.getGroup_id();
		
		List<Plan> list = planService.findByGroupId(group_id);
		
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("list", list);
		
		return "list";
	}
	
	public String setTeacher(){
		
		Integer teacher_id = plan.getTeacher_id();
		System.out.println("teacher_id-->"+teacher_id);
		
		Integer group_id = plan.getGroup_id();
		System.out.println("group_id-->"+group_id);
		
		List<Plan> list = planService.findByGroupId(group_id);
		for (Plan p : list) {
			p.setTeacher_id(teacher_id);
			planService.update(p);
		}
		
		System.out.println(list);
		
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("list", list);
		
		return "list";
		
	}
	
	public String update(){
		
		System.out.println("传进来update的plan--> "+plan);
		
		Integer group_id = plan.getGroup_id();
		
		Integer plan_id = plan.getPlan_id();
		Plan oldPlan = planService.findById(plan_id);
		
		if (plan.getPlan_start_date().toString().equals(oldPlan.getPlan_start_date().toString())) {
			System.out.println("开始时间相同");
			
			if (plan.getPlan_end_date().toString().equals(oldPlan.getPlan_end_date().toString())) {
				System.out.println("时间都相同");
				
				planService.update(plan);
				
			}else{
				System.out.println("结束时间改变");
				
				planService.update(plan);
				
				Date d = plan.getPlan_end_date();
				
				List<Plan> li = planService.findBiggerIdAndGroupId(plan_id, group_id);
				System.out.println("li--->"+li);
				
				d = dateUtil.getNextStartDate(d);
				
				for (Plan newPlan : li) {
					
					newPlan.setPlan_start_date(d);
					d = dateUtil.getEndDate(d);
					newPlan.setPlan_end_date(d);
					
					planService.update(newPlan);
					
					List<Plan> list = planService.findByGroupId(group_id);
					
					ValueStack vs = ActionContext.getContext().getValueStack();
					vs.set("list", list);
				}
			}
			
		}else{
			System.out.println("开始时间变化");
			
			Date newEndDate = dateUtil.getEndDate(plan.getPlan_start_date());
			plan.setPlan_end_date(newEndDate);
			planService.update(plan);
			
			List<Plan> li = planService.findBiggerIdAndGroupId(plan_id, group_id);
			System.out.println("li--->"+li);
			
			Date d = dateUtil.getNextStartDate(newEndDate);
			
			for (Plan newPlan : li) {
				
				newPlan.setPlan_start_date(d);
				d = dateUtil.getEndDate(d);
				newPlan.setPlan_end_date(d);
				
				planService.update(newPlan);
				
				List<Plan> list = planService.findByGroupId(group_id);
				
				ValueStack vs = ActionContext.getContext().getValueStack();
				vs.set("list", list);
			}
			
		}
		
		List<Plan> list = planService.findByGroupId(group_id);
		
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("list", list);
		
		return "list";
	}
	
	//给删除班级的时候调用的方法
	public void deleteByGroupId(Integer group_id){
		planService.deleteByGroupId(group_id);
	}
	
	//修改班级方向信息的时候调用的方法
	public void updateDirection(Integer group_id,Date startDate,Integer direction_id){
		planService.deleteByGroupId(group_id);
		this.save(startDate, direction_id, group_id);
	}
}
