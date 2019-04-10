						<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%> 




<div class="form-group" id="multibatchselect">
											<div class="col-md-12"></div>
											<label>Batch Id<span style="color: red">*</span></label>
											<select class="form-control" id="batchid" name="batchid"
												multiple="multiple">
												<c:forEach items="${daglistdto}" var="daglistdto">
													<option value="${daglistdto.BATCH_ID}">${daglistdto.BATCH_ID}</option>
												</c:forEach>
											</select>
</div>
							<div id="composerList" style="display:none;">						
							<div>
							<div style="float:left;width:20%;height:25px;font-weight:bold;text-align:center;">Batches</div>
							<div style="float:right;width:80%;height:25px;font-weight:bold;text-align:center;">Batch Sequence ---></div>
							</div>	
							<div>
							<div id="daglist" class="double-scroll"  ondrop='drop(event,this)' ondragover='allowDrop(event)' style="float:left;width:20%;height:600px;overflow:auto;scrollbar-x-position:top;border:1px solid grey;border-radius:10px;">				
							</div>
							</div>
							</div>

							

		

						
							
							
										<script>
		var select = document.getElementById('batchid');
		multi(select, {});
		
		 $(document).ready(function() {
				$("#batchid").change(function() {
					var project_id =document.getElementById('project').value;
					disableForm(seqpip);
				       $('#loading').show();
					var batch_id="";
					var multiselect = $(this).val();
					for(i=0;i< multiselect.length;i++){
						batch_id=multiselect[i]+","+batch_id;
					}
					if(document.getElementById('button_type').value=="create"){
						document.getElementById("composerList").style.display="";
						var project_id =document.getElementById('project').value;
						$.post('/scheduler/CreateBatchSequence3', {
							batch_id : batch_id,
							project_id :project_id
						}, function(data) {
						$('#loading').hide();
						enableForm(seqpip);
						$('#daglist').html(data);
						document.getElementById("gridc").style.display="grid";
					}).fail(function() {
		                alert("System error: Something went wrong");
		                $('#loading').hide();
		         });
					}
				});
			})
	</script>
