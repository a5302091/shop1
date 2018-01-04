package com.hql.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.hql.entity.User;
import com.hql.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * �û�ģ��Action
 * 
 * @author Administrator
 *
 */

public class UserAction extends ActionSupport implements ModelDriven<User> {

	// ģ������ʹ�ö���
	private User user = new User();

	@Override
	public User getModel() {
		return user;
	}

	// ��ȡ��֤�����
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
	 * ��ת��ע��ҳ��
	 */

	public String RegistPage() {

		return "RegistPage";
	}

	/**
	 * ��֤�û��Ƿ����
	 * 
	 * @throws IOException
	 *
	 */

	public String findByName() throws IOException {

		// ����serrvice

		User u = userService.findByName(user.getUsername());

		// ���respone����
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");

		if (u != null) {
			// ��ѯ���û�
			response.getWriter().println("<font color='red'>�û����Ѿ�����</font>");
		} else {
			// δ��ѯ���û�
			response.getWriter().println("<font color='green'>�û�������ʹ��</font>");
		}

		return NONE;
	}

	// �û�ע��
	public String insert() {

		// �ж���֤�����:
		// ��session�л����֤������ֵ:
		String checkcode1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if (!checkcode.equalsIgnoreCase(checkcode1)) {
			this.addActionError("��֤���������!");
			return "checkcodeFail";
		}

		// �����ظ�ע��
		User u = userService.findByName(user.getUsername());

		// ���respone����
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");

		if (u != null) {
			this.addActionError("�û����Ѿ�����,�����ظ�ע��");
			return "checkcodeFail";
		} else {
			userService.insert(user);

			this.addActionMessage("ע��ɹ�!");
			return "msg";
		}
		
	

	}
	


	//ע��ɹ�����ת����¼ҳ��
	public  String loginPage(){
		return "loginpage";
	}



	// �û���¼
	public String login() {

		String check = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");

		if (!checkcode.equalsIgnoreCase(check)) {
			this.addActionError("��֤����������");

			return "checkLogin";

		}

		User u = userService.login(user);
		// �ж�
		if (u == null) {
			this.addActionError("�û��������������");
			return LOGIN;
		} else {
			// ��¼�ɹ�
			// ����½�л�ȡ�����˺���Ϣ����session������
			ServletActionContext.getRequest().getSession().setAttribute("u", u);
			// ��תҳ��
			return "login_success";
		}

	}

	// �û��˳�

	public String quit() {
		// ����Session
		ServletActionContext.getRequest().getSession().invalidate();
		// ҳ����ת
		return "quit";
	}

}
