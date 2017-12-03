package quaters;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        //Итого. 1-й конструктор - java 2-й конструктор - @PostConstruct 3-й конструктор - @AfterProxy

        //запускаем jvisualvm в jdk, добавляем плагим MBean, устанавливаем признак enabled
        // с помощью этого признака включаем или отключаем профилирование на хожу в режиме выполнения программы
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/spring-settings.xml");
        //while (true) {
            //Thread.sleep(100);
            context.getBean(Quater.class).sayQuote(); //для тестирования 3-х фазового вызывать не нужно, он сам вызовется за счет аннотации
        //}
    }
}
