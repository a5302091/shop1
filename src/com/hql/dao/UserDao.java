package com.hql.dao;

/**
 * 用户持久层 
 */

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hql.entity.User;

public class UserDao extends HibernateDaoSupport {
	
	//查询是否存在用户
	
	public User findByName(String username){
		
		String hql="from User where username=?";
		
		List<User> list = this.getHibernateTemplate().find(hql, username);
		
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		
		return null;
		
	}
	
	//用户注册
	public void insert(User user){
		this.getHibernateTemplate().save(user);
	}
	


	//修改用户状态
	public void update(User u) {
		this.getHibernateTemplate().update(u);
	}

	
	//用户登录
	public User login(User user) {
		
		String hql="from User where username=? and password=?";
		List<User> list = this.getHibernateTemplate().find(hql, user.getUsername(),user.getPassword());
		
		if(list!=null && list.size()>0){
			
			return list.get(0);
		}
		
		return null;
	}
	

}
