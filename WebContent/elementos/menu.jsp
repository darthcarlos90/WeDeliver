<div id="users">
	<div id="forma">
		<%
			//sesionIniciada;
			try {
				String sesion = (String) session.getAttribute("sesion");
				if (sesion.equals("Activa")) {
		%>
		<jsp:include page="sesion2.jsp"></jsp:include>

		<%
			} else {
		%>
		<jsp:include page="sesion.jsp"></jsp:include>
		<%
			}
			} catch (Exception e) {
		%>
		<jsp:include page="sesion.jsp"></jsp:include>
		<%
			}
		%>


	</div>
	<!-- forma -->
</div>
<!-- users -->
<div class="boton">
	<a id="boton">Usuarios</a>
</div>
<div id="header">
	<div id="logo" class="left">
		<a href="index.jsp"><img alt="logo" src="images/logoChico.png"
			class="logo"></img></a>
	</div>
	<!-- up -->
	<div id="inner" class="center">
		<a href="index.jsp">Home</a>
		<a href="about.jsp">¿Quienes Somos?</a>
		<a href="catalogo">Catalogo</a>
		<a href="contact.jsp">Contacto</a>
		<a href="Register.jsp">Registro</a>
	</div>
	<!-- inner -->
</div>
<!-- header -->