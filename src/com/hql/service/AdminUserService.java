package com.hql.service;

import com.hql.dao.AdminUserDao;
import com.hql.entity.AdminUser;

public class AdminUserService {
	
	//注入dao
	private AdminUserDao adminUserDao;

	public AdminUserDao getAdminUserDao() {
		return adminUserDao;
	}

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	
	//管理员登录
	public AdminUser login(AdminUser adminUser) {
		
		return adminUserDao.login(adminUser);
		
	}
	
	

}
