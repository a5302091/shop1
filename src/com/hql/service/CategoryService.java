package com.hql.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hql.dao.CategoryDao;
import com.hql.entity.Category;

/**
 * 一级分类菜单 
 * @author Administrator
 *
 */

@Transactional
public class CategoryService {
	
	//注入dao
	private CategoryDao categoryDao;

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	
	//查询所有一级菜单
	public List<Category> findAll() {
		
		return categoryDao.findAll();
		
		
	}
	
	//添加一个一级分类
	public void save(Category category) {
		
		categoryDao.save(category);
		
	}

	//修改一级分类
	public void update(Category category) {
		
		categoryDao.update(category);
		
	}

	//根据id查询
	public Category findByCid(Integer cid) {
		
		
		
		return categoryDao.findByCid(cid);
	}

	//删除
	public void delete(Category category) {
		
		categoryDao.delete(category);
		
	}
	
	

}
