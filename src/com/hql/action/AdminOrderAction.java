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
 * ��̨��������
 * 
 * @author Administrator
 *
 */

public class AdminOrderAction extends ActionSupport implements ModelDriven<Order> {

	// ʹ��ģ������
	private Order order = new Order();

	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}

	// �˻�service
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	// ����page
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}
	
	//��ѯ���еĶ���
	public String findAll(){
		pageBean<Order> pageBean=orderService.findByPage(page);
		
		//��ҳ����ֵջ
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		//ҳ����ת
		return "findAll";
	}
	
	
	//��ѯ��������
	public String findOrderItem(){
		 
		List<OrderItem> list=orderService.findOrderItem(order.getOid());
		
		ActionContext.getContext().getValueStack().set("list", list);
		
		return "findOrderItem";
	}
	
	

}
