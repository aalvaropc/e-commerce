/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.*;
import java.util.*;

import Model.Cart;
import Model.Product;

public class ProductDao {
	private Connection con;

	private String query;
    private PreparedStatement pst;
    private ResultSet rs;
    

	public ProductDao(Connection con) {
		
		this.con = con;
	}
	
	
	public List<Product> getAllProducts() {
        List<Product> book = new ArrayList<>();
        try {

            query = "select * from Productos";
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
            	Product row = new Product();
                row.setId(rs.getInt("idProducto"));
                row.setName(rs.getString("Nombre"));
                row.setDescription(rs.getString("Descripcion"));
                row.setCategory(rs.getString("Categorias"));
                row.setPrice(rs.getDouble("Precio"));
                row.setExist(rs.getInt("Exist"));
                row.setMarcas(rs.getString("Marcas"));
                row.setOrders(rs.getString("Pedidos"));
                row.setImage(rs.getString("image"));
 
                book.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return book;
    }
	
	
	 public Product getSingleProduct(int id) {
		 Product row = null;
	        try {
	            query = "select * from Productos where idProducto=? ";

	            pst = this.con.prepareStatement(query);
	            pst.setInt(1, id);
	            ResultSet rs = pst.executeQuery();

	            while (rs.next()) {
	            	row = new Product();
	                row.setId(rs.getInt("idProducto"));
                        row.setName(rs.getString("Nombre"));
                        row.setDescription(rs.getString("Descripcion"));
                        row.setCategory(rs.getString("Categorias"));
                        row.setPrice(rs.getDouble("Precio"));
                        row.setExist(rs.getInt("Exist"));
                        row.setMarcas(rs.getString("Marcas"));
                        row.setOrders(rs.getString("Pedidos"));
                        row.setImage(rs.getString("image"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }

	        return row;
	    }
	
	public double getTotalCartPrice(ArrayList<Cart> cartList) {
        double sum = 0;
        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    query = "select Precio from Productos where idProducto=?";
                    pst = this.con.prepareStatement(query);
                    pst.setInt(1, item.getId());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        sum+=rs.getDouble("Precio")*item.getQuantity();
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return sum;
    }

    
    public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
        List<Cart> book = new ArrayList<>();
        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    query = "select * from Productos where idProducto=?";
                    pst = this.con.prepareStatement(query);
                    pst.setInt(1, item.getId());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        Cart row = new Cart();
                        row.setId(rs.getInt("idProducto"));
                        row.setName(rs.getString("Nombre"));
                        row.setDescription(rs.getString("Descripcion"));
                        row.setCategory(rs.getString("Categorias"));
                        row.setPrice(rs.getDouble("Precio"));
                        row.setExist(rs.getInt("Exist"));
                        row.setMarcas(rs.getString("Marcas"));
                        row.setOrders(rs.getString("Pedidos"));
                        row.setImage(rs.getString("image"));
                        row.setQuantity(item.getQuantity());
                        book.add(row);
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return book;
    }
}
