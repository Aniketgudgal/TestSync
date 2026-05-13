function getStudentName() {
    fetch("getstudentsession")

        .then(function(response) {
            return response.text();
        })

        .then(function(data) {
            if (data == "Not Logged In") {
                window.location.href =
                    "StudentLogin.html";
            }
            else {
                document.getElementById(
                    "studentName"
                ).innerHTML = data;
            }
        })

        .catch(function(error) {
            console.log(error);
        });
}

getStudentName(); 

