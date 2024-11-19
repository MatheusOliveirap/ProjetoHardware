
package model.DAO;
  import java.sql.*;
import javax.swing.JOptionPane;
//teste tste 

public class Conexao {
    
    public static Connection conector(){

        java.sql.Connection conexao = null;
        
        String driver = "com.mysql.jdbc.Driver";
        
        String url = "jdbc:mysql://localhost:3306/hardware";
        String user = "root";
        String password = "root";
        
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Conexão" + e);
            return null;
        }
    }
}

