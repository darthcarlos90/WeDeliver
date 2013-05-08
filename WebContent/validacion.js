function validar() {

	var pass = document.getElementById('password').value;
	var pass2 = document.getElementById('cPassword').value;

	var boleana = checaPasswords(pass, pass2);

	if (boleana == false) {
		alert("Las contraseñas no concuerdan, favor de poner dos contraseñas que concuerden");
		cambiaEstilo();
		return false;

	} else {
		return true;
	}
}

function checaPasswords(a, b) {
	if (b == a) {
		return true;
	} else {
		return false;
	}
}

function cambiaEstilo() {
	document.getElementById('contra').style.color = "red";
	document.getElementById("contra").style.fontFamily = "Arial";
	document.getElementById("contra").style.fontSize = "larger";
	document.getElementById('contra2').style.color = "red";
	document.getElementById("contra2").style.fontFamily = "Arial";
	document.getElementById("contra2").style.fontSize = "larger";

}
