package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MeterInfo extends JFrame implements ActionListener{
    
    JTextField name, address, state, city, email, phone;
    JButton next;

    Choice meterloc, mtype, mcode, btype;
    String meter_no;
    MeterInfo(String meter_no){
        this.meter_no=meter_no;
        JPanel p=new JPanel();
        p.setBackground(Color.WHITE);
        p.setLayout(null);
        add(p);
        Color logincolor=new Color(52,181,86);
        
        JLabel heading=new JLabel("Meter Information");
        heading.setForeground(Color.RED);
        heading.setBounds(220, 40, 250, 20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        p.add(heading);
        
        JLabel lblmeter=new JLabel("Meter Number");
        lblmeter.setBounds(130, 120, 100, 20);
        lblmeter.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblmeter);
        
        JLabel meter=new JLabel(meter_no);
        meter.setBounds(270, 120, 100, 20);
        meter.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(meter);
        
        JLabel lbllocation=new JLabel("Meter Location");
        lbllocation.setBounds(130, 160, 100, 20);
        lbllocation.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lbllocation);
        
        meterloc=new Choice();
        meterloc.add("Inside");
        meterloc.add("Outside");
        meterloc.setBounds(270, 160, 100, 25);
        p.add(meterloc);
        
        JLabel lbltype=new JLabel("Meter Type");
        lbltype.setBounds(130, 200, 100, 20);
        lbltype.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lbltype);
        
        mtype=new Choice();
        mtype.add("Electric Meter");
        mtype.add("Solar Meter");
        mtype.add("Smart Meter");
        mtype.setBounds(270, 200, 100, 25);
        p.add(mtype);
        
        JLabel lblcode=new JLabel("Phase Code");
        lblcode.setBounds(130, 240, 100, 20);
        lblcode.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblcode);
        
        mcode=new Choice();
        mcode.add("011");
        mcode.add("022");
        mcode.add("033");
        mcode.add("044");
        mcode.add("055");
        mcode.add("066");
        mcode.add("077");
        mcode.add("088");
        mcode.add("099");
        mcode.setBounds(270, 240, 100, 25);
        p.add(mcode);
        
        JLabel lblbill=new JLabel("Bill Type");
        lblbill.setBounds(130, 280, 100, 20);
        lblbill.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblbill);
        
        btype=new Choice();
        btype.add("Normal");
        btype.add("Commercial");
        btype.setBounds(270, 280, 100, 25);
        p.add(btype);
        
        JLabel lbldays=new JLabel("30 days");
        lbldays.setBounds(130, 320, 100, 20);
        lbldays.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lbldays);
        
        JLabel days=new JLabel("30 days");
        days.setBounds(270, 320, 100, 20);
        days.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(days);
        
        JLabel lblnote=new JLabel("Note");
        lblnote.setBounds(130, 360, 100, 20);
        lblnote.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(lblnote);
        
        JLabel note=new JLabel("By default Bill is calculated for 30 days only");
        note.setBounds(270, 360, 500, 20);
        note.setFont(new Font("Tahoma", Font.PLAIN, 14));
        p.add(note);
        
        
        //Buttons
        next=new JButton("Submit");
        next.setBounds(260, 420, 100, 30);
        next.setBackground(logincolor);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);
        
        
        
        setLayout(new BorderLayout());
        add(p, "Center");
        setSize(620,550);
        setLocation(330,75);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==next){
            String meternum=meter_no;
            String location=meterloc.getSelectedItem();
            String typem=mtype.getSelectedItem();
            String code=mcode.getSelectedItem();
            String typeb=btype.getSelectedItem();
            String days="30";
            
            String query1="insert into meter_info values('"+meternum+"', '"+location+"', '"+typem+"', '"+code+"', '"+typeb+"', '"+days+"')";
            
            
            try{
                Database db=new Database();
                db.s.executeUpdate(query1);
                
                JOptionPane.showMessageDialog(null, "Meter Information Added Successfully");
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
        new MeterInfo("");
    }
    
}
