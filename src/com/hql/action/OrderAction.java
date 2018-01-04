package com.hql.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.hql.entity.Cart;
import com.hql.entity.CartItem;
import com.hql.entity.Order;
import com.hql.entity.OrderItem;
import com.hql.entity.Product;
import com.hql.entity.User;
import com.hql.service.OrderService;
import com.hql.service.ProductService;
import com.hql.utils.PaymentUtil;
import com.hql.utils.pageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ��������Action
 * 
 * @author Administrator
 *
 */

public class OrderAction extends ActionSupport implements ModelDriven<Order> {

	private Order order = new Order();

	@Override
	public Order getModel() {
		return order;
	}

	// ֧���ɹ������Ӧ
	private String r6_Order;

	// ֧�����
	private String r3_Amt;

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	// ����֧������
	private String pd_FrpId;

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	// ����page
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPage() {
		return page;
	}

	// ע��Serrvice
	private OrderService orderService;

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public String save() {

		// ����ʱ��
		order.setOrdertime(new Date());

		// ����״̬
		order.setState(1);// 1:����δ���� 2:�Ѿ�����,û�з��� 3:�Ѿ�����,����û���ջ� 4:�������

		// ��ȡ���ﳵ�е���Ϣ,ת�Ƶ�������
		Cart car = (Cart) ServletActionContext.getRequest().getSession().getAttribute("car");

		// �ж�
		if (car == null) {
			this.addActionError("�㻹û�й���,����ȥ�����");
			return "msg";
		} else {
			// ��ȡ�����ܼ�
			order.setTotal(car.getTotal());
		}
		// ���ö�����
		for (CartItem cartItem : car.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setOrder(order);

			order.getOrderItems().add(orderItem);
		}

		// ���ö����������û�
		User u = (User) ServletActionContext.getRequest().getSession().getAttribute("u");
		if (u == null) {
			this.addActionError("�����ȵ�½��ȥ������Ʒ!!!");

			return LOGIN;
		}
		order.setUser(u);
		orderService.save(order);

		//

		return "save";
	}

	// ��ʾ�ҵĶ���
	public String findByUid() {

		// �����û�id��ѯ�û�
		User u = (User) ServletActionContext.getRequest().getSession().getAttribute("u");
		Integer uid = u.getUid();
		// ����Service
		pageBean<Order> pageBean = orderService.findByUid(uid, page);

		// ��ʾ����
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);

		return "findByUid";

	}

	// ���ݶ���id��ѯ�������и���
	public String findByOid() {

		order = orderService.findByOid(order.getOid());

		return "findByOid";
	}

	// Ϊ��������:
	public String payOrder() throws IOException {
		// 1.�޸�����:
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		// �޸Ķ���
		orderService.update(currOrder);
		// 2.��ɸ���:
		// ������Ҫ�Ĳ���:
		String p0_Cmd = "Buy"; // ҵ������:
		String p1_MerId = "10001126856";// �̻����:
		String p2_Order = order.getOid().toString();// �������:
		String p3_Amt = "0.01"; // ������:
		String p4_Cur = "CNY"; // ���ױ���:
		String p5_Pid = ""; // ��Ʒ����:
		String p6_Pcat = ""; // ��Ʒ����:
		String p7_Pdesc = ""; // ��Ʒ����:
		String p8_Url = "http://localhost:8080/my_shop/order_callBack.action"; // �̻�����֧���ɹ����ݵĵ�ַ:
		String p9_SAF = ""; // �ͻ���ַ:
		String pa_MP = ""; // �̻���չ��Ϣ:
		String pd_FrpId = this.pd_FrpId;// ֧��ͨ������:
		String pr_NeedResponse = "1"; // Ӧ�����:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // ��Կ
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue); // hmac
		// ���ױ���������:
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		
		// �ض���:���ױ�����:
		ServletActionContext.getResponse().sendRedirect(sb.toString());
		return NONE;
	}
	
	// ����ɹ�����ת������·��:
	public String callBack(){
		// �޸Ķ�����״̬:
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		// �޸Ķ���״̬Ϊ2:�Ѿ�����:
		currOrder.setState(2);
		orderService.update(currOrder);
		this.addActionMessage("֧���ɹ�!�������Ϊ: "+r6_Order +" ������Ϊ: "+r3_Amt);
		return "msg";
	}
	
	
}
