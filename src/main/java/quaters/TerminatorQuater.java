package quaters;

import javax.annotation.PostConstruct;

@DeprecatedClass(NewImpl = T1000.class)
@Profiling
public class TerminatorQuater implements Quater {
    @InjectRandomInt (min = 2, max  = 7)
    private int repeat;
    private String message;

    public TerminatorQuater() {
        System.out.println("phase 1");
        //System.out.println(repeat); будет напечатано 0, на этом этапе repeat не инициализировано
    }

    @PostConstruct
    public void init(){
        System.out.println("phase 2");
        System.out.println(repeat);
    }

    //@PostConstruct //Добавляем 3-й фазовый конструктор
    @PostProxy
    public void sayQuote() {
        for (int i = 0; i < repeat; i++) {
            System.out.println("Message " + message);
        }

    }

    public void setMessage(String message) {
        this.message = message;
    }
}
