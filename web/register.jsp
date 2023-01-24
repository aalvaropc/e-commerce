<%@page import="Model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%
	User auth = (User) request.getSession().getAttribute("auth");
	if (auth != null) {
		response.sendRedirect("index.jsp");
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
<title>Amazon - Registro</title>
</head>
<body>
    <!--<!-- <%@include file="/includes/navbar.jsp"%> -->

	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">Registrarse</div>
			<div class="card-body">
				<form action="user-register" method="post">
					<div class="form-group">
						<label>Nombre</label> 
						<input type="text" name="name" class="form-control" placeholder="Ingresa tu nombre">
					</div>
					<div class="form-group">
						<label>Email</label> 
						<input type="email" name="email" class="form-control" placeholder="Ingresa tu email">
					</div>
					<div class="form-group">
						<label>Contraseña</label> 
						<input type="password" name="password" class="form-control" placeholder="Ingresa tu contraseña">
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-warning">Registrarse</button>
						<a href="login.jsp" class="btn btn-secondary">Ya tengo cuenta</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>