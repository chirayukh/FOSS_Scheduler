<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../cdg_header2.jsp" />
<script>
	function json_construct() {

		var button_type=document.getElementById('button_type').value;
		var errors = [];
		var flowname;
		if(button_type=="create"){
			flowname = document.getElementById("flowname").value;
			var schtype=document.getElementById("schtype").value;
			if (!checkLength(schtype)) {
				errors[errors.length] = "schtype";
			}
		}else if (button_type == "edit"){
			flowname = document.getElementById("flow1").value;
		}
		if (!checkLength(flowname)) {
			errors[errors.length] = "flowname";
		}
	
		
		get_seq();
		var sequence=document.getElementById('sequence').value;
		if (!checkLength(sequence)) {
			errors[errors.length] = "Please create sequence";
		}
		if (errors.length > 0) {
			reportErrors(errors);
			return false;
		}
		
		var data = {};
		$(".form-control").serializeArray().map(function(x) {
			data[x.name] = x.value;
		});
		var x = '{"header":{"user":"info@clouddatagrid.com","service_account":"Extraction_CDG_UK","reservoir_id":"R0001","event_time":"today"},"body":{"data":'
				+ JSON.stringify(data) + '}}';
		document.getElementById('x').value = x;
		//alert(x);
		//console.log(x);
		document.getElementById('seqpip').submit();
	}
	
	
	$(document).ready(function() {
		$("#schtype").change(function() {
			var schtype = $(this).val();
			
			if(schtype=='RE'){
				document.getElementById('firstregbt').style.display = "block";
			}else{
				document.getElementById('reg1').value = "";
				document.getElementById('firstregbt').style.display = "none";
			}
				var project_id =document.getElementById('project').value;
				disableForm(seqpip);
			       $('#loading').show();
				$.post('/scheduler/CreateBatchSequence2', {
				schtype :schtype,
				project_id :project_id
				},
				 function(data) {
					$('#loading').hide();
					enableForm(seqpip);
					$('#connfunc2').html(data)
				}).fail(function() {
                    alert("System error: Something went wrong");
                    $('#loading').hide();
             });
		});
		$("#reg1").change(function() {
			var reg = $(this).val();
			document.getElementById("0").innerHTML='<button id="'+reg+'" name="'+reg+'" class="btn btn-rounded btn-gradient-info mr-2" draggable="false" ondragstart="drag(event)" onclick="return false;" style="width:90%;margin:5px;padding:10px 0px;">'+reg+'</button></div>';
		});
	});
	
	
	function funccheck(val) {
		if (val == 'create') {
// 			document.getElementById('button_type').value="create";
// 			document.getElementById('editflow').style.display = "none";
// 			document.getElementById("connfunc3").style.display="none";
			window.location.reload();
		} else {
			document.getElementById('gridc').style.display = "none";
			document.getElementById('editflow').style.display = "block";
			document.getElementById('createflow').style.display = "none";
			document.getElementById('button_type').value="edit";
			document.getElementById("connfunc2").style.display="none";
		}	
	}
	
	function testfunc1(val){
		var flowname = val;
		var project_id =document.getElementById('project').value;
		disableForm(seqpip);
	       $('#loading').show();
			$.post('/scheduler/BatchEditSequence1', {
				flowname : flowname,
				project_id :project_id
			}, function(data) {
				$('#loading').hide();
				enableForm(seqpip);
				$('#connfunc3').html(data)
			}).fail(function() {
                alert("System error: Something went wrong");
                $('#loading').hide();
         });
	}
		
	
	function disableForm(theform) {
	    if (document.all || document.getElementById) {
	        for (i = 0; i < theform.length; i++) {
	        var formElement = theform.elements[i];
	            if (true) {
	                formElement.disabled = true;
	            }
	        }
	    }
	}


	function enableForm(theform) {
	    if (document.all || document.getElementById) {
	        for (i = 0; i < theform.length; i++) {
	        var formElement = theform.elements[i];
	            if (true) {
	                formElement.disabled = false;
	            }
	        }
	    }
	}

</script>

