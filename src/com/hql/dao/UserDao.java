package com.hql.dao;

/**
 * �û��־ò� 
 */

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hql.entity.User;

public class UserDao extends HibernateDaoSupport {
	
	//��ѯ�Ƿ�����û�
	
	public User findByName(String username){
		
		String hql="from User where username=?";
		
		List<User> list = this.getHibernateTemplate().find(hql, username);
		
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		
		return null;
		
	}
	
	//�û�ע��
	public void insert(User user){
		this.getHibernateTemplate().save(user);
	}
	


	//�޸��û�״̬
	public void update(User u) {
		this.getHibernateTemplate().update(u);
	}

	
	//�û���¼
	public User login(User user) {
		
		String hql="from User where username=? and password=?";
		List<User> list = this.getHibernateTemplate().find(hql, user.getUsername(),user.getPassword());
		
		if(list!=null && list.size()>0){
			
			return list.get(0);
		}
		
		return null;
	}
	

}
