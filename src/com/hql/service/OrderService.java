package com.hql.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hql.dao.OrderDao;
import com.hql.entity.Order;
import com.hql.entity.OrderItem;
import com.hql.utils.pageBean;

/**
 * 订单业务成
 * 
 * @author Administrator
 *
 */

@Transactional
public class OrderService {

	// 注入dao
	private OrderDao orderDao;

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	// 保存订单
	public void save(Order order) {

		orderDao.save(order);

	}

		
	public pageBean<Order> findByUid(Integer uid,Integer page) {
		pageBean<Order> pageBean = new pageBean<Order>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		// 显示5个
		int limit = 5;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = orderDao.findCountByUid(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示数据集合:
		int begin = (page - 1)*limit;
		List<Order> list = orderDao.findPageByUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	//根据订单Id查询订单商品付款
	public Order findByOid(Integer oid) {
		
		return orderDao.findByOid(oid);
	}

	//修改订单
	public void update(Order cuuorder) {
		
		orderDao.update(cuuorder);
		
	}

	//查询所有订单
	public pageBean<Order> findByPage(Integer page) {
		
		pageBean<Order> pageBean=new pageBean<Order>();
		
		//设置当前页数
		pageBean.setPage(page);
		
		//设置每页显示的条数
		int limit=8;
		pageBean.setLimit(limit);
		
		//设置总的记录是
		
		int totalCount=orderDao.findByCount();
		pageBean.setTotalCount(totalCount);
		
		//设置总的页数
		int totalPage=0;
		if(totalCount%limit==0){
			totalPage=totalCount / limit;
		}else{
			totalPage=totalCount / limit +1;
		}
		pageBean.setTotalPage(totalPage);
		
		int begin=(page-1)*limit;
		
		//设置每页显示的数据集合
		List<Order> list=orderDao.findByPage(begin,limit);
		
		pageBean.setList(list);
		
		return pageBean;
	}

	//查询订单详情
	public List<OrderItem> findOrderItem(Integer oid) {
		
		
		return orderDao.findOrderItem(oid);
	}
}
