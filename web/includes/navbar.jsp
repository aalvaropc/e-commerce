<nav class="navbar navbar-expand-lg " style="background-color: #232F3E; height: 120px">
	<div class="container">
            <a class="navbar-brand" href="index.jsp"><img src="product-image/amazon_logo2.jpg" alt="Amazon logo" style="width: 120px"></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
	
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="index.jsp" style="color: white">Inicio</a></li>
				<li class="nav-item"><a class="nav-link" href="cart.jsp" style="color: white">Carrito <span class="badge badge-danger">${cart_list.size()}</span> </a></li>
				<%
				if (auth != null) {
				%>
				<li class="nav-item"><a class="nav-link" href="orders.jsp" style="color: white">Ordenes</a></li>
				<li class="nav-item"><a class="nav-link" href="log-out" style="color: white">Salir</a></li>
				<%
				} else {
				%>
				<li class="nav-item"><a class="nav-link" href="login.jsp" style="color: white">Iniciar Sesión</a></li>
				<%
				}
				%>
			</ul>
		</div>
	</div>
</nav>