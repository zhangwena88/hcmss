package com.hc.bean;

import java.util.Date;


public class Plan {

	/*
	 CREATE TABLE `plan` (
	`plan_id` int(11) NOT NULL auto_increment,
	`plan_start_date` date NOT NULL COMMENT '计划开始时间',
	`plan_end_date` date NOT NULL COMMENT '计划结束时间',
	`plan_content` varchar(30) default NULL COMMENT '教学内容概述',
	`group_id` int(11) NOT NULL COMMENT '外键班级表',
	`teacher_id` int(11) NOT NULL COMMENT '外键老师表',
	PRIMARY KEY  (`plan_id`)
	) ENGINE=MyISAM DEFAULT CHARSET=utf8;
	 */
	
	private Integer plan_id;
	
	private Date plan_start_date;
	private Date plan_end_date;
	private String plan_content;
	
	private Integer group_id;
	private Integer teacher_id;
	
	public Integer getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(Integer plan_id) {
		this.plan_id = plan_id;
	}
	public Date getPlan_start_date() {
		return plan_start_date;
	}
	public void setPlan_start_date(Date plan_start_date) {
		this.plan_start_date = plan_start_date;
	}
	public Date getPlan_end_date() {
		return plan_end_date;
	}
	public void setPlan_end_date(Date plan_end_date) {
		this.plan_end_date = plan_end_date;
	}
	public String getPlan_content() {
		return plan_content;
	}
	public void setPlan_content(String plan_content) {
		this.plan_content = plan_content;
	}
	public Integer getGroup_id() {
		return group_id;
	}
	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}
	public Integer getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(Integer teacher_id) {
		this.teacher_id = teacher_id;
	}
	
	@Override
	public String toString() {
		return "Plan [plan_id=" + plan_id  + ", plan_start_date="
				+ plan_start_date + ", plan_end_date=" + plan_end_date + ", plan_content=" + plan_content
				+ ", group_id=" + group_id + ", teacher_id=" + teacher_id + "]";
	}
	
	
}
