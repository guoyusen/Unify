package com.bili.unify.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bili.unify.main.hjlistening.HJListeningProcessor;

public class Starter {

	private static ApplicationContext applicationContext;
	
	public static void main(String[] args) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
		
		HJListeningProcessor.downloadMP3(applicationContext);
	}
	
}
