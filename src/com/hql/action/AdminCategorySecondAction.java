	package com.hql.action;

import java.util.List;

import com.hql.entity.Category;

/**
 * ��������Action
 */

import com.hql.entity.CategorySecond;
import com.hql.service.AdminCategorySecondService;
import com.hql.service.CategoryService;
import com.hql.utils.pageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {
	
	//ʹ��ģ������
	private CategorySecond categorySecond=new CategorySecond();
	
	@Override
	public CategorySecond getModel() {
		
		return categorySecond;
	}
	
	//����page
	private Integer page;
	
	public void setPage(Integer page) {
		this.page = page;
	}
	
	//ע��service
	private AdminCategorySecondService adminCategorySecondService;

	public void setAdminCategorySecondService(AdminCategorySecondService adminCategorySecondService) {
		this.adminCategorySecondService = adminCategorySecondService;
	}
	
	//ע��һ������service
	private CategoryService categoryService;
	
	

	
	


	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	//��ѯ���ж�������
	public String findAll(){
		
		pageBean<CategorySecond> pageBean=adminCategorySecondService.
				findByPage(page);
		
		//����ֵջ
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findAll";
	}
	
	//��ת�����ҳ��
	public String addPage(){
		//��ѯһ������
		List<Category> cList = categoryService.findAll();
		
		//����ֵջ
		ActionContext.getContext().getValueStack().set("cList", cList);
		
		
		return "addPage";
	}
	
	//ɾ����������
	public String delete(){
		
		//������ѯ
		categorySecond=adminCategorySecondService.findByCsid(categorySecond.getCsid());
		
		adminCategorySecondService.delete(categorySecond);
		
		return "delete";
	}
	
	// ��Ӷ�������ķ���:
		public String save() {
			adminCategorySecondService.save(categorySecond);
			return "save";
		}
		
		
	//�޸Ķ�������
		public String edit(){
			
			//��ѯ��������
			categorySecond = adminCategorySecondService.findByCsid(categorySecond.getCsid());
			
			//��ѯһ������
			List<Category> cList = categoryService.findAll();
			
			//����ֵջ
			ActionContext.getContext().getValueStack().set("cList", cList);
			return "edit";
		}
		
		
		//�޸�
		public String update(){
			
			adminCategorySecondService.update(categorySecond);
			
			return "update";
			
		}

	

}
