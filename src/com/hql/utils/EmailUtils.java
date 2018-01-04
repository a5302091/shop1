package com.hql.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * 给用户发送账号激活码邮件
 * 
 * @author Administrator
 *
 */
public class EmailUtils {

	/**
	 * 1.创建Session对象 
	 * 2.创建一个代表邮件对象的Message 
	 * 3.发送邮件Transport
	 */
	
	public static void setEmail(String to,String code){
	
	// 获取连接对象
	Properties pros = new Properties();
	pros.setProperty("mail.host", "localhost");
	Session session = Session.getInstance(pros, new Authenticator() {

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {

			return new PasswordAuthentication("service@shop.com", "111");
		}

	});
	
	//创建邮件对象
		Message message=new MimeMessage(session);
	
	try {
		//设置发件人
		message.setFrom(new InternetAddress("service@shop.com"));
		//设置收件人
		message.addRecipient(RecipientType.TO, new InternetAddress(to));
		//给邮箱设置标题
		message.setSubject("来自激活用户的一封邮件");
		//发送的邮件内容		
		message.setContent("<h1>我的商城激活邮件!点击下面的链接激活用户</h1><h3><a href='http://192.168.123.91:8080/my_shop/user_active.action?code="+code+"'>http://192.168.123.91:8080/shop/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
		//发送邮件
		Transport.send(message);
	} catch (MessagingException e) {
		e.printStackTrace();
	}
	
	
	}
	
	public static void main(String[] args) {
		setEmail("aaa@shop.com", "520025520025");
	}

}
