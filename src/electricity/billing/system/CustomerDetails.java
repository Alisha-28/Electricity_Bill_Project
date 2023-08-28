package electricity.billing.system;
import java.awt.*;
import java.sql.ResultSet;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class CustomerDetails extends JFrame implements ActionListener{
    
    
    JTable table;
    JButton print;
    CustomerDetails(){
        
        setSize(1200, 650);
        setLocation(50, 25);
        
        table=new JTable();
        
        try{
            Database db=new Database();
            ResultSet rs=db.s.executeQuery("select * from customer");
            
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
            
            
        }catch (Exception e){
            e.printStackTrace();
        }
        
        JScrollPane sp=new JScrollPane(table);
        add(sp);
        
        print= new JButton("Print");
        print.addActionListener(this);
        add(print, "South");
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        try
            {
                table.print();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main (String[] args){
        new CustomerDetails();
    }
}
