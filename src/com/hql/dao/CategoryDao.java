package com.hql.dao;

import java.io.Serializable;
import java.util.List;

/**
 * һ���˵�����
 */

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hql.entity.Category;

public class CategoryDao extends HibernateDaoSupport {

	// ��ʾ����һ���˵�
	public List<Category> findAll() {

		String hql = "from Category";
		List<Category> list = this.getHibernateTemplate().find(hql);
		
		if (list != null) {

			return list;
		}

		return null;
	}

	//���һ��һ���˵�
	public void save(Category category) {
		
		this.getHibernateTemplate().save(category);
		
	
	}
	


	//һ������id
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Category.class, cid);
	}

	//ɾ��һ������
	public void delete(Category category) {
		
		 this.getHibernateTemplate().delete(category);
	}

	//�޸�һ������
	public void update(Category category) {
		
		this.getHibernateTemplate().update(category);
		
	}

}
