package com.hql.action;

import java.util.List;

import com.hql.entity.Category;
import com.hql.entity.Product;
import com.hql.service.CategoryService;
import com.hql.service.ProductService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 访问首页Action 
 * @author Administrator
 *
 */

public class MainAction extends ActionSupport {
	
	//注入一级分类Service
	private CategoryService categoryService;
	//注入商品信息Service
	private ProductService productService;
	
	/**
	 * 执行方法 
	 */
	
	public String execute(){
		
		//查询一级菜单
		List<Category> clist=categoryService.findAll();
		//将集合存入Session中
	  ActionContext.getContext().getSession().put("clist", clist);
	  //显示热门商品
		List<Product> hlist=productService.findHot();
		//保存在值栈中
		ActionContext.getContext().getValueStack().set("hlist", hlist);
		
		//显示最新商品
		List<Product> nlist=productService.findNew();
		//将最新商品保存在值栈中
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
