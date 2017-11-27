package com.hc.services.testTeacher;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hc.bean.testTeacher.Paper;
import com.hc.dao.testTeacher.PaperDAO;

@Transactional
public class PaperServiceImpl implements PaperService {
	private PaperDAO paperDao ; 
	

	public PaperDAO getPaperDao() {
		return paperDao;
	}

	public void setPaperDao(PaperDAO paperDao) {
		this.paperDao = paperDao;
	}

	@Override
	public void insert(Paper paper) {
		paperDao.insert(paper);
		
	}

	@Override
	public List<Paper> findAll() {
		
		return paperDao.findAll();
	}

	@Override
	public void delect(Paper paper) {
		paperDao.delect(paper);
		
	}

	@Override
	public void update(Paper paper) {
		paperDao.update(paper);
		
	}

	@Override
	public Paper fineById(int id) {
		
		return paperDao.fineById(id);
	}

	@Override
	public List<Paper> findByIdList(List idlist) {
		// TODO Auto-generated method stub
		return paperDao.findByIdList(idlist);
	}

}
