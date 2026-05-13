function getAdminName() {

	fetch("getadminsessiondata")

	.then(function(response) {
		return response.text();
	})

	.then(function(data) {

		if (data === "Not Logged In") {
			window.location.href =
				"AdminLogin.html";
		}
		else {
			document.getElementById(
				"adminName"
			).innerHTML = data;
		}
	})

	.catch(function(error) {
		console.log(error);
	});
}

getAdminName();