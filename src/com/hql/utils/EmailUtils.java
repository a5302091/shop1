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
 * ���û������˺ż������ʼ�
 * 
 * @author Administrator
 *
 */
public class EmailUtils {

	/**
	 * 1.����Session���� 
	 * 2.����һ�������ʼ������Message 
	 * 3.�����ʼ�Transport
	 */
	
	public static void setEmail(String to,String code){
	
	// ��ȡ���Ӷ���
	Properties pros = new Properties();
	pros.setProperty("mail.host", "localhost");
	Session session = Session.getInstance(pros, new Authenticator() {

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {

			return new PasswordAuthentication("service@shop.com", "111");
		}

	});
	
	//�����ʼ�����
		Message message=new MimeMessage(session);
	
	try {
		//���÷�����
		message.setFrom(new InternetAddress("service@shop.com"));
		//�����ռ���
		message.addRecipient(RecipientType.TO, new InternetAddress(to));
		//���������ñ���
		message.setSubject("���Լ����û���һ���ʼ�");
		//���͵��ʼ�����		
		message.setContent("<h1>�ҵ��̳Ǽ����ʼ�!�����������Ӽ����û�</h1><h3><a href='http://192.168.123.91:8080/my_shop/user_active.action?code="+code+"'>http://192.168.123.91:8080/shop/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
		//�����ʼ�
		Transport.send(message);
	} catch (MessagingException e) {
		e.printStackTrace();
	}
	
	
	}
	
	public static void main(String[] args) {
		setEmail("aaa@shop.com", "520025520025");
	}

}
