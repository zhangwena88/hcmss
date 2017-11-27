package com.hc.bean;

public class Template {

	/*
	 * CREATE TABLE `template` (
		`template_id` int(11) NOT NULL auto_increment,
		`template_week` int(3) NOT NULL COMMENT '第几周',
		`template_name` varchar(20) NOT NULL COMMENT '模板名',
		`template_content` varchar(60) NOT NULL COMMENT '内容',
		`direction_id` int(11) NOT NULL COMMENT '方向外键',
		 PRIMARY KEY  (`template_id`)
		) ENGINE=MyISAM 
		DEFAULT CHARSET=utf8;
	 */
	
	private Integer template_id;
	private String template_name;
	private Integer template_week;
	private String template_content;
	private Integer direction_id;
	
	public Integer getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(Integer template_id) {
		this.template_id = template_id;
	}
	public Integer getTemplate_week() {
		return template_week;
	}
	public void setTemplate_week(Integer template_week) {
		this.template_week = template_week;
	}
	public String getTemplate_name() {
		return template_name;
	}
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}
	public String getTemplate_content() {
		return template_content;
	}
	public void setTemplate_content(String template_content) {
		this.template_content = template_content;
	}
	public Integer getDirection_id() {
		return direction_id;
	}
	public void setDirection_id(Integer direction_id) {
		this.direction_id = direction_id;
	}
	
	
	@Override
	public String toString() {
		return "Template [template_id=" + template_id + ", template_week=" + template_week + ", template_name="
				+ template_name + ", template_content=" + template_content + ", direction_id=" + direction_id + "]";
	}
	
}
