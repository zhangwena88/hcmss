package com.hc.bean;

public class Direction {

	/*
	 * CREATE TABLE `direction` (
		`direction_id` int(11) NOT NULL auto_increment,
		`direction_name` varchar(20) NOT NULL COMMENT '方向字典',
		PRIMARY KEY  (`direction_id`)
		) ENGINE=MyISAM DEFAULT CHARSET=utf8;
	 */
	private Integer direction_id;
	private String direction_name;
	
	public Integer getDirection_id() {
		return direction_id;
	}
	public void setDirection_id(Integer direction_id) {
		this.direction_id = direction_id;
	}
	public String getDirection_name() {
		return direction_name;
	}
	public void setDirection_name(String direction_name) {
		this.direction_name = direction_name;
	}
	
	@Override
	public String toString() {
		return "Direction [direction_id=" + direction_id + ", direction_name=" + direction_name + "]";
	}
	
}
