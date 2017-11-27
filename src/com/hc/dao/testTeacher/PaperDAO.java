package com.hc.dao.testTeacher;

import java.util.List;

import com.hc.bean.testTeacher.Paper;

public interface PaperDAO {

	void insert(Paper paper);

	List<Paper> findAll();

	void delect(Paper paper);

	void update(Paper paper);

	Paper fineById(int id);

	List<Paper> findByIdList(List idlist);

}
