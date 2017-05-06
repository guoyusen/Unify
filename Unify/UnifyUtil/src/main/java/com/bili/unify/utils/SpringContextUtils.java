package com.bili.unify.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextUtils {

	private SpringContextUtils() {}
	
	public static ApplicationContext getApplicationSpringContext(String configPath) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configPath);
		return applicationContext;
	}
	
}
