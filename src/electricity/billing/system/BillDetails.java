package electricity.billing.system;
import java.awt.*;
import java.sql.ResultSet;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class BillDetails extends JFrame implements ActionListener{
    
    
    JTable table;
    JButton print;
    BillDetails(String meternum){
        
        setSize(1000, 650);
        setLocation(150, 25);
        
        table=new JTable();
        
        try{
            Database db=new Database();
            String query="select * from bill where meter_no='"+meternum+"'";
            ResultSet rs=db.s.executeQuery(query);
            
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
        new BillDetails("");
    }
}
