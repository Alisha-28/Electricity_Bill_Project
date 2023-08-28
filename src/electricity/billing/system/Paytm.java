package electricity.billing.system;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Paytm extends JFrame implements ActionListener{
    
    JButton back;
    String meternum;
    Paytm(String meternum){
        this.meternum=meternum;
        
        JEditorPane j=new JEditorPane();
        j.setEditable(false);
        try{
            
            j.setPage("https://paytm.com/online-payments");
        
        }catch(Exception e){
            j.setContentType("text/html");
            j.setText("<html>Paytm Loading Failed<html>");
        }
        
        JScrollPane pane=new JScrollPane(j);
        add(pane);
        
        back=new JButton("Back");
        back.setBounds(1150, 30, 100, 30);
        back.setBackground(Color.red);
        back.setForeground(Color.white);
        back.addActionListener(this);
        j.add(back);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        
        setVisible(false);
        new PayBill(meternum);
        
    }
    
    public static void main(String[] args){
        new Paytm("");
    }
    
}
