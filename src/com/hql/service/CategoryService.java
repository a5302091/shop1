package com.hql.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hql.dao.CategoryDao;
import com.hql.entity.Category;

/**
 * һ������˵� 
 * @author Administrator
 *
 */

@Transactional
public class CategoryService {
	
	//ע��dao
	private CategoryDao categoryDao;

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	
	//��ѯ����һ���˵�
	public List<Category> findAll() {
		
		return categoryDao.findAll();
		
		
	}
	
	//���һ��һ������
	public void save(Category category) {
		
		categoryDao.save(category);
		
	}

	//�޸�һ������
	public void update(Category category) {
		
		categoryDao.update(category);
		
	}

	//����id��ѯ
	public Category findByCid(Integer cid) {
		
		
		
		return categoryDao.findByCid(cid);
	}

	//ɾ��
	public void delete(Category category) {
		
		categoryDao.delete(category);
		
	}
	
	

}
