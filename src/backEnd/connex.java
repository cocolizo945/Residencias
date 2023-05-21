package backEnd;


import java.sql.*;


public class connex {
    Connection con;

    public Connection conecc(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/residencias","root","1234");
            System.out.println("Successs");

        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return con;
    }





}
