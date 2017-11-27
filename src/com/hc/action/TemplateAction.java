package com.hc.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.hc.bean.PageBean;
import com.hc.bean.Template;
import com.hc.services.TemplateService;
import com.hc.util.ExcelUtil;
import com.hc.util.FastJsonUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class TemplateAction extends ActionSupport implements ModelDriven<Template>{

	private Template template = new Template();
	@Override
	public Template getModel() {
		return template;
	}

	private TemplateService templateService;
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

	
	// 需要做文件上传
	private File upload;
	private String uploadFileName;
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	
	private ExcelUtil excelUtil;
	public void setExcelUtil(ExcelUtil excelUtil) {
		this.excelUtil = excelUtil;
	}
	
	
	public String save(){
		
		System.out.println("进来了");
		System.out.println("template--->"+template);
		
		if (uploadFileName != null) {
			
			System.out.println("文件名2---->"+ uploadFileName);
			
			List<Object> list = new ArrayList<>();
			try {
				list = excelUtil.importListFromExcel(new Template(), new FileInputStream(upload), uploadFileName);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			System.out.println("-----------");
			System.out.println(list);
			for (Object object : list) {
				System.out.println(object);
			}
			System.out.println("-----------");
			
			for (Object object : list) {
				Template t = (Template)object;
				t.setDirection_id(template.getDirection_id());
				t.setTemplate_name(template.getTemplate_name());
				
				templateService.save(t);
			}
		}else{
			
			System.out.println("文件空");
		}
		
		return "success";
	}
	
	public String add(){
		return "add";
	}
	
	public String findTemplateName(){
		
		List<Template> list = templateService.findTemplateName();
		
		String jsonString = FastJsonUtil.toJSONString(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
		
		return NONE;
	}
	
	
	
	// 属性驱动
	// 当前页
	private Integer pageCode = 1;
	public void setPageCode(Integer pageCode) {
		if (pageCode == null) {
			pageCode = 1;
		}
		this.pageCode = pageCode;
	}

	// 每页显示的条数
	private Integer pageSize = 5;
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public String findNameByPage(){
		
		// 创建离线查询对象
		DetachedCriteria criteria = DetachedCriteria.forClass(Template.class);

		// 拼接查询条件
		// 用方向查
		Integer direction_id = template.getDirection_id();
		if (direction_id != null && direction_id > 0) {
			criteria.add(Restrictions.eq("direction_id", direction_id));
		}

		//用模板名查询
		String template_name = template.getTemplate_name();
		if (template_name != null && !template_name.trim().isEmpty()) {
			criteria.add(Restrictions.like("template_name", "%"+template_name+"%"));
		}

		//System.out.println(criteria);
		
		PageBean<Template> page = templateService.findNameByPage(pageCode, pageSize, criteria);
		
		// 获取值栈
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("page", page);
		return "list"; 
	}
	
	public String deleteByName() throws UnsupportedEncodingException{
		
		String name = new String(template.getTemplate_name().getBytes("ISO-8859-1"),"utf-8");
		System.out.println("name---->"+name);
		
		List<Template> list = templateService.findByName(name);
		for (Template t : list) {
			templateService.delete(t);
		}
		
		return "listAgain";
	}
	
	public String initUpdateByName() throws UnsupportedEncodingException{
		
		ValueStack vs = ActionContext.getContext().getValueStack();
		
		String name = new String(template.getTemplate_name().getBytes("ISO-8859-1"),"utf-8");
		//System.out.println("1name-->"+name);
		
		List<Template> list = templateService.findByName(name);
		//System.out.println(list);
		
		vs.set("templateName", name);
		vs.set("templateList", list);
		
		return "edit";
	}
	
	public String update(){
		/*System.out.println("2 :"+template);
		System.out.println(template.getTemplate_content());
		System.out.println(template.getDirection_id());*/
		
		templateService.update(template);
		
		List<Template> list = templateService.findByName(template.getTemplate_name());
		
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("templateName", template.getTemplate_name());
		vs.set("templateList", list);
		
		return "edit";
	}
			
}
