package com.hql.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hql.entity.Product;
import com.hql.utils.PageHibernateCallback;

/**
 * 商品信息持久层
 * 
 * @author Administrator
 *
 */

public class ProductDao extends HibernateDaoSupport {

	// 查询热门商品
	public List<Product> findHot() {

		// 使用离线条件查询.
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 查询热门的商品,条件就是is_host = 1
		criteria.add(Restrictions.eq("is_hot", 1));
		// 倒序排序输出:
		criteria.addOrder(Order.desc("pdate"));
		// 执行查询:
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}

	// 显示最新商品
	public List<Product> findNew() {

		// 使用离校条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 按日期进行查询
		criteria.addOrder(Order.desc("pdate"));
		// 执行查询
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);

		return list;
	}

	// 根据商品ID来查询商品
	public Product findBypid(Integer pid) {

		return this.getHibernateTemplate().get(Product.class, pid);

	}

	// 根据分类Id查询商品个数
	public int findCountCid(Integer cid) {

		String hql = "select count(*) from Product p where p.categorySecond.category.cid=?";

		List<Long> list = this.getHibernateTemplate().find(hql, cid);

		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}

		return 0;
	}

	// 根据分类Id查询商品集合
	public List<Product> findbyPageCid(Integer cid, int begin, int limit) {

		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid=?";

		// 实现分页
		List<Product> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<>(hql, new Object[] { cid }, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 根据二级分类Id查询商品个数
	public int findCountCsid(Integer csid) {

		String hql = "select count(*) from Product p where p.categorySecond.csid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);

		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}

		return 0;

	}

	// 根据二级分类Id查询商品集合
	public List<Product> findbyPageCsid(Integer csid, int begin, int limit) {

		String hql = "select p from Product p join p.categorySecond cs where cs.csid=?";

		// 实现分页查询
		List<Product> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<>(hql, new Object[] { csid }, begin, limit));

		if (list != null && list.size() > 0) {
			return list;
		}

		return null;
	}

	// 统计商品个数
	public int findCount() {

		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;

	}

	// 后台查询商品
	public List<Product> findByPage(int begin, int limit) {

		String hql = "from Product order by pdate desc";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<>(hql, null, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	//添加商品
	public void save(Product product) {
		
		this.getHibernateTemplate().save(product);
		
	}

	//删除商品
	public void delete(Product product) {
		
		this.getHibernateTemplate().delete(product);
		
	}

	//修改商品
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}

}
