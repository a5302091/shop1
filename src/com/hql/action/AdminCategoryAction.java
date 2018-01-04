package com.hql.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.hql.entity.Category;
import com.hql.service.CategoryService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/*
 * ��̨һ���������
 */

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category> {
	
	//ʹ��ģ������
	private Category category=new Category();

	@Override
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}
	
	//ע��һ������service
	private CategoryService categoryService;
	
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}



	//��̨��ѯ����һ������
	public String findAll(){
		
		List<Category> clist = categoryService.findAll();
	
		//����ֵջ
		ActionContext.getContext().getValueStack().set("clist", clist);
		
		return "findAll";
	}
	
	//���һ������
	public String save(){
		
		categoryService.save(category);
		
		return "save";
	}
	
	//�޸�һ������
	public String update(){
		categoryService.findByCid(category.getCid());
		
		//�޸�
		categoryService.update(category);
		
		return "update";
	}
	
	//ɾ��һ������
	public String delete(){
		//ʹ��ģ��������ȡid
		 category=categoryService.findByCid(category.getCid());
		 //ɾ��
		 categoryService.delete(category);
		 
		 return "delete";
	}
	
	
	//�����޸�ҳ��action
	public String edit(){
		category=categoryService.findByCid(category.getCid());
		
		return "edit";
	}
	

	

	

}
