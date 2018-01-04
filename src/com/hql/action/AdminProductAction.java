package com.hql.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.hql.entity.CategorySecond;
import com.hql.entity.Product;
import com.hql.service.AdminCategorySecondService;
import com.hql.service.ProductService;
import com.hql.utils.pageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ��̨��Ʒ����
 * 
 * @author Administrator
 *
 */

public class AdminProductAction extends ActionSupport implements ModelDriven<Product> {

	// ģ����������
	private Product product = new Product();

	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}

	// �ļ��ϴ��Ĳ���

	// 1.�ϴ����ļ�
	private File upload;

	// 2.�ļ��ϴ���
	private String uploadFileName;

	// 3.�ļ��ϴ���MIME����
	private String uploadContextType;

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}

	// ע����Ʒservice
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	// ���շ�ҳ
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}
	
	//��ȡ��Ʒid
	private Integer pid;
	
	
	public void setPid(Integer pid) {
		this.pid = pid;
	}

	// ע���������service
	private AdminCategorySecondService adminCategorySecondService;

	public void setAdminCategorySecondService(AdminCategorySecondService adminCategorySecondService) {
		this.adminCategorySecondService = adminCategorySecondService;
	}

	// ��ѯ��Ʒ
	public String findAll() {

		pageBean<Product> pageBean = productService.findByPage(page);

		// ����ֵջ
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);

		// ��ת
		return "findAll";

	}

	// �����Ʒҳ����ת
	public String addPage() {

		// ��ѯ��������

		List<CategorySecond> csList = adminCategorySecondService.findAll();

		// ����ֵջ
		ActionContext.getContext().getValueStack().set("csList", csList);

		return "addPage";

	}

	// �����Ʒ
	public String save() throws Exception {

		// ����service;

		product.setPdate(new Date());

		if (upload != null) {
			// ��ȡ�ļ��ϴ��Ĵ���·��
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");

			// ����һ���ļ�����
			File diskFile = new File(realPath + "//" + uploadFileName);

			// �ļ��ϴ�
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/" + uploadFileName);

		}

		productService.save(product);

		return "savesuccess";
	}
	
	
	//ɾ����Ʒ
	public String delete(){
		
		productService.delete(product);
		
		return "delete";
		
	}
	
	
	//�޸���Ʒ��תҳ��
	public String edit(){
		
		//����id��ѯ��Ʒ
		product = productService.findBypid(product.getPid());
		
		//��ѯ��������
		List<CategorySecond> csList = adminCategorySecondService.findAll();
		
		//����ֵջ
		ActionContext.getContext().getValueStack().set("csList", csList);
		
		return "edit";
	}
	
	//�޸���Ʒ
	public String update() throws IOException{
		
		product.setPdate(new Date());
		
		//�ļ��ϴ�
		if(upload!=null){
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			File file=new File(realPath+"//"+uploadFileName);
			FileUtils.copyFile(upload, file);
			product.setImage("products/"+uploadFileName);
		}
		
		productService.update(product);
		
		return "update";
	}
	
	
	
}
