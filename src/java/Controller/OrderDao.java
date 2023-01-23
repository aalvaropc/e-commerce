/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.PreparedStatement;

import Model.*;

public class OrderDao {
	
	private Connection con;

	private String query;
        private Statement st;
        private ResultSet rs;
        private PreparedStatement pst;
    
    

	public OrderDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean insertOrder(Order model) {
        boolean result = false;
        try {
            query = "insert into pedidos values ('" + model.getOrderId() + "', '" + model.getQunatity()+ "','"
                    + model.getDate()+ "', '" + model.getEstadoP()+ "'," + model.getUid()+ ")";
            st = this.con.createStatement();
            st.execute(query);
            result = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
	

    public List<Order> userOrders(int id) {
        List<Order> list = new ArrayList<>();
        try {
            query = "select * from Pedidos where idClientes=? ";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                ProductDao productDao = new ProductDao(this.con);
                int pId = rs.getInt("IdPedidos");
                Product product = productDao.getSingleProduct(pId);
                order.setOrderId(rs.getInt("Clientes"));
                order.setId(pId);
                order.setName(product.getName());
                order.setCategory(product.getCategory());
                order.setPrice(product.getPrice()*rs.getInt("Cantidad"));
                order.setQunatity(rs.getInt("Cantidad"));
                order.setDate(rs.getString("Fechapedido"));
                order.setEstadoP(rs.getString("EstadoPedido"));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void cancelOrder(int id) {
        //boolean result = false;
        try {
            query = "delete from Pedidos where idPedidos=?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            pst.execute();
            //result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
        //return result;
    }
}
