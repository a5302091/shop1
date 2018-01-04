package com.hql.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hql.entity.AdminUser;

public class AdminUserDao extends HibernateDaoSupport {

	//π‹¿Ì‘±µ«¬º
	public AdminUser login(AdminUser adminUser) {
		
		String hql="from AdminUser where username=? and password=?";
		
		List<AdminUser> list=this.getHibernateTemplate().find(hql, adminUser.getUsername(),adminUser.getPassword());
		
		if(list!=null &&list.size()>0){
			return list.get(0);
		}
		return null;
		
	}

}
