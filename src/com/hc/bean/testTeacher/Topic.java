package com.hc.bean.testTeacher;

public class Topic {
	private int topic_id;
	private String topic_choice;
	private int topic_subject;
	private int teacher_id;
	public int getTopic_id() {
		return topic_id;
	}
	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}
	public String getTopic_choice() {
		return topic_choice;
	}
	public void setTopic_choice(String topic_choice) {
		this.topic_choice = topic_choice;
	}
	public int getTopic_subject() {
		return topic_subject;
	}
	public void setTopic_subject(int topic_subject) {
		this.topic_subject = topic_subject;
	}
	public int getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}
	@Override
	public String toString() {
		return "Topic [topic_id=" + topic_id + ", topic_choice=" + topic_choice + ", topic_subject=" + topic_subject
				+ ", teacher_id=" + teacher_id + "]";
	}
	
	
}
