package com.hql.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hql.dao.OrderDao;
import com.hql.entity.Order;
import com.hql.entity.OrderItem;
import com.hql.utils.pageBean;

/**
 * ����ҵ���
 * 
 * @author Administrator
 *
 */

@Transactional
public class OrderService {

	// ע��dao
	private OrderDao orderDao;

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	// ���涩��
	public void save(Order order) {

		orderDao.save(order);

	}

		
	public pageBean<Order> findByUid(Integer uid,Integer page) {
		pageBean<Order> pageBean = new pageBean<Order>();
		// ���õ�ǰҳ��:
		pageBean.setPage(page);
		// ����ÿҳ��ʾ��¼��:
		// ��ʾ5��
		int limit = 5;
		pageBean.setLimit(limit);
		// �����ܼ�¼��:
		int totalCount = 0;
		totalCount = orderDao.findCountByUid(uid);
		pageBean.setTotalCount(totalCount);
		// ������ҳ��
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// ����ÿҳ��ʾ���ݼ���:
		int begin = (page - 1)*limit;
		List<Order> list = orderDao.findPageByUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	//���ݶ���Id��ѯ������Ʒ����
	public Order findByOid(Integer oid) {
		
		return orderDao.findByOid(oid);
	}

	//�޸Ķ���
	public void update(Order cuuorder) {
		
		orderDao.update(cuuorder);
		
	}

	//��ѯ���ж���
	public pageBean<Order> findByPage(Integer page) {
		
		pageBean<Order> pageBean=new pageBean<Order>();
		
		//���õ�ǰҳ��
		pageBean.setPage(page);
		
		//����ÿҳ��ʾ������
		int limit=8;
		pageBean.setLimit(limit);
		
		//�����ܵļ�¼��
		
		int totalCount=orderDao.findByCount();
		pageBean.setTotalCount(totalCount);
		
		//�����ܵ�ҳ��
		int totalPage=0;
		if(totalCount%limit==0){
			totalPage=totalCount / limit;
		}else{
			totalPage=totalCount / limit +1;
		}
		pageBean.setTotalPage(totalPage);
		
		int begin=(page-1)*limit;
		
		//����ÿҳ��ʾ�����ݼ���
		List<Order> list=orderDao.findByPage(begin,limit);
		
		pageBean.setList(list);
		
		return pageBean;
	}

	//��ѯ��������
	public List<OrderItem> findOrderItem(Integer oid) {
		
		
		return orderDao.findOrderItem(oid);
	}
}
