package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project extends JFrame implements ActionListener{
    
    String type, meter;
    
    Project(String type, String meter)
    {
        this.type=type;
        this.meter=meter;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/project1.png"));
        Image i2=i1.getImage().getScaledInstance(1300, 850, Image.SCALE_DEFAULT);
        
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        add(image);
        
        JMenuBar mb=new JMenuBar();
        mb.setBackground(Color.WHITE);
        
        
        JMenu admin=new JMenu("Administration");
        admin.setFont(new Font("Tahoma", Font.PLAIN, 14));
        admin.setForeground(Color.RED);
        
        
        JMenuItem newcustomer=new JMenuItem("New Customer");
        newcustomer.setFont(new Font("Tahoma", Font.PLAIN, 12));
        newcustomer.setBackground(Color.WHITE);
        newcustomer.addActionListener(this);
        
        JMenuItem customerdetails=new JMenuItem("Customer Details");
        customerdetails.setFont(new Font("Tahoma", Font.PLAIN, 12));
        customerdetails.setBackground(Color.WHITE);
        customerdetails.addActionListener(this);
        
        JMenuItem depositdetails=new JMenuItem("Deposit Details");
        depositdetails.setFont(new Font("Tahoma", Font.PLAIN, 12));
        depositdetails.setBackground(Color.WHITE);
        depositdetails.addActionListener(this);
        
        JMenuItem calculatebill=new JMenuItem("Calculate Bill");
        calculatebill.setFont(new Font("Tahoma", Font.PLAIN, 12));
        calculatebill.setBackground(Color.WHITE);
        calculatebill.addActionListener(this);
   
        setJMenuBar(mb);
        admin.add(newcustomer);
        admin.add(customerdetails);
        admin.add(depositdetails);
        admin.add(calculatebill);
        
        
        JMenu user=new JMenu("User Information");
        user.setFont(new Font("Tahoma", Font.PLAIN, 14));
        user.setForeground(Color.RED);
        
        JMenuItem updateinfo=new JMenuItem("Update Information");
        updateinfo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        updateinfo.setBackground(Color.WHITE);
        updateinfo.addActionListener(this);
        
        JMenuItem viewinfo=new JMenuItem("View Information");
        viewinfo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        viewinfo.setBackground(Color.WHITE);
        viewinfo.addActionListener(this);
        
        user.add(updateinfo);
        user.add(viewinfo);
        
        
        JMenu bill=new JMenu("Electricity Bill");
        bill.setFont(new Font("Tahoma", Font.PLAIN, 14));
        bill.setForeground(Color.RED);
        
        JMenuItem paybill=new JMenuItem("Pay Bill");
        paybill.setFont(new Font("Tahoma", Font.PLAIN, 12));
        paybill.setBackground(Color.WHITE);
        paybill.addActionListener(this);
        
        JMenuItem billdetails=new JMenuItem("Bill Details");
        billdetails.setFont(new Font("Tahoma", Font.PLAIN, 12));
        billdetails.setBackground(Color.WHITE);
        billdetails.addActionListener(this);
       
        JMenuItem generatebill=new JMenuItem("Generate Bill");
        generatebill.setFont(new Font("Tahoma", Font.PLAIN, 12));
        generatebill.setBackground(Color.WHITE);
        generatebill.addActionListener(this);
        
        bill.add(paybill);
        bill.add(billdetails);
        bill.add(generatebill);
        
        
        JMenu exit=new JMenu("Exit");
        exit.setFont(new Font("Tahoma", Font.PLAIN, 14));
        exit.setForeground(Color.BLACK);
        
        JMenuItem logout=new JMenuItem("Log Out");
        logout.setFont(new Font("Tahoma", Font.PLAIN, 12));
        logout.setBackground(Color.WHITE);
        logout.addActionListener(this);
        
        
        exit.add(logout);
        
        if(type.equals("Admin")){
            mb.add(admin);
        }
        
        else{
            
            mb.add(user);
            mb.add(bill);
        }

        mb.add(exit);
        
        
        mb.setLayout(new GridBagLayout());
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        String msg=ae.getActionCommand();
        if (msg.equals("New Customer")){
            new NewCustomer();
        }
        else if (msg.equals("Customer Details")){
            new CustomerDetails();
        }
        else if (msg.equals("Deposit Details")){
            new DepositDetails();
        }
        else if (msg.equals("Calculate Bill")){
            new CalculateBill();
        }
        else if (msg.equals("View Information")){
            
            new ViewInformation(meter);
        } 
        else if (msg.equals("Update Information")){
            
            new UpdateInformation(meter);
        } 
        else if (msg.equals("Bill Details")){
            
            new BillDetails(meter);
        } 
        else if (msg.equals("Pay Bill")){
            
            new PayBill(meter);
        } 
        else if (msg.equals("Generate Bill")){
            
            new GenerateBill(meter);
        } 
        else if (msg.equals("Log Out")){
            setVisible(false);
            new Login();
        } 
    }
    
    public static void main(String []args)
    {
        new Project("", "");
    }
}
