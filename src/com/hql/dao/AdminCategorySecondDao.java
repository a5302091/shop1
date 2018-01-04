package com.hql.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hql.entity.CategorySecond;
import com.hql.utils.PageHibernateCallback;

public class AdminCategorySecondDao extends HibernateDaoSupport {

	// ͳ�ƶ����������
	public int findCount() {

		String hql = "select count(*) from CategorySecond";

		List<Long> list = this.getHibernateTemplate().find(hql);

		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}

		return 0;
	}

	// ��ѯ��������
	public List<CategorySecond> findByPage(int begin, int limit) {

		String hql = "from CategorySecond order by csid desc";

		List<CategorySecond> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<>(hql, null, begin, limit));

		if (list != null && list.size() > 0) {

			return list;
		}

		return null;
	}

	// DAO�еı����������ķ���
	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}

	// ����ID��ѯ��������
	public CategorySecond findByCsid(Integer csid) {

		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}

	// ɾ����������
	public void delete(CategorySecond categorySecond) {

		this.getHibernateTemplate().delete(categorySecond);
	}
	
	//�޸Ķ�������
	public void update(CategorySecond categorySecond) {
		
		this.getHibernateTemplate().update(categorySecond);
		
	}

	//��ѯ���ж�������
	public List<CategorySecond> findAll() {
		
		String hql="from CategorySecond";
		
	
		return 	this.getHibernateTemplate().find(hql);
	}

}
