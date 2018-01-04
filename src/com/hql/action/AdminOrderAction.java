package com.hql.action;

import java.util.List;

import com.hql.entity.Order;
import com.hql.entity.OrderItem;
import com.hql.service.OrderService;
import com.hql.utils.pageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台订单管理
 * 
 * @author Administrator
 *
 */

public class AdminOrderAction extends ActionSupport implements ModelDriven<Order> {

	// 使用模型驱动
	private Order order = new Order();

	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}

	// 账户service
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	// 接收page
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}
	
	//查询所有的订单
	public String findAll(){
		pageBean<Order> pageBean=orderService.findByPage(page);
		
		//分页存入值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		//页面跳转
		return "findAll";
	}
	
	
	//查询订单详情
	public String findOrderItem(){
		 
		List<OrderItem> list=orderService.findOrderItem(order.getOid());
		
		ActionContext.getContext().getValueStack().set("list", list);
		
		return "findOrderItem";
	}
	
	

}
