package com.hql.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hql.entity.Order;
import com.hql.entity.OrderItem;
import com.hql.utils.PageHibernateCallback;

/**
 * �����־ò�
 * 
 * @author Administrator
 *
 */

public class OrderDao extends HibernateDaoSupport {

	// ���涩��
	public void save(Order order) {

		this.getHibernateTemplate().save(order);

	}

	// Dao���ѯ�ҵĶ�����ҳ��ѯ:ͳ�Ƹ���
	public int findCountByUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// Dao���ѯ�ҵĶ�����ҳ��ѯ:��ѯ����
	public List<Order> findPageByUid(Integer uid, int begin, int limit) {
		String hql = "from Order o where o.user.uid = ? order by o.ordertime desc";
		List<Order> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<Order>(hql, new Object[] { uid }, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// ���ݶ���id��ѯ��������
	public Order findByOid(Integer oid) {

		return this.getHibernateTemplate().get(Order.class, oid);
	}

	// �޸Ķ���
	public void update(Order cuuorder) {

		this.getHibernateTemplate().update(cuuorder);

	}

	// ͳ�ƶ�������
	public int findByCount() {

		String hql = "select count(*) from Order";

		List<Long> list = this.getHibernateTemplate().find(hql);

		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}

		return 0;
	}

	// ��ѯ���ж���
	public List<Order> findByPage(int begin, int limit) {

		String hql = "from Order order by ordertime desc";

		List<Order> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<Order>(hql, null, begin, limit));

		if (list != null && list.size() > 0) {
			return list;
		}

		return null;
	}

	// ��ѯ��������
	public List<OrderItem> findOrderItem(Integer oid) {

		String hql = "from OrderItem a where a.order.oid=? ";
		List<OrderItem> list = this.getHibernateTemplate().find(hql, oid);

		if (list != null && list.size() > 0) {
			return list;
		}

		return null;
	}

}
