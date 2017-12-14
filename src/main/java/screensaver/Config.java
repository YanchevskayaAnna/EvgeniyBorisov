package screensaver;

import org.springframework.context.annotation.*;

import java.awt.*;
import java.util.Random;

@Configuration
@ComponentScan(basePackages = "screensaver")
public class Config {
    @Bean
//    @Scope("prototype") если так поставить, будут разные цвета
//    @Scope("singleton") если так поставить, будет один и тот же цвет
    @Scope("periodical") //хочу, чтобы цвет менялся каждые 3 секунды. но такого скоупа нет, пишем свой
    public Color color(){
        Random random = new Random();
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    @Bean
    public ColorFrame frame(){
        return new ColorFrame(){

            protected Color getColor() {
                return color();
            }
        };
    }

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        while (true) {
            context.getBean(ColorFrame.class).ShowOnRandomPlace();
            Thread.sleep(200);
        }
    }
}
