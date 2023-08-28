package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
//import java.sql.*;

public class UpdateInformation extends JFrame implements ActionListener{
    
    JTextField tfaddress, tfstate, tfcity, tfemail, tfphone;
    JButton cancel,update;
    JLabel name, meter;
    String meternum;
    UpdateInformation(String meternum){
        this.meternum=meternum;
        JPanel p=new JPanel();
        p.setBackground(Color.WHITE);
        p.setLayout(null);
        add(p);
        Color logincolor=new Color(52,181,86);
        
        JLabel heading=new JLabel("Update Customer Information");
        heading.setForeground(Color.RED);
        heading.setBounds(160, 40, 400, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        p.add(heading);
        
        JLabel lblname=new JLabel("Customer Name");
        lblname.setBounds(150, 120, 100, 20);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblname);
        
        name=new JLabel(" ");
        name.setBounds(275, 120, 200, 20);
        name.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(name);
       
        
        JLabel lblmeter=new JLabel("Meter Number");
        lblmeter.setBounds(150, 160, 100, 20);
        lblmeter.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblmeter);
        
        meter=new JLabel(" ");
        meter.setBounds(275, 160, 100, 20);
        meter.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(meter);
        
        
        JLabel lbladdress=new JLabel("Address");
        lbladdress.setBounds(150, 200, 100, 20);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lbladdress);
        
         tfaddress=new JTextField();
        tfaddress.setBounds(275, 200, 215, 20);
        tfaddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(tfaddress);
        
        
        JLabel lblcity=new JLabel("City");
        lblcity.setBounds(150, 240, 100, 20);
        lblcity.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblcity);
        
         tfcity=new JTextField();
        tfcity.setBounds(275, 240, 215, 20);
        tfcity.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(tfcity);
        
        
        JLabel lblstate=new JLabel("State");
        lblstate.setBounds(150, 280, 100, 20);
        lblstate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblstate);
        
         tfstate=new JTextField();
        tfstate.setBounds(275, 280, 215, 20);
        tfstate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(tfstate);
        
        
        JLabel lblemail=new JLabel("Email");
        lblemail.setBounds(150, 320, 100, 20);
        lblemail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblemail);
        
         tfemail=new JTextField();
        tfemail.setBounds(275, 320, 215, 20);
        tfemail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(tfemail);
        
        
        JLabel lblphone=new JLabel("Phone");
        lblphone.setBounds(150, 360, 100, 20);
        lblphone.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblphone);
        
         tfphone=new JTextField();
        tfphone.setBounds(275, 360, 215, 20);
        tfphone.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(tfphone);
        
        
        try {
                Database db=new Database();
                ResultSet rs=db.s.executeQuery("select * from customer where meter_no='"+meternum+"' ");
                while(rs.next()){
                    name.setText(rs.getString("name"));
                    tfaddress.setText(rs.getString("address"));
                    tfcity.setText(rs.getString("city"));
                    tfstate.setText(rs.getString("state"));
                    tfemail.setText(rs.getString("email"));
                    tfphone.setText(rs.getString("phone"));
                    meter.setText(rs.getString("meter_no"));
                }
        }
            catch (Exception e){
                e.printStackTrace();
            }
        
        
        
        update=new JButton("Update");
        update.setBounds(170, 420, 100, 30);
        update.setBackground(logincolor);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        p.add(update);
       
        cancel=new JButton("Cancel");
        cancel.setBounds(340, 420, 100, 30);
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
        else{
            String address=tfaddress.getText();
            String city=tfcity.getText();
            String state=tfstate.getText();
            String email=tfemail.getText();
            String phone=tfphone.getText();
            
            try{
                Database db=new Database();
                db.s.executeUpdate("update customer set address='"+address+"', city='"+city+"', state='"+state+"', email='"+email+"', phone='"+phone+"' where meter_no='"+meternum+"'");
                
                JOptionPane.showMessageDialog(null, "User Information Updated Successfully");
                setVisible(false);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
}
        
    
    public static void main(String []args){
        new UpdateInformation("");
    }
    
}
