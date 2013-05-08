var estado = "cerrado";
$(document).ready(function() {
	$("#boton").click(function() {
		if(estado == "cerrado")
		{
			$("#users").animate({
				top: "0px"
			}, 500);
			$(".boton").animate({
				marginTop: "50px",
				borderTopLeftRadius: "5px"
			}, 500);
			$("#boton").text("X");
			estado = "abierto";
		}
		else
		{
			$("#users").animate({
				top: "-52px"
			}, 500);
			$(".boton").animate({
				marginTop: "0px",
				borderTopLeftRadius: "0px"
			}, 500);
			$("#boton").text("Usuarios");
			estado = "cerrado";
		}
	});
});