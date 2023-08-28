package electricity.billing.system;
import java.awt.*;
import java.sql.ResultSet;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class GenerateBill extends JFrame implements ActionListener{
    
    
    JTable table;
    JButton bill;
    JTextArea area;
    String meternum;
    Choice month; 
    GenerateBill(String meternum){
        this.meternum=meternum;
        
        setSize(500, 650);
        setLocation(425, 20);
        
        setLayout(new BorderLayout());
        
        JPanel p=new JPanel();
        
        JLabel lblmeter=new JLabel("");
        
        
        month=new Choice();
        
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
        
        area=new JTextArea(50, 15);
        area.setText("\n\t1. Select Your Month\n\t2. Click \"Generate Bill\" Button\n\n");
        area.setFont(new Font("Tahoma", Font.BOLD, 16));
        JScrollPane pane=new JScrollPane(area);
        
        bill=new JButton("Generate Bill");
        bill.addActionListener(this);
        
        p.add(lblmeter);
        p.add(month);
        p.add(bill);
        add(p, "North");
        add(pane, "Center");
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        try
            {
                Database db=new Database();
                
                String cmonth=month.getSelectedItem();
                
                area.setText("\n\t     Jaipur Vidyut Vitran Nigam Limited");
                area.append("\n\t  Electricity Bill for the month of "+cmonth+"\n\n");
                area.setFont(new Font("Tahoma", Font.PLAIN, 15));
                
                ResultSet rs=db.s.executeQuery("select * from customer where meter_no='"+meternum+"'");
                
                if(rs.next()){
                    area.append("\n    Customer Name:  "+rs.getString("name"));
                    area.append("\n    Meter Number:  "+rs.getString("meter_no"));
                    area.append("\n    Address:  "+rs.getString("address"));
                    area.append("\n    City:  "+rs.getString("city"));
                    area.append("\n    State:  "+rs.getString("state"));
                    area.append("\n    Email:  "+rs.getString("email"));
                    area.append("\n    Phone:  "+rs.getString("phone"));
                }
                
                area.append("\n\n----------------------------------\n\n");
                
                rs=db.s.executeQuery("select * from meter_info where meter_no='"+meternum+"'");
                
                if(rs.next()){
                    area.append("\n    Meter Location:  "+rs.getString("meter_loc"));
                    area.append("\n    Meter Type:  "+rs.getString("meter_type"));
                    area.append("\n    Phase Code:  "+rs.getString("phase_code"));
                    area.append("\n    Bill Type:  "+rs.getString("bill_type"));
                    area.append("\n    Days:  "+rs.getString("days"));
                }
                
                area.append("\n\n----------------------------------\n\n");
                
                rs=db.s.executeQuery("select * from tax");
                
                if(rs.next()){
                    area.append("\n    Cost Per Unit:  "+rs.getString("cost_per_unit"));
                    area.append("\n    Meter Rent:  "+rs.getString("meter_rent"));
                    area.append("\n    Service Charge:  "+rs.getString("service_charge"));
                    area.append("\n    Service Tax:  "+rs.getString("service_tax"));
                    area.append("\n    Swacch Bharat Cess:  "+rs.getString("swacch_bharat_cess"));
                    area.append("\n    Fixed:  "+rs.getString("fixed_tax"));
                }
                
                area.append("\n\n----------------------------------\n\n");
                
                rs=db.s.executeQuery("select * from bill where meter_no='"+meternum+"' and month='"+cmonth+"'");
                
                if(rs.next()){
                    area.append("\n    Current Month:  "+rs.getString("month"));
                    area.append("\n    Units Consumed:  "+rs.getString("units"));
                    area.append("\n    Total Charges:  "+rs.getString("totalbill"));
                    area.append("\n----------------------------------\n");
                    area.append("\n    Total Payable:  "+rs.getString("totalbill"));
                    
                }
                
                
                
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main (String[] args){
        new GenerateBill("");
    }
}
