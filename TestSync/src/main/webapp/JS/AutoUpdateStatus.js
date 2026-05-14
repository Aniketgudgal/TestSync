/**
 * 
 */
window.onload = function () {
	 updateAttempted();
    setInterval(updateAttempted, 60000);
}
function updateAttempted(){

    fetch("/TestSync/updateAttempt")
    .then(response => response.text())
    .then(data => {
        console.log(data);
    }).catch(error => {
		console.log(error);
    });
}