package com.hql.action;

import org.apache.struts2.ServletActionContext;

import com.hql.entity.AdminUser;
import com.hql.service.AdminUserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ����Ա
 * 
 * @author Administrator
 *
 */

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser> {

	// ʹ��ģ������
	private AdminUser adminUser = new AdminUser();

	@Override
	public AdminUser getModel() {
		// TODO Auto-generated method stub
		return adminUser;
	}

	// ������֤��
	private String checkcode;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	

	public String getCheckcode() {
		return checkcode;
	}

	// ע��Service
	private AdminUserService adminUserService;

	public AdminUserService getAdminUserService() {
		return adminUserService;
	}

	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

	// ����Ա��¼
	public String login() {

		// �ж���֤���Ƿ���ȷ
		String check = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");

		if (!checkcode.equalsIgnoreCase(check)) {
			this.addActionError("��֤����������");
			return "adminerror";
		}

		AdminUser admin = adminUserService.login(adminUser);

		if (admin == null) {
			this.addActionError("�û��������������");
			return "adminerror";
		} else {

			// ����¼���˺Ŵ���session��
			ServletActionContext.getRequest().getSession().setAttribute("admin", admin);

			// ��¼�ɹ�
			return "adminsuccess";

		}

	}

}
