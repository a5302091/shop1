package com.hql.service;

import org.springframework.transaction.annotation.Transactional;

import com.hql.dao.UserDao;
import com.hql.entity.User;
import com.hql.utils.EmailUtils;
import com.hql.utils.UUIDUtils;

@Transactional
public class UserService {
	
	//ע��userDao
	
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	//��ѯ�Ƿ�����û�
	public User findByName(String username){
		
		return userDao.findByName(username);
	}
	
	
	//ע���û�
	public void insert(User user){

		userDao.insert(user);

		
		
	}
	

	
	
	//�޸��û�״̬
	public void update(User u) {
		userDao.update(u);
	}

	
	//�û���¼
	public User login(User user) {
		return userDao.login(user);
	}

}
