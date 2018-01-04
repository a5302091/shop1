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
 * 订单管理Action
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

	// 支付成功后的响应
	private String r6_Order;

	// 支付金额
	private String r3_Amt;

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	// 接收支付银行
	private String pd_FrpId;

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	// 接收page
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPage() {
		return page;
	}

	// 注入Serrvice
	private OrderService orderService;

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public String save() {

		// 订单时间
		order.setOrdertime(new Date());

		// 订单状态
		order.setState(1);// 1:代表未付款 2:已经付款,没有发货 3:已经发货,但是没有收货 4:交易完成

		// 获取购物车中的信息,转移到订单上
		Cart car = (Cart) ServletActionContext.getRequest().getSession().getAttribute("car");

		// 判断
		if (car == null) {
			this.addActionError("你还没有购物,请先去购物吧");
			return "msg";
		} else {
			// 获取购物总计
			order.setTotal(car.getTotal());
		}
		// 设置订单项
		for (CartItem cartItem : car.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setOrder(order);

			order.getOrderItems().add(orderItem);
		}

		// 设置订单所属的用户
		User u = (User) ServletActionContext.getRequest().getSession().getAttribute("u");
		if (u == null) {
			this.addActionError("请您先登陆在去购买物品!!!");

			return LOGIN;
		}
		order.setUser(u);
		orderService.save(order);

		//

		return "save";
	}

	// 显示我的订单
	public String findByUid() {

		// 根据用户id查询用户
		User u = (User) ServletActionContext.getRequest().getSession().getAttribute("u");
		Integer uid = u.getUid();
		// 调用Service
		pageBean<Order> pageBean = orderService.findByUid(uid, page);

		// 显示数据
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);

		return "findByUid";

	}

	// 根据订单id查询订单进行付款
	public String findByOid() {

		order = orderService.findByOid(order.getOid());

		return "findByOid";
	}

	// 为订单付款:
	public String payOrder() throws IOException {
		// 1.修改数据:
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		// 修改订单
		orderService.update(currOrder);
		// 2.完成付款:
		// 付款需要的参数:
		String p0_Cmd = "Buy"; // 业务类型:
		String p1_MerId = "10001126856";// 商户编号:
		String p2_Order = order.getOid().toString();// 订单编号:
		String p3_Amt = "0.01"; // 付款金额:
		String p4_Cur = "CNY"; // 交易币种:
		String p5_Pid = ""; // 商品名称:
		String p6_Pcat = ""; // 商品种类:
		String p7_Pdesc = ""; // 商品描述:
		String p8_Url = "http://localhost:8080/my_shop/order_callBack.action"; // 商户接收支付成功数据的地址:
		String p9_SAF = ""; // 送货地址:
		String pa_MP = ""; // 商户扩展信息:
		String pd_FrpId = this.pd_FrpId;// 支付通道编码:
		String pr_NeedResponse = "1"; // 应答机制:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue); // hmac
		// 向易宝发送请求:
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
		
		// 重定向:向易宝出发:
		ServletActionContext.getResponse().sendRedirect(sb.toString());
		return NONE;
	}
	
	// 付款成功后跳转回来的路径:
	public String callBack(){
		// 修改订单的状态:
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		// 修改订单状态为2:已经付款:
		currOrder.setState(2);
		orderService.update(currOrder);
		this.addActionMessage("支付成功!订单编号为: "+r6_Order +" 付款金额为: "+r3_Amt);
		return "msg";
	}
	
	
}
