<%@page import="Model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%
	User auth = (User) request.getSession().getAttribute("auth");
        
	if (auth != null) { 
    String role = auth.getRole();
    if(role.equals("admin")){
        response.sendRedirect("panel-admin.jsp");
        return;
    }
    response.sendRedirect("index.jsp");
    return;
}
	ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
	if (cart_list != null) {
		request.setAttribute("cart_list", cart_list);
	}
	%>
<!DOCTYPE html>
<html>
    <head>
    <%@include file="/includes/head.jsp"%>
    <title>Amazon</title>
    </head>
    <body>
        <!--<!-- <%@include file="/includes/navbar.jsp"%> -->

            <div class="container">
                    <div class="card w-50 mx-auto my-5">
                            <div class="card-header text-center">Iniciar sesión</div>
                            <div class="card-body">
                                    <form action="user-login" method="post">
                                            <div class="form-group">
                                                    <label>Email</label> 
                                                    <input type="email" name="login-email" class="form-control" placeholder="Ingresa email">
                                            </div>
                                            <div class="form-group">
                                                    <label>Contraseña</label> 
                                                    <input type="password" name="login-password" class="form-control" placeholder="Ingresa contraseña">
                                            </div>
                                            <div class="text-center">
                                                    <button type="submit" class="btn btn-warning">Iniciar</button>
                                                    <a href="register.jsp" class="btn btn-secondary">Registrarse</a>
                                            </div>
                                    </form>
                            </div>
                    </div>
            </div>
    </body>
</html>