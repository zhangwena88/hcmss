package com.hc.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.hc.bean.Direction;
import com.hc.services.DirectionServices;
import com.hc.util.FastJsonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DirectionAction extends ActionSupport implements ModelDriven<Direction>{

	private Direction direction = new Direction();
	@Override
	public Direction getModel() {
		return direction;
	}

	private DirectionServices directionServices;
	public void setDirectionServices(DirectionServices directionServices) {
		this.directionServices = directionServices;
	}
	
	public String findAll(){
		
		List<Direction> list = directionServices.findAll();
		
		//System.out.println(list);
		
		String jsonString = FastJsonUtil.toJSONString(list);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		FastJsonUtil.write_json(response, jsonString);
		
		return NONE;
	}
	
}
