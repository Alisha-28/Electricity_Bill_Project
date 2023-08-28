package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PayBill extends JFrame implements ActionListener{
    
    String meternum;
    JButton pay, cancel;
    Choice month;
    JLabel meter, name, address, units, totalbill, status;
    PayBill(String meternum){
        this.meternum=meternum;
        JPanel p=new JPanel();
        p.setBackground(Color.WHITE);
        p.setLayout(null);
        add(p);
        Color logincolor=new Color(52,181,86);
        
        JLabel heading=new JLabel("Pay Bill");
        heading.setForeground(Color.RED);
        heading.setBounds(280, 40, 200, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        p.add(heading);
        
        
        JLabel lblmeter=new JLabel("Meter Number");
        lblmeter.setBounds(130, 120, 100, 20);
        lblmeter.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblmeter);
        
        meter=new JLabel("");
        meter.setBounds(270, 120, 200, 20);
        meter.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(meter);
        
        
        JLabel lblname=new JLabel("Customer Name");
        lblname.setBounds(130, 160, 100, 20);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblname);
        
        name=new JLabel("");
        name.setBounds(270, 160, 100, 20);
        p.add(name);
       
        
        JLabel lblmonth=new JLabel("Month");
        lblmonth.setBounds(130, 200, 100, 20);
        lblmonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblmonth);
        
        month=new Choice();
        month.setBounds(270, 200, 200, 20);
        month.add("January");
        month.add("February");
        month.add("March");
        month.add("April");
        month.add("May");
        month.add("June");
        month.add("July");
        month.add("August");
        month.add("September");
        month.add("October");
        month.add("November");
        month.add("December");
        add(month);
        
        
        JLabel lblunits=new JLabel("Units");
        lblunits.setBounds(130, 240, 100, 20);
        lblunits.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblunits);
        
        units=new JLabel("");
        units.setBounds(270, 240, 200, 20);
        p.add(units);
        
        
        JLabel lbltotalbill=new JLabel("Total Bill");
        lbltotalbill.setBounds(130, 280, 100, 20);
        lbltotalbill.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lbltotalbill);
        
        totalbill=new JLabel("");
        totalbill.setBounds(270, 280, 200, 20);
        p.add(totalbill);
        
        
        JLabel lblstatus=new JLabel("Status");
        lblstatus.setBounds(130, 320, 100, 20);
        lblstatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblstatus);
        
        status=new JLabel("");
        status.setBounds(270, 320, 200, 20);
        status.setForeground(Color.red);
        p.add(status);
        
        try{
                Database db=new Database();
                ResultSet rs=db.s.executeQuery("select * from customer where meter_no='"+meternum+"' ");
                
                while(rs.next()){
                    meter.setText(meternum);
                    name.setText(rs.getString("name"));
                }
                
                
            }   catch(Exception e){
                e.printStackTrace();
            }
        
        month.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie){
                
                try{
                Database db=new Database();
                ResultSet rs=db.s.executeQuery("select * from bill where meter_no='"+meternum+"' and month='"+month.getSelectedItem()+"' ");
                
                while(rs.next()){
                    units.setText(rs.getString("units"));
                    totalbill.setText(rs.getString("totalbill"));
                    status.setText(rs.getString("status"));
                }
                
                
            }   catch(Exception e){
                e.printStackTrace();
            }
        
                
            }
            
        });
        
        
        //Buttons
        pay=new JButton("Pay");
        pay.setBounds(160, 420, 100, 30);
        pay.setBackground(logincolor);
        pay.setForeground(Color.WHITE);
        pay.addActionListener(this);
        p.add(pay);
        
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
        if(ae.getSource()==pay){
            try{
            Database db=new Database();
            db.s.executeUpdate("update bill set status= 'Paid' where meter_no='"+meternum+"' and month='"+month.getSelectedItem()+"' ");
            }
            catch (Exception e){
                e.printStackTrace();
            }
            setVisible(false);
            new Paytm(meternum);
        }
        
        
    else{
    setVisible(false);
    }
}
    
    public static void main(String []args){
        new PayBill("");
    }
    
}
