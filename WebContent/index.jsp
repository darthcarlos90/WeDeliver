<!DOCTYPE html>
<html>
<head>
<jsp:include page="elementos/head.jsp" />
<script type="text/javascript" src="validacion.js"></script>
<link rel="stylesheet" href="css/nivo-slider.css" type="text/css"
	media="screen" />
<script src="js/jquery.nivo.slider.pack.js" type="text/javascript"></script>
</head>
<body>

	<jsp:include page="elementos/menu.jsp" />
	<div id="content"><!--  content -->
		<!--  Nivo Slider -->
		<div class="center">
		<div id="slider" class="nivoSlider">
			<img src="images/slide1.jpg"  />
			<img src="images/slide2.jpg"/>
		</div>
	</div>
		<!--  Nivo Slider -->
		<br>
		<h1>¡Bienvenido a We Deliver!</h1>
		<br>
		<h3>Somos una empresa pequeña con el proposito de ayudar a los
			alumnos del ITESM con sus proyectos escolares.</h3>
		<br>
		<h4>
			Te invitamos a que te <a href="Register.jsp">registres</a> para
			efectuar pedidos.
		</h4>
		

	</div>
<!-- Aqui acaba el content -->
	<jsp:include page="elementos/footer.jsp" />
</body>
</html>