package com.raju.uitls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender mailsender;
	
	
	public boolean sendmail(String to,String body,String sub) {
		
		boolean issent=false;
		
		try {
			
			MimeMessage mimeMessage = mailsender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
			helper.setTo(to);
			helper.setSubject(sub);
			helper.setText(body,true);
			mailsender.send(mimeMessage);
			issent=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return issent;
	}
}
