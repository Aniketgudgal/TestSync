 window.onload = function(){
	loadCourses();
 }
 
 function loadCourses(){
	fetch("GetCourseController").then(response => response.text()).then(data =>{
		document.getElementById("choosecourse").innerHTML += data;
	}).catch(error =>{
		console.log(error);
	});
	
 }