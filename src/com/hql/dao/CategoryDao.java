package com.hql.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 一级菜单分类
 */

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hql.entity.Category;

public class CategoryDao extends HibernateDaoSupport {

	// 显示所有一级菜单
	public List<Category> findAll() {

		String hql = "from Category";
		List<Category> list = this.getHibernateTemplate().find(hql);
		
		if (list != null) {

			return list;
		}

		return null;
	}

	//添加一个一级菜单
	public void save(Category category) {
		
		this.getHibernateTemplate().save(category);
		
	
	}
	


	//一级分类id
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Category.class, cid);
	}

	//删除一级分类
	public void delete(Category category) {
		
		 this.getHibernateTemplate().delete(category);
	}

	//修改一级分类
	public void update(Category category) {
		
		this.getHibernateTemplate().update(category);
		
	}

}
