package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class CalculateBill extends JFrame implements ActionListener{
    
    JTextField  units;
    JButton submit, cancel;
    JLabel meter, name, address;
    Choice meternumber, month;
    CalculateBill(){
        
        JPanel p=new JPanel();
        p.setBackground(Color.WHITE);
        p.setLayout(null);
        add(p);
        Color logincolor=new Color(52,181,86);
        
        JLabel heading=new JLabel("Calculate Electricity Bill");
        heading.setForeground(Color.RED);
        heading.setBounds(180, 40, 270, 40);
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        p.add(heading);
        
        JLabel lblmeter=new JLabel("Meter Number");
        lblmeter.setBounds(130, 120, 100, 20);
        lblmeter.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblmeter);
        
        meternumber=new Choice();
        
        try{
            Database db=new Database();
            ResultSet rs=db.s.executeQuery("select * from customer");
            
            while(rs.next()){
                meternumber.add(rs.getString("meter_no"));
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
        
        meternumber.setBounds(270, 120, 200, 20);
        p.add(meternumber);
        
        JLabel lblname=new JLabel("Name");
        lblname.setBounds(130, 160, 100, 20);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblname);
        
        name=new JLabel();
        name.setBounds(270, 160, 100, 20);
        name.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(name);
        
        JLabel lbladdress=new JLabel("Address");
        lbladdress.setBounds(130, 200, 100, 20);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lbladdress);
        
        address=new JLabel();
        address.setBounds(270, 200, 200, 20);
        p.add(address);
        
        try{
            Database db=new Database();
            ResultSet rs=db.s.executeQuery("select * from customer where meter_no= '"+meternumber.getSelectedItem()+"' ");
            
            while(rs.next()){
                name.setText(rs.getString("name"));
                address.setText(rs.getString("address"));
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
        
        meternumber.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                    try{
                    Database db=new Database();
                    ResultSet rs=db.s.executeQuery("select * from customer where meter_no= '"+meternumber.getSelectedItem()+"' ");

                    while(rs.next()){
                        name.setText(rs.getString("name"));
                        address.setText(rs.getString("address"));
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        
        JLabel lblunits=new JLabel("Units");
        lblunits.setBounds(130, 240, 100, 20);
        lblunits.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblunits);
        
        units=new JTextField();
        units.setBounds(270, 240, 200, 20);
        p.add(units);
        
        JLabel lblmonth=new JLabel("Month");
        lblmonth.setBounds(130, 280, 100, 20);
        lblmonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblmonth);
        
        month=new Choice();
        month.setBounds(270, 280, 200, 20);
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
        p.add(month);
        
        
        //Buttons
        submit=new JButton("Submit");
        submit.setBounds(160, 400, 100, 30);
        submit.setBackground(logincolor);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        p.add(submit);
        
        cancel=new JButton("Cancel");
        cancel.setBounds(330, 400, 100, 30);
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
        if(ae.getSource()==submit){
            String cmeter=meternumber.getSelectedItem();
            String cunits=units.getText();
            String cmonths=month.getSelectedItem();
            
            int totalbill=0;
            int unit_consumed=Integer.parseInt(cunits);
            
            String query="select * from tax";
            
            try{
                Database db=new Database();
                
                ResultSet rs=db.s.executeQuery(query);
                
                while(rs.next()){
                    totalbill+=unit_consumed * Integer.parseInt(rs.getString("cost_per_unit"));
                    totalbill+=Integer.parseInt(rs.getString("meter_rent")) + Integer.parseInt(rs.getString("service_charge"))+Integer.parseInt(rs.getString("service_tax"))+Integer.parseInt(rs.getString("swacch_bharat_cess"))+Integer.parseInt(rs.getString("fixed_tax"));
                
                }
            }   catch(Exception e){
                e.printStackTrace();
            }
            
            String query2="insert into bill values ('"+cmeter+"', '"+cmonths+"', '"+cunits+"', '"+totalbill+"', 'Not Paid')";
            
            try{
                Database db=new Database();
                
                db.s.executeUpdate(query2);
                
                JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully");
                setVisible(false);
                
            }   catch(Exception e){
                e.printStackTrace();
            }
        
        }
        
        
        
    else{
    setVisible(false);
    }
}
    
    public static void main(String []args){
        new CalculateBill();
    }
    
}
