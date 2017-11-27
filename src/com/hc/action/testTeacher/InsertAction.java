package com.hc.action.testTeacher;



import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import com.hc.bean.testTeacher.Paper;
import com.hc.services.testTeacher.PaperService;
import com.hc.util.FastJsonUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

public class InsertAction extends ActionSupport{
	private Paper paper;
	private PaperService paperService;
	
	public PaperService getPaperService() {
		return paperService;
	}

	public void setPaperService(PaperService paperService) {
		this.paperService = paperService;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	@Override
	public String execute() throws Exception {
		
		paper.setPaper_subject( new String(paper.getPaper_subject().getBytes("ISO-8859-1"),"utf-8"));
		paper.setPaper_topic(new String(paper.getPaper_topic().getBytes("ISO-8859-1"),"utf-8"));
		paper.setPaper_a( new String(paper.getPaper_a().getBytes("ISO-8859-1"),"utf-8"));
		paper.setPaper_b( new String(paper.getPaper_b().getBytes("ISO-8859-1"),"utf-8"));
		paper.setPaper_c( new String(paper.getPaper_c().getBytes("ISO-8859-1"),"utf-8"));
		paper.setPaper_d( new String(paper.getPaper_d().getBytes("ISO-8859-1"),"utf-8"));
		paper.setPaper_e( new String(paper.getPaper_e().getBytes("ISO-8859-1"),"utf-8"));
		System.out.println(paper);
		paperService.insert(paper);
		HttpServletResponse response = ServletActionContext.getResponse();
		/*HttpServletRequest hsr = ServletActionContext.getRequest();
		hsr.getRequestDispatcher("/testOnline.jsp").forward(hsr, response);*/
		response.sendRedirect("/hcms/testOnline.jsp");
		return null;
	}
	
	@Action("findAll")
	public void findAll()
	{
		List<Paper> list = paperService.findAll();
		System.out.println(list);
		String jsonString = FastJsonUtil.toJSONString(list);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		FastJsonUtil.write_json(response, jsonString);
	
	/*	ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("list", list);*/
	
	}
	@Action("update")
	public void update() throws UnsupportedEncodingException
	{
	
		paper.setPaper_subject( new String(paper.getPaper_subject().getBytes("ISO-8859-1"),"utf-8"));
		paper.setPaper_topic(new String(paper.getPaper_topic().getBytes("ISO-8859-1"),"utf-8"));
		paper.setPaper_a( new String(paper.getPaper_a().getBytes("ISO-8859-1"),"utf-8"));
		paper.setPaper_b( new String(paper.getPaper_b().getBytes("ISO-8859-1"),"utf-8"));
		paper.setPaper_c( new String(paper.getPaper_c().getBytes("ISO-8859-1"),"utf-8"));
		paper.setPaper_d( new String(paper.getPaper_d().getBytes("ISO-8859-1"),"utf-8"));
		paper.setPaper_e( new String(paper.getPaper_e().getBytes("ISO-8859-1"),"utf-8"));
		System.out.println(paper);
		paperService.update(paper);
		System.out.println("更新完成");
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, "更新完成");
	}
	
	
	@Action("delect")
	public void delect()
	{
		System.out.println(paper);
		paperService.delect(paper);
		System.out.println("删除完成");
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, "删除完成");
	}
	
	
}
