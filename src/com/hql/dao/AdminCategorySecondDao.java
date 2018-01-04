package com.hql.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hql.entity.CategorySecond;
import com.hql.utils.PageHibernateCallback;

public class AdminCategorySecondDao extends HibernateDaoSupport {

	// 统计二级分类个数
	public int findCount() {

		String hql = "select count(*) from CategorySecond";

		List<Long> list = this.getHibernateTemplate().find(hql);

		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}

		return 0;
	}

	// 查询二级分类
	public List<CategorySecond> findByPage(int begin, int limit) {

		String hql = "from CategorySecond order by csid desc";

		List<CategorySecond> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<>(hql, null, begin, limit));

		if (list != null && list.size() > 0) {

			return list;
		}

		return null;
	}

	// DAO中的保存二级分类的方法
	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}

	// 根据ID查询二级分类
	public CategorySecond findByCsid(Integer csid) {

		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}

	// 删除二级分类
	public void delete(CategorySecond categorySecond) {

		this.getHibernateTemplate().delete(categorySecond);
	}
	
	//修改二级分类
	public void update(CategorySecond categorySecond) {
		
		this.getHibernateTemplate().update(categorySecond);
		
	}

	//查询所有二级分类
	public List<CategorySecond> findAll() {
		
		String hql="from CategorySecond";
		
	
		return 	this.getHibernateTemplate().find(hql);
	}

}
