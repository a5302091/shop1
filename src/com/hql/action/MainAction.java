package com.hql.action;

import java.util.List;

import com.hql.entity.Category;
import com.hql.entity.Product;
import com.hql.service.CategoryService;
import com.hql.service.ProductService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ������ҳAction 
 * @author Administrator
 *
 */

public class MainAction extends ActionSupport {
	
	//ע��һ������Service
	private CategoryService categoryService;
	//ע����Ʒ��ϢService
	private ProductService productService;
	
	/**
	 * ִ�з��� 
	 */
	
	public String execute(){
		
		//��ѯһ���˵�
		List<Category> clist=categoryService.findAll();
		//�����ϴ���Session��
	  ActionContext.getContext().getSession().put("clist", clist);
	  //��ʾ������Ʒ
		List<Product> hlist=productService.findHot();
		//������ֵջ��
		ActionContext.getContext().getValueStack().set("hlist", hlist);
		
		//��ʾ������Ʒ
		List<Product> nlist=productService.findNew();
		//��������Ʒ������ֵջ��
		ActionContext.getContext().getValueStack().set("nlist", nlist);

		return "index";
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	
	
	
	
	
	

}
