<jsp:include page="../cdg_header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
$(document).ready(function() {
			$("#frequency").change(function() {
				var frequency = $(this).val();
				//alert(frequency);
				var batchId = document.getElementById('arrfeedId').value
				$.post('${pageContext.request.contextPath}/admin/frequency', {
					frequency : frequency,
					batchId : batchId
				}, function(data) {
					$('#allvalues').html(data);
				});
		
	});
			
			
			$("#arrfeedId").change(function() {
				var frequency = document.getElementById('frequency').value
				var batchId = $(this).val();
				$.post('${pageContext.request.contextPath}/admin/batchid', {
					batchId : batchId,
					frequency : frequency
				}, function(data) {
					 $('#allvalues').html(data);
					
				});
			
			});
			
			 
});
	
						
					

	
</script>
<div class="main-panel">
	<div class="content-wrapper">
		<div class="row">
			<div class="col-12 grid-margin stretch-card">
				<div class="card">
					<div class="card-body">
					<h4 class="card-title">All Jobs</h4>
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
							<div class="row">
						   <div class="form-group col-md-6 ">
						   		<label>Batch Id</label> 
						   		
											<select class="form-control" name="arrfeedId" id="arrfeedId">
												<option value="ALL" selected="selected">All</option>
											    <c:forEach items="${arrfeedId}" var="arrfeedId">
													<option value="${arrfeedId}">${arrfeedId}</option>
												</c:forEach>									  	
											</select>
				          </div>

				          <div class="form-group col-md-6">
				          <label>Frequency</label> 
											<select class="form-control" name="frequency" id="frequency">
												<option value="ALL" selected="selected">All</option>
											    <option value="DAILY">Daily</option>
											    <option value="WEEKLY">Weekly</option>
											    <option value="MONTHLY">Monthly</option>
											    <option value="YEARLY">Yearly</option>
									  		</select>
				          </div>
				          </div>	
        
					</div>
				</div>
			</div>
		</div>
	
         
		<div class="row">
			<div class="col-12 grid-margin stretch-card">
				<div class="card">
					<div class="card-body">
						<h4 class="card-title">Job Details</h4>
						<%
							if (request.getAttribute("successString") != null) {
						%>
						<p class="text-success h4">${successString}</p>
						<%
							}
						%>
						<%
							if (request.getAttribute("errorString") != null) {
						%>
						<p class="text-danger h4">${errorString}</p>
						<%
							}
						%>
					<form class="forms-sample" id="viewDetails1"
							name="viewDetails1" method="POST"
							action="${pageContext.request.contextPath}/admin/viewFeedJob"
							enctype="application/json">						

			<div id="allvalues" style="display: block;">
				 <table class="table table-bordered" id="feedId1"  >
                    <thead>
                      <tr style="color: green;;font: bolder;">
                      <th style="">
                          Batch Id 
                        </th>
                        <th>
                         Schedule Info
                        </th>
                    	<th>
                         Approve
                        </th>
                        <th >
                         Reject
                        </th>
                        <th>
                        View Batch Details
                        </th> 
                      </tr> 
                    </thead>
                    <tbody>
                   <c:forEach var="row" items="${allLoadJobs}">
	                    <tr>
	                    <td style="max-width:150px"><c:out value="${row.batch_id}" /></td>
						<td style="max-width:150px"><c:out value="${row.consolidatedSchedule}" /></td>
						<td style="max-width:30px">
							<input type="hidden" id="img_id" value="${row.in_current}"/>
							<a href="#">
								<img id="approve" name="approve" src="${pageContext.request.contextPath}/assets/img/approve.jfif" 
					      				alt="Image" height="160" width="160"class="rounded"  >
							</a>					
						</td>
							
						<td style="max-width:30px">
						<a href="#" ><img name="reject" id="reject" src="${pageContext.request.contextPath}/assets/img/reject.png"  alt="Image" height="160" width="160" class="rounded">
						</a>
						</td>
					  <td style="max-width:30px">
						<a href="#" ><img name="ViewDetails" id="ViewDetails" src="${pageContext.request.contextPath}/assets/img/view1.jfif"  alt="Image" style="height: 36px; width: 70px;" class="rounded">
						</a>
						
						</td>						</tr>
	                </c:forEach>
                      
                     </tbody>
                  </table>
                 </div>
                 <br>
                 <br>
               
						
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../cdg_footer.jsp" />