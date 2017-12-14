package screensaver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

@Component
@Scope("prototype")
public abstract class ColorFrame extends JFrame{
//    @Autowired
    private Color color; //всегда 1 цвет

    public ColorFrame() throws HeadlessException {
        setSize(200, 200);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void ShowOnRandomPlace(){
        Random random = new Random();
        setLocation(random.nextInt(1200), random.nextInt(700));
//        getContentPane().setBackground(color); всегда 1 цвет будет
        getContentPane().setBackground(getColor());
        repaint();
    }

    //если не сделать абстрактным, всегда будет 1 цвет
    protected abstract Color getColor();
}
