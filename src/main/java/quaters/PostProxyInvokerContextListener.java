package quaters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Method;

//для 3-х фазового конструктора
public class PostProxyInvokerContextListener implements ApplicationListener<ContextRefreshedEvent>{ //generic
    @Autowired //будем разговаривать с главной фабрикой спринга
    private ConfigurableListableBeanFactory factory;

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext context = contextRefreshedEvent.getApplicationContext();
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            //Найти бин по имени мы не можем, потому что там уже будет прокси
            //поэтому мы обращаемся к главной фабрике, потому что только у неё есть метод getBeanDefinition
            BeanDefinition beanDefinition = factory.getBeanDefinition(name);
            String originalClassName = beanDefinition.getBeanClassName();
            try {
                Class<?> originalClass = Class.forName(originalClassName);
                Method[] methods = originalClass.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(PostProxy.class)){
                        //method.invoke(); работать не будет
                        Object bean = context.getBean(name);
                        Method currentMethod = bean.getClass().getMethod(method.getName(), method.getParameterTypes());
                        currentMethod.invoke(bean);
                    }
                }
            //} catch (ClassNotFoundException e) {
            } catch (Exception e) {    //чтобы сразу все охватить
                e.printStackTrace();
            }


        }

    }
}
