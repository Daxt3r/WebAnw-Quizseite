function hinweisFunction() {
	alert("Nach Beendigung vom Quiz werden Name und E-Mail erfordert. Diese können wieder manuell gelöscht werden.");
	document.getElementById("hinweis").innerHTML = "Hinweis ist gelesen, danke!";
}

function sendPUT() {
	var contentType;
	var url ="http://localhost:8080/updatePlayerData"; //url an die die Daten geschickt werden sollen
	var json;
	
	//The getElementById() method returns the element that has the ID attribute with the specified value. 
	var method = "PUT"
	//var message = document.getElementById('message').value;
	
	contentType = 'application/json; charset=utf-8';
	
	//JavaScript Objekt wird erstellt und die eingegebenen Werte werden in das Objekt gespeichert 
	var player = new Object();
	player.sName = document.getElementById('sName').value;
	player.sMail = document.getElementById('sMail').value;
	player.sNewMail = document.getElementById('sNewMail').value;
	
	//Convert a JavaScript object into a string
	json = JSON.stringify(player); 
	
	//The XMLHttpRequest object can be used to request data from a web server.
	var	xmlhttp = new XMLHttpRequest();
	//To send a request to a server
	xmlhttp.open(method, url, true);
	xmlhttp.setRequestHeader('Content-type', contentType);
	
	if (method !== "GET") {
		xmlhttp.onload = function() {
			var	result = JSON.parse(xmlhttp.responseText);
			//4: request finished and response is ready
			//200: "OK"
			if(xmlhttp.readyState == 4 && xmlhttp.status ==	"200") {
				console.table(result);
			} 
			else
			{
				console.error(result);
			}
		}
	}
	xmlhttp.send(json);
}
