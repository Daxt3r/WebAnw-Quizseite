function hinweisFunction() {
	var x = document.getElementById("hinweis");
	alert("Nach Beendigung vom Quiz werden Name und E-Mail erfordert. Diese können wieder manuell gelöscht werden.");
	document.getElementById("hinweis").innerHTML = "Hinweis ist gelesen, danke!";
	x.style.color = "green";
}

function versteckFunction() {
	var x = document.getElementById("verstecken");
		if (x.style.display === "none") {
			x.style.display = "block";
		}
		else {
			x.style.display = "none";
		}
}