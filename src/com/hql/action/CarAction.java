package com.hql.action;


import org.apache.struts2.ServletActionContext;

import com.hql.entity.Cart;
import com.hql.entity.CartItem;
import com.hql.entity.Product;
import com.hql.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 购物车Action
 * 
 * @author Administrator
 *
 */

public class CarAction extends ActionSupport  {

	

	// 注入商品Service
	private ProductService productService;

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	// 接收pid
	private Integer pid;

	// 接收数量count
	private Integer count;
	

	public void setPid(Integer pid) {
		this.pid = pid;
	}



	public void setCount(Integer count) {
		this.count = count;
	}

	public String addCar() {

		// 封装一个carItem对象

		CartItem carItem = new CartItem();

		// 获取商品数量
		carItem.setCount(count);

		// 获取商品pid

		Product product = productService.findBypid(pid);

		// 获取商品
		carItem.setProduct(product);

		// 存入session中
		Cart car = getCar();

		car.addCart(carItem);

		return "addCar";
	}

	// 从session中获得购物车
	private Cart getCar() {

		Cart car = (Cart) ServletActionContext.getRequest().getSession().getAttribute("car");

		// 如果不存在,就自己创建一个传入session中
		if (car == null) {
			car = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("car", car);
		}

		return car;
	}
	
	//清空购物车
	public String cleanCar(){
			//获得购物车对象
		getCar().clearCart();
		
		return "cleanCar";
	}
	
	//删除购物车中的商品
	public String removeCart(){
		
		//获取购物车对象
		getCar().removeCart(pid);
		
		return "removeCart";
	}
	
	//跳转至购物车页面
	public String goCar(){
		
		return "goCar";
	}
	

}
