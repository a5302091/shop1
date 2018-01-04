package com.hql.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hql.entity.Order;
import com.hql.entity.OrderItem;
import com.hql.utils.PageHibernateCallback;

/**
 * 订单持久层
 * 
 * @author Administrator
 *
 */

public class OrderDao extends HibernateDaoSupport {

	// 保存订单
	public void save(Order order) {

		this.getHibernateTemplate().save(order);

	}

	// Dao层查询我的订单分页查询:统计个数
	public int findCountByUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// Dao层查询我的订单分页查询:查询数据
	public List<Order> findPageByUid(Integer uid, int begin, int limit) {
		String hql = "from Order o where o.user.uid = ? order by o.ordertime desc";
		List<Order> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<Order>(hql, new Object[] { uid }, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 根据订单id查询订单付款
	public Order findByOid(Integer oid) {

		return this.getHibernateTemplate().get(Order.class, oid);
	}

	// 修改订单
	public void update(Order cuuorder) {

		this.getHibernateTemplate().update(cuuorder);

	}

	// 统计订单个数
	public int findByCount() {

		String hql = "select count(*) from Order";

		List<Long> list = this.getHibernateTemplate().find(hql);

		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}

		return 0;
	}

	// 查询所有订单
	public List<Order> findByPage(int begin, int limit) {

		String hql = "from Order order by ordertime desc";

		List<Order> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<Order>(hql, null, begin, limit));

		if (list != null && list.size() > 0) {
			return list;
		}

		return null;
	}

	// 查询订单详情
	public List<OrderItem> findOrderItem(Integer oid) {

		String hql = "from OrderItem a where a.order.oid=? ";
		List<OrderItem> list = this.getHibernateTemplate().find(hql, oid);

		if (list != null && list.size() > 0) {
			return list;
		}

		return null;
	}

}
