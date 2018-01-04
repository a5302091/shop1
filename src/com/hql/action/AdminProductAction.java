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
 * 后台商品管理
 * 
 * @author Administrator
 *
 */

public class AdminProductAction extends ActionSupport implements ModelDriven<Product> {

	// 模型驱动对象
	private Product product = new Product();

	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}

	// 文件上传的参数

	// 1.上传的文件
	private File upload;

	// 2.文件上传名
	private String uploadFileName;

	// 3.文件上传的MIME类型
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

	// 注入商品service
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	// 接收分页
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}
	
	//获取商品id
	private Integer pid;
	
	
	public void setPid(Integer pid) {
		this.pid = pid;
	}

	// 注入二级分类service
	private AdminCategorySecondService adminCategorySecondService;

	public void setAdminCategorySecondService(AdminCategorySecondService adminCategorySecondService) {
		this.adminCategorySecondService = adminCategorySecondService;
	}

	// 查询商品
	public String findAll() {

		pageBean<Product> pageBean = productService.findByPage(page);

		// 存入值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);

		// 跳转
		return "findAll";

	}

	// 添加商品页面跳转
	public String addPage() {

		// 查询二级分类

		List<CategorySecond> csList = adminCategorySecondService.findAll();

		// 存入值栈
		ActionContext.getContext().getValueStack().set("csList", csList);

		return "addPage";

	}

	// 添加商品
	public String save() throws Exception {

		// 调用service;

		product.setPdate(new Date());

		if (upload != null) {
			// 获取文件上传的磁盘路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");

			// 创建一个文件接收
			File diskFile = new File(realPath + "//" + uploadFileName);

			// 文件上传
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/" + uploadFileName);

		}

		productService.save(product);

		return "savesuccess";
	}
	
	
	//删除商品
	public String delete(){
		
		productService.delete(product);
		
		return "delete";
		
	}
	
	
	//修改商品跳转页面
	public String edit(){
		
		//根据id查询商品
		product = productService.findBypid(product.getPid());
		
		//查询二级分类
		List<CategorySecond> csList = adminCategorySecondService.findAll();
		
		//存入值栈
		ActionContext.getContext().getValueStack().set("csList", csList);
		
		return "edit";
	}
	
	//修改商品
	public String update() throws IOException{
		
		product.setPdate(new Date());
		
		//文件上传
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
