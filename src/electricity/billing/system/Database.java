package electricity.billing.system;
import java.sql.*;

public class Database {
    
   Connection c;
   Statement s;
   Database()
   {
       try
       {
            c= DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "root", "!3ilksj3!");
            s= c.createStatement();
        }
       catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
