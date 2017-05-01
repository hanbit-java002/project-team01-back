/*package com.resell.hp.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	
	@Bean
	public static JavaMailSender mailSender(){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		// Properties 설정 
		Properties props = new Properties(); 
		props.put("mail.smtp.starttls.enable","true");//설정하지 않으면 에러 발생

		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.quitwait","false");
		props.put("mail.debug","true"); // 디버그 모드 : 개발이 완료되면 false

		mailSender.setJavaMailProperties(props);

		mailSender.setProtocol("smtp");
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("hanbitresell@gmail.com");	//보내는 사람의 gmail계정
		mailSender.setPassword("hanbit20170524");	//비밀번호
		mailSender.setDefaultEncoding("utf-8");
	
		return mailSender;
	}
}*/