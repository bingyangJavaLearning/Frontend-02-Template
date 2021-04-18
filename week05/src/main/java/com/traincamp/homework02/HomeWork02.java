package com.traincamp.homework02;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HomeWork02 {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//1、基于xml方式
		Object xmlBean = context.getBean("xmlBean");
		System.out.println("1、通过xml方式注册的bean："+xmlBean);
		//2、基于@Configuration注解配置方式
		ConfigurationBean configurationBean = context.getBean(ConfigurationBean.class);
		System.out.println("2、通过@Configuration方式注册的bean："+configurationBean);
		//3、基于@Controller,@Service,@Compont方式,可使用byType方式获取，或指定bean名字通过byName方式获取
		Object controllerBean = context.getBean("controllerBean");
		System.out.println("3、通过扫描包+注解方式注册的bean（byName）："+controllerBean);
		ComponentBean componentBean = context.getBean(ComponentBean.class);
		System.out.println("3、通过扫描包+注解方式注册的bean（byType）"+componentBean);
		//4、使用beanFactory 动态生成bean
		boolean containsBean = context.containsBean("userService");
		System.out.println("动态注册bean前，当前容器中是否包含名为userService的bean："+containsBean);
		defineBean((ClassPathXmlApplicationContext)context, "userService", UserService.class);
		containsBean = context.containsBean("userService");
		System.out.println("动态注册bean后，当前容器中是否包含名为userService的bean："+containsBean);
		if (containsBean) {
			Object bean = context.getBean("userService");
			System.out.println("4、通过运行时动态注册的bean"+bean);
		}
	}
	
	private static void defineBean(ClassPathXmlApplicationContext context,String beanName,Class<?> clazz) {
		//1、获取beanFactory
		DefaultListableBeanFactory factory = (DefaultListableBeanFactory)context.getBeanFactory();
		//2、定义bean
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
		//3、添加属性
		builder.addPropertyValue("id", 21);
		builder.addPropertyValue("name", clazz.getSimpleName()+"Bean");
		//4、注册bean
		factory.registerBeanDefinition(beanName, builder.getBeanDefinition());
	}

}
