package com.hql.service;

import com.hql.dao.AdminUserDao;
import com.hql.entity.AdminUser;

public class AdminUserService {
	
	//ע��dao
	private AdminUserDao adminUserDao;

	public AdminUserDao getAdminUserDao() {
		return adminUserDao;
	}

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	
	//����Ա��¼
	public AdminUser login(AdminUser adminUser) {
		
		return adminUserDao.login(adminUser);
		
	}
	
	

}
