var asyncRequest; // variable para poner el objeto XMLHttpRequest

// Agrergar los manejadores de eventos, en este caso es solo 1

function registerListeners() {
	var submitButton;
	submitButton = document.getElementById("cambiar");
	submitButton.addEventListener("click", function() {
		getContent("NewPassword.html");
	}, false);
}

// Preparar la petici�n as�ncrona.
function getContent(url) {
	// Intentar crear un objeto XMLHttpRequest y hacer la petici�n
	try {
		asyncRequest = new XMLHttpRequest(); // crear el objeto de request

		// registrar el manejador de eventos
		asyncRequest.addEventListener("readystatechange", stateChange, false);
		asyncRequest.open("GET", url, true);// preparar la
		// petici�n
		asyncRequest.send(null); // mandar la petici�n
	} catch (exception) {
		alert("Request failed.");
	}
}

function stateChange() {
	if (asyncRequest.readyState == 4 && asyncRequest.status == 200) {
		document.getElementById("nuevoPass").innerHTML = asyncRequest.responseText;
	}
}

window.addEventListener("load", function() {
	registerListeners();
}, false);
