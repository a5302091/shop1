package com.hql.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.hql.entity.User;
import com.hql.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户模块Action
 * 
 * @author Administrator
 *
 */

public class UserAction extends ActionSupport implements ModelDriven<User> {

	// 模型驱动使用对象
	private User user = new User();

	@Override
	public User getModel() {
		return user;
	}

	// 获取验证码对象
	private String checkcode;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 跳转至注册页面
	 */

	public String RegistPage() {

		return "RegistPage";
	}

	/**
	 * 验证用户是否存在
	 * 
	 * @throws IOException
	 *
	 */

	public String findByName() throws IOException {

		// 调用serrvice

		User u = userService.findByName(user.getUsername());

		// 获得respone对象
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");

		if (u != null) {
			// 查询到用户
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		} else {
			// 未查询到用户
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}

		return NONE;
	}

	// 用户注册
	public String insert() {

		// 判断验证码程序:
		// 从session中获得验证码的随机值:
		String checkcode1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if (!checkcode.equalsIgnoreCase(checkcode1)) {
			this.addActionError("验证码输入错误!");
			return "checkcodeFail";
		}

		// 不能重复注册
		User u = userService.findByName(user.getUsername());

		// 获得respone对象
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");

		if (u != null) {
			this.addActionError("用户名已经存在,不能重复注册");
			return "checkcodeFail";
		} else {
			userService.insert(user);

			this.addActionMessage("注册成功!");
			return "msg";
		}
		
	

	}
	


	//注册成功后跳转至登录页面
	public  String loginPage(){
		return "loginpage";
	}



	// 用户登录
	public String login() {

		String check = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");

		if (!checkcode.equalsIgnoreCase(check)) {
			this.addActionError("验证码输入有误");

			return "checkLogin";

		}

		User u = userService.login(user);
		// 判断
		if (u == null) {
			this.addActionError("用户名或者密码错误");
			return LOGIN;
		} else {
			// 登录成功
			// 将登陆中获取到的账号信息存入session对象中
			ServletActionContext.getRequest().getSession().setAttribute("u", u);
			// 跳转页面
			return "login_success";
		}

	}

	// 用户退出

	public String quit() {
		// 消耗Session
		ServletActionContext.getRequest().getSession().invalidate();
		// 页面跳转
		return "quit";
	}

}
