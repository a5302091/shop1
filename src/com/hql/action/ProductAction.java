package com.hql.action;

import java.util.List;

import com.hql.entity.Product;
import com.hql.service.CategoryService;
import com.hql.service.ProductService;
import com.hql.utils.pageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 商品Action
 * 
 * @author Administrator
 *
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product> {

	private Product product = new Product();

	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}

	// 注入一级分类cid
	private Integer cid;

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getCid() {
		return cid;
	}

	// 注入二级分类的csid
	private Integer csid;

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	// 当前的页数
	private int page;

	public void setPage(int page) {
		this.page = page;
	}

	// 注入Service
	private ProductService productService;

	private CategoryService categoryService;

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

	// 获取商品ID查询商品
	public String findBypid() {

		product = productService.findBypid(product.getPid());

		return "findBypid";

	}

	// 根据一级分类ID查询二级分类
	public String findByCid() {

		pageBean<Product> pageBean = productService.findByPageCid(cid, page);// 根据一级分类商品,带分页
		// 将pageBean存入值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);

		return "findBycid";

	}

	// 根据二级分类Id查询商品
	public String findByCsid() {

		pageBean<Product> pageBean=productService.findByCsid(csid, page);
		//将pageBean存入值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);

		return "findByCsid";
	}
	
	

}
