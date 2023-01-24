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
            query = "select id, name, email, role from users where email=? and password=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);

            rs = pst.executeQuery();
            
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
            }
            
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        
        return user;
    }
}
