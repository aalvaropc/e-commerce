package Controller;

import java.sql.*;
import Model.*;

public class UserDao {
	
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public UserDao(Connection con) {
            this.con = con;
    }
	
    public User userLogin(String email, String password) {
            User user = null;
        try {
            query = "select * from usuario where email=? and Contraseña=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);
            rs = pst.executeQuery();
            
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("idUsuario"));
                user.setName(rs.getString("Nombre"));
                user.setEmail(rs.getString("email"));
            }
            
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        
        return user;
    }
}
