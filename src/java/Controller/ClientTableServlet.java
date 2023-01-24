package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import java.util.logging.Logger;
import com.mysql.cj.jdbc.Driver;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Model.DbCon;
import java.util.logging.Level;

@WebServlet("/ClientTableServlet")
public class ClientTableServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
Connection con = null;
Statement stmt = null;
public ClientTableServlet() {
    super();
}

protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try{
            con = DbCon.getConnection();
    }catch(ClassNotFoundException e){
            
    } catch (SQLException ex) {
        Logger.getLogger(ClientTableServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
    

    try {
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id, name, email, password FROM users where role='cliente'");
        List<ClientTable> clientList = new ArrayList<>();
        while (rs.next()) {
            int id= rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String password = rs.getString("password");
            
            clientList.add(new ClientTable(id, name, email, password));
        }
        Gson gson = new Gson();
        String json = gson.toJson(clientList);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        } catch (SQLException ex) {
            Logger.getLogger(ClientTableServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}