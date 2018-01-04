package com.hql.service;

import java.util.List;

import com.hql.dao.AdminCategorySecondDao;
import com.hql.entity.CategorySecond;
import com.hql.utils.pageBean;

public class AdminCategorySecondService {

	// ע��Dao
	private AdminCategorySecondDao adminCategorySecondDao;

	public AdminCategorySecondDao getAdminCategorySecondDao() {
		return adminCategorySecondDao;
	}

	public void setAdminCategorySecondDao(AdminCategorySecondDao adminCategorySecondDao) {
		this.adminCategorySecondDao = adminCategorySecondDao;
	}

	// ��ѯ���ж�������
	public pageBean<CategorySecond> findByPage(Integer page) {

		pageBean<CategorySecond> pageBean = new pageBean<CategorySecond>();

		// ���ò���:
		pageBean.setPage(page);

		// ÿҳ��ʾ�Ǽ���
		int limit = 8;
		pageBean.setLimit(limit);

		// �����ܼ�¼��
		int totalCount = adminCategorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);

		// ������ҳ��
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}

		pageBean.setTotalPage(totalPage);

		// ��ʾÿҳ��ʾ���ݼ���
		int begin = (page - 1) * limit;
		List<CategorySecond> list = adminCategorySecondDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	// ҵ���ı����������Ĳ���
	public void save(CategorySecond categorySecond) {
		adminCategorySecondDao.save(categorySecond);
	}

	// ��ѯ��������ID
	public CategorySecond findByCsid(Integer csid) {

		return adminCategorySecondDao.findByCsid(csid);
	}

	// ɾ����������
	public void delete(CategorySecond categorySecond) {

		adminCategorySecondDao.delete(categorySecond);
	}

	// �޸Ķ�������
	public void update(CategorySecond categorySecond) {
		adminCategorySecondDao.update(categorySecond);
	}

	//��ѯ���ж�������
	public List<CategorySecond> findAll() {
		
		return adminCategorySecondDao.findAll();
				
		
	}

}
