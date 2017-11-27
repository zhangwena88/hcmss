package com.hc.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hc.bean.Direction;
import com.hc.dao.DirectionDao;

@Transactional
public class DirectionServicesImpl implements DirectionServices{

	private DirectionDao directionDao;
	public void setDirectionDao(DirectionDao templateDao) {
		this.directionDao = templateDao;
	}
	
	@Override
	public List<Direction> findAll() {
		List<Direction> list = directionDao.findAll();
		return list;
	}
	
}
