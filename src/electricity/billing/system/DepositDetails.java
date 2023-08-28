package electricity.billing.system;
import java.awt.*;
import java.sql.ResultSet;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class DepositDetails extends JFrame implements ActionListener{
    
    Choice meternumber, month;
    JTable table;
    JButton search,print;
    DepositDetails(){
        
        setSize(700, 600);
        setLocation(300, 50);
        
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        JLabel lblmeter=new JLabel("Search by Meter Number");
        lblmeter.setBounds(30, 20, 160, 20);
        lblmeter.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(lblmeter);
        
        meternumber=new Choice();
        meternumber.setBounds(190, 20, 150, 20);
        add(meternumber);
        
        try{
            Database db=new Database();
            ResultSet rs=db.s.executeQuery("select * from customer");
            
            while(rs.next()){
                meternumber.add(rs.getString("meter_no"));
                
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
        
        JLabel lblmonth=new JLabel("Search by Month");
        lblmonth.setBounds(365, 20, 115, 20);
        lblmonth.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(lblmonth);
        
        month=new Choice();
        month.setBounds(480, 20, 150, 20);
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
        
        table=new JTable();
        
        try{
            Database db=new Database();
            ResultSet rs=db.s.executeQuery("select * from bill");
            
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
            
            
        }catch (Exception e){
            e.printStackTrace();
        }
        
        JScrollPane sp=new JScrollPane(table);
        sp.setBounds(0,100,700,600);
        add(sp);
        
        search= new JButton("Search");
        search.setBounds(30,55,80,20);
        search.addActionListener(this);
        add(search);
        
        print= new JButton("Print");
        print.setBounds(140,55,80,20);
        print.addActionListener(this);
        add(print);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== search){
            String query="select * from bill where meter_no='"+meternumber.getSelectedItem()+"' and month='"+month.getSelectedItem()+"' ";
            
            try
            {
                Database db =new Database();
                ResultSet rs=db.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
                
        }catch (Exception e){
            e.printStackTrace();
        }
            
        }
        else{
            try
            {
                table.print();
        }catch (Exception e){
            e.printStackTrace();
        }
        }
    }
    
    public static void main (String[] args){
        new DepositDetails();
    }
}
