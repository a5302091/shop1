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
 * ��ƷAction
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

	// ע��һ������cid
	private Integer cid;

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getCid() {
		return cid;
	}

	// ע����������csid
	private Integer csid;

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	// ��ǰ��ҳ��
	private int page;

	public void setPage(int page) {
		this.page = page;
	}

	// ע��Service
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

	// ��ȡ��ƷID��ѯ��Ʒ
	public String findBypid() {

		product = productService.findBypid(product.getPid());

		return "findBypid";

	}

	// ����һ������ID��ѯ��������
	public String findByCid() {

		pageBean<Product> pageBean = productService.findByPageCid(cid, page);// ����һ��������Ʒ,����ҳ
		// ��pageBean����ֵջ��
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);

		return "findBycid";

	}

	// ���ݶ�������Id��ѯ��Ʒ
	public String findByCsid() {

		pageBean<Product> pageBean=productService.findByCsid(csid, page);
		//��pageBean����ֵջ��
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);

		return "findByCsid";
	}
	
	

}
