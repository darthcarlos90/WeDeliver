<!DOCTYPE html>
<html>
	<jsp:include page="elementos/head.jsp" />
	<script type="text/javascript" src="validacion.js"></script>
	<body>
	<jsp:include page="elementos/menu.jsp" />
		<div id="content">
			
			<!-- Aqui empieza el content -->
			<br>
			<h1>¡Contactanos!</h1>
			<br>
			<h2>Déjanos tus datos si tienes alguna duda y nosotros estaremos en contacto</h2>
			 
<form name="registro" onsubmit="return validar()" method="get" action= "validado.html" class="registro">
    <table>
        <tr>
            <td>Nombre</td>
            <td><input type= "text" name= "nombre" id="nombre"/></td>
        </tr>
        <tr>
            <td>Correo</td>
            <td> <input type= "email" name= "email" placeholder= "tumail@dominio.com" required/></td>
        </tr>
        <tr>
            <td>Matrícula</td>
            <td><input type= "text" name="matricula" placeholder="Solo números" id="matricula"/></td>
        </tr>
        <tr>
            <td>Carrera</td>
            <td><input type="text" name="carrera" id="carrera"/></td>
        </tr>
        <tr>
            <td>Comentarios</td>
            <td><input type="text" name="carrera" id="carrera"/></td>
           <!--  <textarea rows="5" cols="21">Agrega aqui tus comentarios</textarea>
            --> </td>
        </tr>
        <tr>
            <td><input type="submit" value="Confirmar" name="submit"></td>
            <td><input type="reset" value="Cancelar" name="cancelar"></td>
        </tr>
    </table>
</form>
			
			<!-- Aqui acaba el content -->
			
		</div><!-- content -->
		<jsp:include page="elementos/footer.jsp" />
	</body>
</html>





 