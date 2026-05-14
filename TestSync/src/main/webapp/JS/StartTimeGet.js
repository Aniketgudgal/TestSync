window.onload = function () {

    let buttons =
        document.querySelectorAll(
            "button[data-exam]"
        );

    buttons.forEach(button => {

        let examId =
            button.getAttribute(
                "data-exam"
            );

        loadExamTime(examId);

    });

}

function loadExamTime(examId){

    let aj = new XMLHttpRequest();

    aj.open(
        "GET",
        "/TestSync/getExamStartTime?es_id="
        + examId,
        true
    );

    aj.onreadystatechange = function(){

        if(this.readyState == 4
            &&
           this.status == 200){

            let startTime =
                this.responseText.trim();

            console.log(startTime);

            checkTime(startTime, examId);

        }

    };

    aj.send();

}

function checkTime(examTime, examId) {
    setInterval(() => {
        let now = new Date();
        let currentTime = now.toTimeString().split(" ")[0];

		console.log(currentTime);
        let current = new Date("1970-01-01T" + currentTime);
        let exam = new Date("1970-01-01T" + examTime);
		console.log(exam);
		console.log(current);
        let btn = document.getElementById("strt" + examId);

        if (current >= exam) {
            // ✅ Enable button once exam time is reached
            btn.disabled = false;
            btn.classList.remove("disable-btn");
            btn.classList.add("enable-btn");
        } else {
            // ❌ Keep disabled before exam time
            btn.disabled = true;
            btn.classList.remove("enable-btn");
            btn.classList.add("disable-btn");
        }
    }, 1000);
}
