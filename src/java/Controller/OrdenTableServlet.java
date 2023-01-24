
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

@WebServlet("/OrdenTableServlet")
public class OrdenTableServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
Connection con = null;
Statement stmt = null;
public OrdenTableServlet() {
    super();
}
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try{
            con = DbCon.getConnection();
    }catch(ClassNotFoundException e){
            
    } catch (SQLException ex) {
        Logger.getLogger(OrdenTableServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT o.o_id, u.name, p.name, p.category, o.o_date, o.o_quantity FROM orders o JOIN users u ON o.u_id = u.id JOIN products p ON o.p_id = p.id");
        List<OrdenTable> ordenList = new ArrayList<>();
        while (rs.next()) {
            int id= rs.getInt("o.o_id");
            String client = rs.getString("u.name");
            String product = rs.getString("p.name");
            String category = rs.getString("p.category");
            String date = rs.getString("o.o_date");
            int amount = rs.getInt("o.o_quantity");
            
            ordenList.add(new OrdenTable(id, client, product, category, date, amount));
        }
        Gson gson = new Gson();
        String json = gson.toJson(ordenList);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        } catch (SQLException ex) {
            Logger.getLogger(OrdenTableServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}