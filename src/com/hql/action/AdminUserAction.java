package com.hql.action;

import org.apache.struts2.ServletActionContext;

import com.hql.entity.AdminUser;
import com.hql.service.AdminUserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 管理员
 * 
 * @author Administrator
 *
 */

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser> {

	// 使用模型驱动
	private AdminUser adminUser = new AdminUser();

	@Override
	public AdminUser getModel() {
		// TODO Auto-generated method stub
		return adminUser;
	}

	// 接收验证码
	private String checkcode;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	

	public String getCheckcode() {
		return checkcode;
	}

	// 注入Service
	private AdminUserService adminUserService;

	public AdminUserService getAdminUserService() {
		return adminUserService;
	}

	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

	// 管理员登录
	public String login() {

		// 判断验证码是否正确
		String check = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");

		if (!checkcode.equalsIgnoreCase(check)) {
			this.addActionError("验证码输入有误");
			return "adminerror";
		}

		AdminUser admin = adminUserService.login(adminUser);

		if (admin == null) {
			this.addActionError("用户名或者密码错误");
			return "adminerror";
		} else {

			// 将登录的账号存入session中
			ServletActionContext.getRequest().getSession().setAttribute("admin", admin);

			// 登录成功
			return "adminsuccess";

		}

	}

}
