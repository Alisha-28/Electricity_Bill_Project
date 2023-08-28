package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    
    JButton login, signup, cancel;
    JTextField username, password;
    Choice loginas;
    
    Login()
    {
        super("Login Page");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        Color logincolor=new Color(52,181,86);
        Color cancelcolor=new Color(212,32,32);
        Color signupcolor=new Color(85,154,213);
        
        //Labels
        JLabel lblusername=new JLabel("Username");
        lblusername.setBounds(300, 30, 100, 25);
        lblusername.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblusername);
        
        username=new JTextField();
        username.setBounds(400, 30, 150, 25);
        add(username);
        
        
        JLabel lblpassword=new JLabel("Password");
        lblpassword.setBounds(300, 70, 100, 25);
        lblpassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblpassword);
        
        password=new JTextField();
        password.setBounds(400, 70, 150, 25);
        add(password);
        
        
        JLabel lblloginas=new JLabel("Logging in as");
        lblloginas.setBounds(300, 110, 100, 25);
        lblloginas.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblloginas);
        
        loginas=new Choice();
        loginas.add("Admin");
        loginas.add("Customer");
        loginas.setBounds(400, 113, 150, 25);
        add(loginas);
        
        //Buttons
        
        login=new JButton("Login");
        login.setBackground(logincolor);
        login.setForeground(Color.WHITE);
        login.setBounds(300, 170, 100, 25);
        login.addActionListener(this);
        add(login);
        
        signup=new JButton("Signup");
        signup.setBackground(signupcolor);
        signup.setForeground(Color.WHITE);
        signup.setBounds(450, 170, 100, 25);
        signup.addActionListener(this);
        add(signup);
        
        cancel=new JButton("Cancel");
        cancel.setBackground(cancelcolor);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(375, 210, 100, 25);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/login1.png"));
        Image i2=i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(45,15,210,200);
        add(image);
        
        setSize(640,300);
        setLocation(350,200);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == login)
        {
            String cuser=username.getText();
            String cpass=password.getText();
            String ctype=loginas.getSelectedItem();
            
            try {
                Database db=new Database();
                String query="select * from login where username='"+cuser+"' and password= '"+cpass+"' and acc_type='"+ctype+"'";
                
                ResultSet rs=db.s.executeQuery(query);
                
                if (rs.next()){
                    String meter=rs.getString("meter_no");
                    
                    setVisible(false);
                    new Project(ctype, meter);
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                    username.setText("");
                    password.setText("");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else if (ae.getSource() == signup)
        {
            setVisible(false);
            new Signup();
        }
        else if (ae.getSource() == cancel)
        {
            setVisible(false);
        }
    }
    
    public static void main (String[] args)
    {
        new Login();
    }
    
}
