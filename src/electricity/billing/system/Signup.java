package electricity.billing.system;
import javax.swing.*;
//import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener
{
    
    JButton create, back;
    Choice accountType;
    JTextField username, meter, name, password;
    
    
    Signup()
    {
        super("Signup Page");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        Color signupcolor=new Color(85,154,213);
        
        /*JPanel panel=new JPanel();
        panel.setBounds(5, 5, 615, 250);
        panel.setBorder(new TitledBorder(new LineBorder(signupcolor), "Create Account", TitledBorder.CENTER, TitledBorder.TOP, null, signupcolor));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        add(panel);*/
        
        //Labels
        JLabel lblcreate=new JLabel("Create Account as");
        lblcreate.setBounds(250, 30, 150, 25);
        lblcreate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblcreate);
        
        accountType=new Choice();
        accountType.add("Admin");
        accountType.add("Customer");
        accountType.setBounds(400, 32, 100, 25);
        
        add(accountType);
        
        JLabel lblmeter=new JLabel("Meter Number");
        lblmeter.setBounds(250, 70, 150, 25);
        lblmeter.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblmeter);
        
        meter=new JTextField();
        meter.setBounds(400, 70, 150, 25);
        add(meter);
        
        lblmeter.setVisible(false);
        meter.setVisible(false);
        
        JLabel lblusername=new JLabel("Username");
        lblusername.setBounds(250, 110, 150, 25);
        lblusername.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblusername);
        
        username=new JTextField();
        username.setBounds(400, 110, 150, 25);
        add(username);
      
        
        JLabel lblname=new JLabel("Name");
        lblname.setBounds(250, 150, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblname);
        
        name=new JTextField();
        name.setBounds(400, 150, 150, 25);
        add(name);
        
        meter.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe){}
            
            @Override
            public void focusLost(FocusEvent fe) {
            
                try
            {
                Database db=new Database();
                ResultSet rs=db.s.executeQuery("select * from login where meter_no= '"+meter.getText()+"'");
                while(rs.next()){
                    name.setText(rs.getString("name"));
                }
            } 
            catch(Exception e) //If query not executed successfully
            {
                e.printStackTrace();
            }
            }
        });
        
        JLabel lblpassword=new JLabel("Password");
        lblpassword.setBounds(250, 190, 150, 25);
        lblpassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblpassword);
        
        password=new JTextField();
        password.setBounds(400, 190, 150, 25);
        add(password);
        
        accountType.addItemListener(new ItemListener(){
           @Override
            public void itemStateChanged(ItemEvent ie){
                String user=accountType.getSelectedItem();
                if (user.equals("Customer")){
                    lblmeter.setVisible(true);
                    meter.setVisible(true);
                    name.setEditable(false);
                }
                else{
                    lblmeter.setVisible(false);
                    meter.setVisible(false);
                    name.setEditable(true);
                }
            }
            
        });
        
        
        //Buttons
        create=new JButton("Create");
        create.setBackground(signupcolor);
        create.setForeground(Color.WHITE);
        create.setBounds(250, 250, 125, 25);
        create.addActionListener(this);
        add(create);
        
        back=new JButton("Back");
        back.setBackground(Color.GRAY);
        back.setForeground(Color.WHITE);
        back.setBounds(425, 250, 125, 25);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/login2.png"));
        Image i2=i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(25,25,210,200);
        add(image);
        
        setSize(640,350);
        setLocation(350,175);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == create)
        {
            String acctype=accountType.getSelectedItem();
            String accuser=username.getText();
            String accname=name.getText();
            String accpass=password.getText();
            String accmeter=meter.getText();
            
            try
            {
                Database db=new Database();
                
                String query=null;
                if(acctype.equals("Admin")){        
                    query="insert into login values('"+accmeter+"', '"+accuser+"', '"+accname+"', '"+accpass+"', '"+acctype+"')";
                }
                else{
                    query="update login set username='"+accuser+"', password= '"+accpass+"', acc_type='"+acctype+"' where meter_no='"+accmeter+"' ";
                }
                db.s.executeUpdate(query);
                
                //If query executed successfully
                JOptionPane.showMessageDialog(null, "Account Create Successfully!");
                
                setVisible(false);
                
                new Login();
            } 
            catch(Exception e) //If query not executed successfully
            {
                e.printStackTrace();
            }
        }
        else if (ae.getSource() == back)
        {
            setVisible(false);
            new Login();
        }
  
    }
    
    public static void main (String[] args)
    {
        new Signup();
    }
}
