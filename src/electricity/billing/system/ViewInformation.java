package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ViewInformation extends JFrame implements ActionListener{
    
    JButton cancel;
    String meternum;
    ViewInformation(String meternum){
        this.meternum=meternum;
        JPanel p=new JPanel();
        p.setBackground(Color.WHITE);
        p.setLayout(null);
        add(p);
        
        JLabel heading=new JLabel("Customer Information");
        heading.setForeground(Color.RED);
        heading.setBounds(190, 40, 400, 20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        p.add(heading);
        
        JLabel lblname=new JLabel("Customer Name");
        lblname.setBounds(180, 120, 100, 20);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblname);
        
        JLabel name=new JLabel(" ");
        name.setBounds(350, 120, 200, 20);
        name.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(name);
       
        
        JLabel lblmeter=new JLabel("Meter Number");
        lblmeter.setBounds(180, 160, 100, 20);
        lblmeter.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblmeter);
        
        JLabel meter=new JLabel(" ");
        meter.setBounds(350, 160, 100, 20);
        meter.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(meter);
        
        
        JLabel lbladdress=new JLabel("Address");
        lbladdress.setBounds(180, 200, 100, 20);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lbladdress);
        
        JLabel address=new JLabel("");
        address.setBounds(350, 200, 200, 20);
        address.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(address);
        
        
        JLabel lblcity=new JLabel("City");
        lblcity.setBounds(180, 240, 100, 20);
        lblcity.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblcity);
        
        JLabel city=new JLabel("");
        city.setBounds(350, 240, 200, 20);
        city.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(city);
        
        
        JLabel lblstate=new JLabel("State");
        lblstate.setBounds(180, 280, 100, 20);
        lblstate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblstate);
        
        JLabel state=new JLabel();
        state.setBounds(350, 280, 200, 20);
        state.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(state);
        
        
        JLabel lblemail=new JLabel("Email");
        lblemail.setBounds(180, 320, 100, 20);
        lblemail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblemail);
        
        JLabel email=new JLabel("");
        email.setBounds(350, 320, 200, 20);
        email.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(email);
        
        
        JLabel lblphone=new JLabel("Phone");
        lblphone.setBounds(180, 360, 100, 20);
        lblphone.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblphone);
        
        JLabel phone=new JLabel("");
        phone.setBounds(350, 360, 200, 20);
        phone.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(phone);
        
        try {
                Database db=new Database();
                ResultSet rs=db.s.executeQuery("select * from customer where meter_no='"+meternum+"' ");
                while(rs.next()){
                    name.setText(rs.getString("name"));
                    address.setText(rs.getString("address"));
                    city.setText(rs.getString("city"));
                    state.setText(rs.getString("state"));
                    email.setText(rs.getString("email"));
                    phone.setText(rs.getString("phone"));
                    meter.setText(rs.getString("meter_no"));
                }
        }
            catch (Exception e){
                e.printStackTrace();
            }
        
        
        cancel=new JButton("Cancel");
        cancel.setBounds(260, 420, 100, 30);
        cancel.addActionListener(this);
        p.add(cancel);
       
        setLayout(new BorderLayout());
        add(p, "Center");
        setSize(620,550);
        setLocation(330,75);
        setVisible(true);
    }
    
public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==cancel){
            setVisible(false);
        }   
}
        
    
    public static void main(String []args){
        new ViewInformation("");
    }
    
}
