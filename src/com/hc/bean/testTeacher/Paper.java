package com.hc.bean.testTeacher;

public class Paper {
	private int paper_id;
	private String paper_subject;
	private String paper_topic;
	private String paper_a;
	private String paper_b;
	private String paper_c;
	private String paper_d;
	private String paper_e;
	private String paper_correct;
	public int getPaper_id() {
		return paper_id;
	}
	public void setPaper_id(int paper_id) {
		this.paper_id = paper_id;
	}
	public String getPaper_subject() {
		return paper_subject;
	}
	public void setPaper_subject(String paper_subject) {
		this.paper_subject = paper_subject;
	}
	public String getPaper_topic() {
		return paper_topic;
	}
	public void setPaper_topic(String paper_topic) {
		this.paper_topic = paper_topic;
	}
	public String getPaper_a() {
		return paper_a;
	}
	public void setPaper_a(String paper_a) {
		this.paper_a = paper_a;
	}
	public String getPaper_b() {
		return paper_b;
	}
	public void setPaper_b(String paper_b) {
		this.paper_b = paper_b;
	}
	public String getPaper_c() {
		return paper_c;
	}
	public void setPaper_c(String paper_c) {
		this.paper_c = paper_c;
	}
	public String getPaper_d() {
		return paper_d;
	}
	public void setPaper_d(String paper_d) {
		this.paper_d = paper_d;
	}
	public String getPaper_e() {
		return paper_e;
	}
	public void setPaper_e(String paper_e) {
		this.paper_e = paper_e;
	}
	public String getPaper_correct() {
		return paper_correct;
	}
	public void setPaper_correct(String paper_correct) {
		this.paper_correct = paper_correct;
	}
	@Override
	public String toString() {
		return "Paper [paper_id=" + paper_id + ", paper_subject=" + paper_subject + ", paper_topic=" + paper_topic
				+ ", paper_a=" + paper_a + ", paper_b=" + paper_b + ", paper_c=" + paper_c + ", paper_d=" + paper_d
				+ ", paper_e=" + paper_e + ", paper_correct=" + paper_correct + "]";
	}
	
	
}
