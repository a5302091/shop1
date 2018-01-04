package com.hql.service;

import java.util.List;

import com.hql.dao.AdminCategorySecondDao;
import com.hql.entity.CategorySecond;
import com.hql.utils.pageBean;

public class AdminCategorySecondService {

	// 注入Dao
	private AdminCategorySecondDao adminCategorySecondDao;

	public AdminCategorySecondDao getAdminCategorySecondDao() {
		return adminCategorySecondDao;
	}

	public void setAdminCategorySecondDao(AdminCategorySecondDao adminCategorySecondDao) {
		this.adminCategorySecondDao = adminCategorySecondDao;
	}

	// 查询所有二级分类
	public pageBean<CategorySecond> findByPage(Integer page) {

		pageBean<CategorySecond> pageBean = new pageBean<CategorySecond>();

		// 设置参数:
		pageBean.setPage(page);

		// 每页显示是几条
		int limit = 8;
		pageBean.setLimit(limit);

		// 设置总记录数
		int totalCount = adminCategorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);

		// 设置总页数
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}

		pageBean.setTotalPage(totalPage);

		// 显示每页显示数据集合
		int begin = (page - 1) * limit;
		List<CategorySecond> list = adminCategorySecondDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	// 业务层的保存二级分类的操作
	public void save(CategorySecond categorySecond) {
		adminCategorySecondDao.save(categorySecond);
	}

	// 查询二级分类ID
	public CategorySecond findByCsid(Integer csid) {

		return adminCategorySecondDao.findByCsid(csid);
	}

	// 删除二级分类
	public void delete(CategorySecond categorySecond) {

		adminCategorySecondDao.delete(categorySecond);
	}

	// 修改二级分类
	public void update(CategorySecond categorySecond) {
		adminCategorySecondDao.update(categorySecond);
	}

	//查询所有二级分累
	public List<CategorySecond> findAll() {
		
		return adminCategorySecondDao.findAll();
				
		
	}

}
