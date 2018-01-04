package com.hql.action;


import org.apache.struts2.ServletActionContext;

import com.hql.entity.Cart;
import com.hql.entity.CartItem;
import com.hql.entity.Product;
import com.hql.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ���ﳵAction
 * 
 * @author Administrator
 *
 */

public class CarAction extends ActionSupport  {

	

	// ע����ƷService
	private ProductService productService;

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	// ����pid
	private Integer pid;

	// ��������count
	private Integer count;
	

	public void setPid(Integer pid) {
		this.pid = pid;
	}



	public void setCount(Integer count) {
		this.count = count;
	}

	public String addCar() {

		// ��װһ��carItem����

		CartItem carItem = new CartItem();

		// ��ȡ��Ʒ����
		carItem.setCount(count);

		// ��ȡ��Ʒpid

		Product product = productService.findBypid(pid);

		// ��ȡ��Ʒ
		carItem.setProduct(product);

		// ����session��
		Cart car = getCar();

		car.addCart(carItem);

		return "addCar";
	}

	// ��session�л�ù��ﳵ
	private Cart getCar() {

		Cart car = (Cart) ServletActionContext.getRequest().getSession().getAttribute("car");

		// ���������,���Լ�����һ������session��
		if (car == null) {
			car = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("car", car);
		}

		return car;
	}
	
	//��չ��ﳵ
	public String cleanCar(){
			//��ù��ﳵ����
		getCar().clearCart();
		
		return "cleanCar";
	}
	
	//ɾ�����ﳵ�е���Ʒ
	public String removeCart(){
		
		//��ȡ���ﳵ����
		getCar().removeCart(pid);
		
		return "removeCart";
	}
	
	//��ת�����ﳵҳ��
	public String goCar(){
		
		return "goCar";
	}
	

}
