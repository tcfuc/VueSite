package com.ccsu.zy.container;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 *@Author swxctx
 *@time 2017��5��11��
 *@Explain:�����࣬���û�����applicationContext.xml�ļ�
 */
public class ServiceProvideCord {
	
	protected static ApplicationContext applicationContext;
	
	public static void load(String fileName){
		applicationContext = new ClassPathXmlApplicationContext(fileName);
	}
}
