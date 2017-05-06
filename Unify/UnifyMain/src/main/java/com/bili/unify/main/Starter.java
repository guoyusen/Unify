package com.bili.unify.main;

import org.springframework.context.ApplicationContext;

import com.bili.unify.main.hjlistening.HJListeningProcessor;
import com.bili.unify.utils.SpringContextUtils;

public class Starter {

	private static ApplicationContext applicationContext;
	
	public static void main(String[] args) throws Exception {
		applicationContext = SpringContextUtils.getApplicationSpringContext("classpath:spring-mybatis.xml");
		HJListeningProcessor.downloadMP3(applicationContext);
	}
	
}
