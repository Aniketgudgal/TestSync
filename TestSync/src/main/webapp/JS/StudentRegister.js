// window.onload = function(){
//	loadCourses();
 //}
 
 window.onload = function () {
     loadCourses();
 }

 function loadCourses(){

     fetch("/TestSync/getCourseController")
     .then(response => response.text())
     .then(data => {

         document.getElementById("choosecourse").innerHTML =
             '<option value="">Select course</option>' + data;

         console.log(data);

     })
     .catch(error => {

         console.log(error);

     });

 }
/*
 function loadCourses()
 {
 	let data = document.getElementById("choosecourse");
 	let aj = new XMLHttpRequest()
	console.log("click");
 	aj.open("GET" , "/TestSync/GetCourseController",true);
 	aj.onreadystatechange = function(){
 		if(this.readyState == 4 && this.status == 200)
 			{
 				console.log(this.responseText);
 				data.innerHTML = this.responseText;
 			}
 	};
 	aj.send();
 }
 */