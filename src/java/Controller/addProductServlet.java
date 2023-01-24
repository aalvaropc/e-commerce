package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.DbCon;
import Model.ProductAdd;
import Controller.ProductDao;

public class addProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("productName");
        String category = request.getParameter("productCategory");
        String priceString = request.getParameter("productPrice");
        double price = 0.0;
        if (priceString != null && !priceString.trim().isEmpty()) {
            price = Double.parseDouble(priceString);
        }
        String image = request.getParameter("productImage"); 
        ProductAdd product = new ProductAdd(name, category, price, image);
        Connection connection;
        
        try {
            connection = DbCon.getConnection();
            ProductDao productDao = new ProductDao(connection);
            productDao.addProduct(product);
            connection.close();
            request.setAttribute("successMessage", "Producto agregado exitosamente");
            request.getRequestDispatcher("panel-admin.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Ocurrió un error al agregar el producto a la base de datos, inténtelo de nuevo más tarde");
            request.getRequestDispatcher("panel-admin.jsp").forward(request, response);
        }
    }
}
