package com.hql.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hql.entity.Product;
import com.hql.utils.PageHibernateCallback;

/**
 * ��Ʒ��Ϣ�־ò�
 * 
 * @author Administrator
 *
 */

public class ProductDao extends HibernateDaoSupport {

	// ��ѯ������Ʒ
	public List<Product> findHot() {

		// ʹ������������ѯ.
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// ��ѯ���ŵ���Ʒ,��������is_host = 1
		criteria.add(Restrictions.eq("is_hot", 1));
		// �����������:
		criteria.addOrder(Order.desc("pdate"));
		// ִ�в�ѯ:
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}

	// ��ʾ������Ʒ
	public List<Product> findNew() {

		// ʹ����У������ѯ
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// �����ڽ��в�ѯ
		criteria.addOrder(Order.desc("pdate"));
		// ִ�в�ѯ
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);

		return list;
	}

	// ������ƷID����ѯ��Ʒ
	public Product findBypid(Integer pid) {

		return this.getHibernateTemplate().get(Product.class, pid);

	}

	// ���ݷ���Id��ѯ��Ʒ����
	public int findCountCid(Integer cid) {

		String hql = "select count(*) from Product p where p.categorySecond.category.cid=?";

		List<Long> list = this.getHibernateTemplate().find(hql, cid);

		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}

		return 0;
	}

	// ���ݷ���Id��ѯ��Ʒ����
	public List<Product> findbyPageCid(Integer cid, int begin, int limit) {

		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid=?";

		// ʵ�ַ�ҳ
		List<Product> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<>(hql, new Object[] { cid }, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// ���ݶ�������Id��ѯ��Ʒ����
	public int findCountCsid(Integer csid) {

		String hql = "select count(*) from Product p where p.categorySecond.csid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);

		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}

		return 0;

	}

	// ���ݶ�������Id��ѯ��Ʒ����
	public List<Product> findbyPageCsid(Integer csid, int begin, int limit) {

		String hql = "select p from Product p join p.categorySecond cs where cs.csid=?";

		// ʵ�ַ�ҳ��ѯ
		List<Product> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<>(hql, new Object[] { csid }, begin, limit));

		if (list != null && list.size() > 0) {
			return list;
		}

		return null;
	}

	// ͳ����Ʒ����
	public int findCount() {

		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;

	}

	// ��̨��ѯ��Ʒ
	public List<Product> findByPage(int begin, int limit) {

		String hql = "from Product order by pdate desc";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<>(hql, null, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	//�����Ʒ
	public void save(Product product) {
		
		this.getHibernateTemplate().save(product);
		
	}

	//ɾ����Ʒ
	public void delete(Product product) {
		
		this.getHibernateTemplate().delete(product);
		
	}

	//�޸���Ʒ
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}

}
