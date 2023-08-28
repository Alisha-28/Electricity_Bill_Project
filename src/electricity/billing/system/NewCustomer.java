package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class NewCustomer extends JFrame implements ActionListener{
    
    JTextField name, address, state, city, email, phone;
    JButton next, cancel;
    JLabel meter;
    NewCustomer(){
        
        JPanel p=new JPanel();
        p.setBackground(Color.WHITE);
        p.setLayout(null);
        add(p);
        Color logincolor=new Color(52,181,86);
        
        JLabel heading=new JLabel("New Customer");
        heading.setForeground(Color.RED);
        heading.setBounds(250, 40, 200, 20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        p.add(heading);
        
        JLabel lblname=new JLabel("Customer Name");
        lblname.setBounds(130, 120, 100, 20);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblname);
        
        name=new JTextField();
        name.setBounds(270, 120, 200, 20);
        p.add(name);
        
        JLabel lblmeter=new JLabel("Meter Number");
        lblmeter.setBounds(130, 160, 100, 20);
        lblmeter.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblmeter);
        
        meter=new JLabel();
        meter.setBounds(270, 160, 100, 20);
        meter.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(meter);
        
        Random rm=new Random();
        long number=rm.nextLong() % 1000000;
        meter.setText(""+Math.abs(number));
        
        JLabel lbladdress=new JLabel("Address");
        lbladdress.setBounds(130, 200, 100, 20);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lbladdress);
        
        address=new JTextField();
        address.setBounds(270, 200, 200, 20);
        p.add(address);
        
        JLabel lblcity=new JLabel("City");
        lblcity.setBounds(130, 240, 100, 20);
        lblcity.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblcity);
        
        city=new JTextField();
        city.setBounds(270, 240, 200, 20);
        p.add(city);
        
        JLabel lblstate=new JLabel("State");
        lblstate.setBounds(130, 280, 100, 20);
        lblstate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblstate);
        
        state=new JTextField();
        state.setBounds(270, 280, 200, 20);
        p.add(state);
        
        JLabel lblemail=new JLabel("Email");
        lblemail.setBounds(130, 320, 100, 20);
        lblemail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblemail);
        
        email=new JTextField();
        email.setBounds(270, 320, 200, 20);
        p.add(email);
        
        JLabel lblphone=new JLabel("Phone");
        lblphone.setBounds(130, 360, 100, 20);
        lblphone.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblphone);
        
        phone=new JTextField();
        phone.setBounds(270, 360, 200, 20);
        p.add(phone);
        
        
        //Buttons
        next=new JButton("Next");
        next.setBounds(160, 420, 100, 30);
        next.setBackground(logincolor);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);
        
        cancel=new JButton("Cancel");
        cancel.setBounds(330, 420, 100, 30);
        cancel.setBackground(Color.GRAY);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        p.add(cancel);
        
        
        
        setLayout(new BorderLayout());
        add(p, "Center");
        setSize(620,550);
        setLocation(330,75);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==next){
            String cname=name.getText();
            String cmeter=meter.getText();
            String cadd=address.getText();
            String ccity=city.getText();
            String cstate=state.getText();
            String cemail=email.getText();
            String cphone=phone.getText();
            
            String query1="insert into customer values('"+cname+"', '"+cmeter+"', '"+cadd+"', '"+ccity+"', '"+cstate+"', '"+cemail+"', '"+cphone+"')";
            String query2="insert into login values('"+cmeter+"', ' ', '"+cname+"', ' ', ' ')";
            
            try{
                Database db=new Database();
                db.s.executeUpdate(query1);
                db.s.executeUpdate(query2);
                
                JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
                setVisible(false);
                
                new MeterInfo(cmeter);
                
            }   catch(Exception e){
                e.printStackTrace();
            }
        
        }
        
    else{
    setVisible(false);
    }
}
    
    public static void main(String []args){
        new NewCustomer();
    }
    
}
