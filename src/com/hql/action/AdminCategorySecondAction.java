	package com.hql.action;

import java.util.List;

import com.hql.entity.Category;

/**
 * 二级分类Action
 */

import com.hql.entity.CategorySecond;
import com.hql.service.AdminCategorySecondService;
import com.hql.service.CategoryService;
import com.hql.utils.pageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {
	
	//使用模型驱动
	private CategorySecond categorySecond=new CategorySecond();
	
	@Override
	public CategorySecond getModel() {
		
		return categorySecond;
	}
	
	//接收page
	private Integer page;
	
	public void setPage(Integer page) {
		this.page = page;
	}
	
	//注入service
	private AdminCategorySecondService adminCategorySecondService;

	public void setAdminCategorySecondService(AdminCategorySecondService adminCategorySecondService) {
		this.adminCategorySecondService = adminCategorySecondService;
	}
	
	//注入一级分类service
	private CategoryService categoryService;
	
	

	
	


	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	//查询所有二级分类
	public String findAll(){
		
		pageBean<CategorySecond> pageBean=adminCategorySecondService.
				findByPage(page);
		
		//存入值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findAll";
	}
	
	//跳转到添加页面
	public String addPage(){
		//查询一级分类
		List<Category> cList = categoryService.findAll();
		
		//存入值栈
		ActionContext.getContext().getValueStack().set("cList", cList);
		
		
		return "addPage";
	}
	
	//删除二级分类
	public String delete(){
		
		//级联查询
		categorySecond=adminCategorySecondService.findByCsid(categorySecond.getCsid());
		
		adminCategorySecondService.delete(categorySecond);
		
		return "delete";
	}
	
	// 添加二级分类的方法:
		public String save() {
			adminCategorySecondService.save(categorySecond);
			return "save";
		}
		
		
	//修改二级分类
		public String edit(){
			
			//查询二级分类
			categorySecond = adminCategorySecondService.findByCsid(categorySecond.getCsid());
			
			//查询一级分类
			List<Category> cList = categoryService.findAll();
			
			//存入值栈
			ActionContext.getContext().getValueStack().set("cList", cList);
			return "edit";
		}
		
		
		//修改
		public String update(){
			
			adminCategorySecondService.update(categorySecond);
			
			return "update";
			
		}

	

}
