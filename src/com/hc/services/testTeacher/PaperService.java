package com.hc.services.testTeacher;

import java.util.List;

import com.hc.bean.testTeacher.Paper;

public interface PaperService {
	public void insert(Paper paper);
	public List<Paper> findAll();
	public void delect(Paper paper);
	public void update(Paper paper);
	public Paper fineById(int id);
	public List<Paper> findByIdList(List idlist);
}