<div class="main-panel">
	<div class="content-wrapper">
		<div class="row">
			<div class="col-12 grid-margin stretch-card">
				<div class="card">
					<div class="card-body">
						<h4 class="card-title">Batch Sequence</h4>
						<p class="card-description">Create Sequence</p>
						<%
               if(request.getAttribute("successString") != null) {
               %>
            <div class="alert alert-success" id="success-alert">
               <button type="button" class="close" data-dismiss="alert">x</button>
               ${successString}
            </div>
            <%
               }
               %>
            <%
               if(request.getAttribute("errorString") != null) {
               %>
            <div class="alert alert-danger" id="error-alert">
               <button type="button" class="close" data-dismiss="alert">x</button>
               ${errorString}
            </div>
            <%
               }
               %>
           
						
							
							
             
               <input type="hidden" name="xtest" id="xtest" value="${xtest}">
                <input type="hidden" name="button_type" id="button_type" value="create" class="form-control">
						<form role="form" class="forms-sample" id="seqpip" name="seqpip"
							action="${pageContext.request.contextPath}/scheduler/CreateBatchSequenceSubmit"
							method="post" enctype="application/json">
							  <input type="hidden" name="x" id="x" value="">
							  <fieldset class="fs">
							<div class="form-group row">
								<label class="col-sm-3 col-form-label">Sequence</label>
								<div class="col-sm-4">
									<div class="form-check form-check-info">
										<label class="form-check-label"> <input type="radio"
											class="form-check-input" name="radio" id="radio1"
											checked="checked" value="create"
											onclick="funccheck(this.value)"> Create
										</label>
									</div>
								</div>
								<div class="col-sm-4">
									<div class="form-check form-check-info">
										<label class="form-check-label"> <input type="radio"
											class="form-check-input" name="radio" id="radio2"
											value="edit" onclick="funccheck(this.value)">
											Edit/View
										</label>
									</div>
								</div>
							</div>
								
							<div id="createflow">
							<div class="form-group row">
											<div class="col-sm-12">
												<label>Batch Flow Name *</label> <input type="text"
													class="form-control" id="flowname" name="flowname"
													placeholder="Batch Flow Name">
								</div>
								</div>
								<div class="form-group row" id="connfunc">
								<div class="col-sm-12">
								<label>Schedule Type *</label> 
								<select class="form-control" name="schtype" id="schtype">	
									<option value="" selected disabled>Select Schedule Type</option>
									<option value="R">Regular-Regular</option>
									<option value="E">Event-Event</option>
									<option value="RE">Regular-Event</option>
									</select>
							</div>
							</div>	
							</div>
							
							
							<div class="form-group row" id="editflow" style="display:none;">
								<div class="col-sm-12">
								<label>Select Flowname *</label> 
								<input list="flow" id="flow1" name="flow1" class="form-control" onchange="testfunc1(this.value)">
									<datalist id="flow">
									  <c:forEach items="${flow1}" var="flow1">
										<option value="${flow1.BATCH_SEQUENCE_ID}">
									</c:forEach>
									</datalist>
							</div>
							</div>	
							
							<div class="form-group row" id="firstregbt" style="display:none;">
								<div class="col-sm-12">
								<label>Select First Regular Batch *</label> 
								<input list="reg" id="reg1" name="reg1" class="form-control">
									<datalist id="reg">
									  <c:forEach items="${reg1}" var="reg1">
										<option value="${reg1.BATCH_ID}">
									</c:forEach>
									</datalist>
							</div>
							</div>	
							
										
							<div id="connfunc2"></div>
							<div id="connfunc3"></div>
							<div id="gridc" class="grid-container" class="double-scroll" style="display:none;float:right;width:80%;height:600px;overflow:auto;overflow-y:hidden;scrollbar-x-position:top;border:1px solid grey;border-radius:10px;"></div>
							
							</fieldset>
							<div class="form-group row">
								<label class="col-sm-12 col-form-label"><b><i>For MVP1, we are supporting one to one dependecy between batches!!</i></b></label>
								</div>
							<input type="hidden" name="sequence" id="sequence"
								class="form-control"> <input type="hidden" name="x"
								id="x">
							<input type="hidden" name="project" id="project" class="form-control"
								value="${project}">
							<center>
								<button class="btn btn-rounded btn-gradient-info mr-2" style="margin: 10px;" onclick="return json_construct();">Create Flow</button>
							</center>	
							</div>	
						</form>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../cdg_footer.jsp" />
		