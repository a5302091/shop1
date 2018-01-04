package com.hql.service;

import org.springframework.transaction.annotation.Transactional;

import com.hql.dao.UserDao;
import com.hql.entity.User;
import com.hql.utils.EmailUtils;
import com.hql.utils.UUIDUtils;

@Transactional
public class UserService {
	
	//注入userDao
	
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	//查询是否存在用户
	public User findByName(String username){
		
		return userDao.findByName(username);
	}
	
	
	//注册用户
	public void insert(User user){

		userDao.insert(user);

		
		
	}
	

	
	
	//修改用户状态
	public void update(User u) {
		userDao.update(u);
	}

	
	//用户登录
	public User login(User user) {
		return userDao.login(user);
	}

}
