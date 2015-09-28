package com.pactera.common.mail;

public class Test {

	public static void main(String[] args) {
	
		// 这个类主要是设置邮件  
        MailSendInfo mailInfo = new MailSendInfo();  
        mailInfo.setMailServerHost("smtp.163.com"); //smtp.qq.com 
        mailInfo.setMailServerPort("25");  
        mailInfo.setValidate(true);  
        mailInfo.setUserName("ahczln@163.com"); // 实际发送者  
        mailInfo.setPassword("lunan449088092");// 您的邮箱密码  
        mailInfo.setFromAddress("ahczln@163.com"); // 设置发送人邮箱地址  
        
        mailInfo.setToAddress("449088092@qq.com"); // 设置接受者邮箱地址  
        mailInfo.setSubject("标题");  
        mailInfo.setContent("内容<b>h6</b>");  
        // 这个类主要来发送邮件  
        SimpleMailSender sms = new SimpleMailSender();  
        //sms.sendTextMail(mailInfo); // 发送文体格式  
        sms.sendHtmlMail(mailInfo); // 发送html格式  
	}
}
