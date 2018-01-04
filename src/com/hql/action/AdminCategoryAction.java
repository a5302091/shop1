package com.hql.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.hql.entity.Category;
import com.hql.service.CategoryService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/*
 * 后台一级分类管理
 */

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category> {
	
	//使用模型驱动
	private Category category=new Category();

	@Override
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}
	
	//注入一级分类service
	private CategoryService categoryService;
	
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}



	//后台查询所有一级分类
	public String findAll(){
		
		List<Category> clist = categoryService.findAll();
	
		//存入值栈
		ActionContext.getContext().getValueStack().set("clist", clist);
		
		return "findAll";
	}
	
	//添加一级分类
	public String save(){
		
		categoryService.save(category);
		
		return "save";
	}
	
	//修改一级分类
	public String update(){
		categoryService.findByCid(category.getCid());
		
		//修改
		categoryService.update(category);
		
		return "update";
	}
	
	//删除一级分类
	public String delete(){
		//使用模型驱动获取id
		 category=categoryService.findByCid(category.getCid());
		 //删除
		 categoryService.delete(category);
		 
		 return "delete";
	}
	
	
	//进入修改页面action
	public String edit(){
		category=categoryService.findByCid(category.getCid());
		
		return "edit";
	}
	

	

	

}
