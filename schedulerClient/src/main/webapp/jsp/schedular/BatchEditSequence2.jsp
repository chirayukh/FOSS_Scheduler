<script>
$(document).ready(function() {
	var i;
	for (i = 0; i< 500; i++){
		document.getElementById(i).innerHTML="";
	}
	document.getElementById("xtest").value="${xtest}";
	var xtest=document.getElementById("xtest").value;
	var z=xtest.split("|");
	var data="";
	for(var x in z){
		var y=z[x].split(",");
			document.getElementById([y[1]]).innerHTML='<button id="'+y[0]+'" name="'+y[0]+'" class="btn btn-rounded btn-gradient-info mr-2" draggable="true" ondragstart="drag(event)" onclick="return false;" style="width:90%;margin:5px;padding:10px 0px;">'+y[0]+'</button></div>';
	}
	
		
});
</script>

						
					 	<%@page import="java.util.ArrayList"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:forEach items="${daglistdto1}" var="daglistdto1">
<button id="${daglistdto1.BATCH_ID}" name="${daglistdto1.BATCH_ID}" class="btn btn-rounded btn-gradient-info mr-2" draggable="true" ondragstart="drag(event)" onclick="return false;" style="width:90%;margin:5px;padding:10px 0px;">${daglistdto1.BATCH_ID}</button>
</c:forEach> 

