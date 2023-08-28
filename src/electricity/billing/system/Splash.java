package electricity.billing.system;
import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable{
    Thread t;
    Splash(){
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/splash2.png"));
        Image i2=i1.getImage().getScaledInstance(500, 400, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        add(image);
        
        setSize(500,400);
        setLocation(430,150);
        setVisible(true);
        
        t=new Thread(this);
        t.start();
    }
    
    public void run()
    {
        try {
            Thread.sleep(3000);
            setVisible(false);
            
            //login frame
            new Login();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main (String[] args)
    {
        new Splash();
    }
    
}
