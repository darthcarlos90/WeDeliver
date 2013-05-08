<!DOCTYPE html>
<html>

<jsp:include page="elementos/head.jsp" />
<script type="text/javascript" src="validacion.js"></script>
<body>
	<jsp:include page="elementos/menu.jsp" />
	<%
		try {
			String userName = (String) session.getAttribute("userName");
			String sesion = (String)session.getAttribute("sesion");
			if (sesion.equals("Activa")) {
	%>

	<div id="content">

		<!-- Aqui empieza el content -->

		<h1>
			Bienvenido!
			<%=userName%></h1>
		<ul>
			<li><a href="carrito?accion=verCarrito">Ir a carrito de
					compras</a></li>
			<li><a href="catalogo.jsp">Comprar Artículos</a></li>
			<li><a href="adminCuentaUsuario.jsp">Administrar cuenta
					usuario</a></li>
			<li><a href="contact.jsp">¿Sugerencias? ¡Da click aquí!</a></li>
		</ul>


		<!-- Aqui acaba el content -->
	</div>
	<%
		} else {
	%>
	<jsp:include page="errorUsuarioSesion.jsp" />
	<%
		}
		} catch (Exception e) {
	%>
	<jsp:include page="errorUsuarioSesion.jsp" />
	<%
		}
	%>
	<!-- content -->
	<jsp:include page="elementos/footer.jsp" />
</body>
</html>