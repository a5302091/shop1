package com.hql.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hql.dao.ProductDao;
import com.hql.entity.Product;
import com.hql.utils.pageBean;

/**
 * ��Ʒ��Ϣҵ���
 * 
 * @author Administrator
 *
 */

@Transactional
public class ProductService {

	// ע��dao

	private ProductDao productDao;

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	// ��ʾ������Ʒ
	public List<Product> findHot() {

		return productDao.findHot();
	}

	// ��ʾ������Ʒ
	public List<Product> findNew() {

		return productDao.findNew();
	}

	// ������ƷID��ѯ��Ʒ
	public Product findBypid(Integer pid) {

		return productDao.findBypid(pid);
	}

	// ����һ������cid��ҳ��ѯ��Ʒ
	public pageBean<Product> findByPageCid(Integer cid, int page) {

		pageBean<Product> pageBean = new pageBean<Product>();
		// ���õ�ǰҳ��
		pageBean.setPage(page);
		// ����ÿһҳ��ʾ�ļ�¼��
		int limit = 12;
		pageBean.setLimit(limit);
		// ��ʾ�ܼ�¼��
		int totalCount = 0;
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		// ������ҳ��
		int totalPage = 0;

		// Math.ceil(totalCount/limit);

		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);

		// ���Ŀ�ʼ
		int begin = (page - 1) * limit;

		// ÿҳ��ʾ�����ݼ���
		List<Product> list = productDao.findbyPageCid(cid, begin, limit);
		pageBean.setList(list);

		return pageBean;
	}

	//���ݶ�������csid��ѯ��Ʒ
	public pageBean<Product> findByCsid(Integer csid, int page) {
		
		pageBean<Product> pageBean=new pageBean<Product>();
		//���õ�ǰ��ҳ��
		pageBean.setPage(page);
		//����ÿҳ��ʾ������
		int limit=12;
		pageBean.setLimit(limit);
		//��ʾ�ܵļ�¼��
		int totalCount=0;
		productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		//��ʾ�ܵ�ҳ��
		int totalPage=0;
		totalPage=(int) Math.ceil(totalCount/limit);
		pageBean.setTotalPage(totalPage);
		
		//ҳ����Ŀ�ʼ
		int begin=(page-1)* limit;
		
		//ÿҳ��ʾ�����ݼ���
		List<Product> list=productDao.findbyPageCsid(csid, begin, limit);
		pageBean.setList(list);
		
		return pageBean;
	}

	//��̨��ѯ��Ʒ
	public pageBean<Product> findByPage(Integer page) {
		pageBean<Product> pageBean = new pageBean<Product>();
		// ���õ�ǰҳ��:
		pageBean.setPage(page);
		// ����ÿҳ��ʾ��¼��:
		int limit = 6;
		pageBean.setLimit(limit);
		// �����ܼ�¼��:
		int totalCount = 0;
		totalCount = productDao.findCount();
		pageBean.setTotalCount(totalCount);
		// ������ҳ��:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// ÿҳ��ʾ�����ݼ���:
		// ���Ŀ�ʼ:
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}
	
	
	//�����Ʒ
	public void save(Product product) {
		
		productDao.save(product);
		
	}

	//ɾ����Ʒ
	public void delete(Product product) {
		
		productDao.delete(product);
		
	}

	//�޸���Ʒ
	public void update(Product product) {
		
		productDao.update(product);
		
	}

}
