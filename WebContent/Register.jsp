<!DOCTYPE html>
<html>
<head>
<jsp:include page="elementos/head.jsp" />
</head>
<script type="text/javascript" src="validacion.js"></script>
<body>
	<div id="content">
	<jsp:include page="elementos/menu.jsp" />
	
	<br>
	
		
		<!-- Aqui empieza el content -->
<h1>¡Hola!</h1>
<br>
<h2>Ingresa tus datos para completar el registro:</h2>
		<jsp:include page="elementos/registro.jsp"></jsp:include>

		<!-- Aqui acaba el content -->
		<jsp:include page="elementos/footer.jsp" />
	</div>
	<!-- content -->
</body>
</html>