<%@page import="Model.DbCon"%>
<%@page import="Controller.ProductDao"%>
<%@page import="Controller.addProductServlet"%>
<%@page import="Model.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
    request.setAttribute("person", auth);
}
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProduct = null;
if (cart_list != null) {
	ProductDao pDao = new ProductDao(DbCon.getConnection());
	cartProduct = pDao.getCartProducts(cart_list);
	double total = pDao.getTotalCartPrice(cart_list);
	request.setAttribute("total", total);
	request.setAttribute("cart_list", cart_list);
}
%>
<!doctype html>
<html lang="en">
  <head>
    <title>Panel-admin</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">	
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/style.css"> 
  </head>
    <body>	
        <div class="wrapper d-flex align-items-stretch">
            <nav id="sidebar">
                <div class="custom-menu">
                    <button type="button" id="sidebarCollapse" class="btn btn-primary"></button>
                </div>
                <div class="img bg-wrap text-center py-4" style="background-image: url(images/bg_1.jpg);">
                    <div class="user-logo">
                        <div class="img" style="background-image: url(images/admin.png);"></div>
                        <h3><%=auth.getName()%></h3>
                    </div>
                </div>
                <ul class="list-unstyled components mb-5">
                  <li class="active">
                    <a href="#" id="homeOption"><span class="fa fa-archive mr-3"></span> Productos</a>
                  </li>
                  <li>
                      <a href="#" id="clienteOption"><span class="fa fa-vcard mr-3 notif"><small class="d-flex align-items-center justify-content-center">5</small></span> Clientes</a>
                  </li>
                  <li>
                    <a href="#" id="ordenOption"><span class="fa fa-money mr-3"></span> Ordenes</a>
                  </li>
                  <li>
                    <a href="#" id="AddProductOption"><span class="fa fa-gift mr-3"></span> Agregar Producto</a>
                  </li>
                  <li>
                      <a href="logout-servlet"><span class="fa fa-sign-out mr-3"></span> Salir</a>
                  </li>
                </ul>
            </nav>
            <div id="content" class="p-4 p-md-5 pt-5" >
            <h2 style="text-align: center; font-size: 3em; font-weight: bold; margin-bottom: 50px;">BIENVENIDO <%=auth.getName()%></h2>
            <div id="tableContainer">
            </div>
           </div>
	</div>
        <script>
            document.getElementById("homeOption").addEventListener("click", function(){
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function() {
                    if (this.readyState === 4 && this.status === 200) {
                       //aqui se procesa la respuesta del servlet
                       var products = JSON.parse(xhttp.responseText);
                       //aqui se construye la tabla
                       var table = "<table class='table'><thead><tr><th>ID</th><th>Nombre</th><th>Categoria</th><th>Precio</th></tr></thead><tbody>";
                        for (var i = 0; i < products.length; i++) {
                            table += "<tr><td>" + products[i].id + "</td><td>" + products[i].name + "</td><td>" + products[i].category + "</td><td>" + products[i].price + "</td></tr>";
                        }
                        table += "</tbody></table>";
                        document.getElementById("content").innerHTML = table;
                    }
                };
                xhttp.open("GET", "ProductTableServlet", true);
                xhttp.send();
            });
            
            document.getElementById("clienteOption").addEventListener("click", function(){
                 var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function() {
                    if (this.readyState === 4 && this.status === 200) {
                       var clients = JSON.parse(xhttp.responseText);
                       var table = "<table class='table'><thead><tr><th>ID</th><th>Nombre</th><th>E-mail</th><th>Contraseña</th></tr></thead><tbody>";
                        for (var i = 0; i < clients.length; i++) {
                            table += "<tr><td>" + clients[i].id + "</td><td>" + clients[i].name + "</td><td>" + clients[i].email + "</td><td>" + clients[i].password + "</td></tr>";
                        }
                        table += "</tbody></table>";
                        document.getElementById("content").innerHTML = table;
                    }
                };
                xhttp.open("GET", "ClientTableServlet", true);
                xhttp.send();
            });

            document.getElementById("AddProductOption").addEventListener("click", function(){
                var form = "<form action='addProductServlet' method='post' enctype='multipart/form-data'>";
                //var form = "<form>";
                form += "<div class='form-group'><label for='productName'>Nombre del Producto:</label>";
                form += "<input type='text' class='form-control' id='productName' name='productName' placeholder='Ingrese el nombre del producto'></div>";
                form += "<div class='form-group'><label for='productPrice'>Precio:</label>";
                form += "<input type='text' class='form-control' id='productPrice' name='productPrice' placeholder='Ingrese el precio del producto'></div>";
                form += "<div class='form-group'><label for='productCategory'>Categoria:</label>";
                form += "<input type='text' class='form-control' id='productCategory' name='productCategory' placeholder='Ingrese la categoria del producto'></div>";
                form += "<div class='form-group'><label for='productImage'>Imagen:</label>";
                form += "<input type='file' class='form-control-file' id='productImage' name='productImage'></div>";
                form += "<button type='submit' class='btn btn-primary'>Agregar Producto</button>";
                form += "</form>";
                document.getElementById("content").innerHTML = form;
            });
  
            document.getElementById("ordenOption").addEventListener("click", function(){
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function() {
                    if (this.readyState === 4 && this.status === 200) {
                        var orden = JSON.parse(xhttp.responseText);
                        var table = "<table class='table'><thead><tr><th>ID</th><th>Cliente</th><th>Producto</th><th>Categoria</th><th>Fecha</th><th>Cantidad</th><th></th></tr></thead><tbody>";
                        for (var i = 0; i < orden.length; i++) {
                            table += "<tr><td>" + orden[i].id + "</td><td>" + orden[i].client + "</td><td>" + orden[i].product + "</td><td>" + orden[i].category + "</td><td>" + orden[i].date + "</td><td>" + orden[i].amount + "</td></tr>";
                        }
                        table += "</tbody></table>";
                        document.getElementById("content").innerHTML = table;
                    }
                };
                xhttp.open("GET", "OrdenTableServlet", true);
                xhttp.send();
            });        
        </script>
        <script src="js/jquery.min.js"></script>
        <script src="js/popper.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>