<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <script type="text/javascript">
$(document).ready(function()  {
	
	
	 $("#approve ").click(function(){
			var $row = $(this).closest("tr");
			var $feedId = $row.find('td:eq( 0 )').html();
			//var $jobId = $row.find('td:eq( 1 )').html();
				   $.post('${pageContext.request.contextPath}/admin/approveBatchJob', {
					   feedId : $feedId
					}, function(data) {
						window.location.reload();
						//window.location.href='${pageContext.request.contextPath}/admin/ApproveBatch'
					
						alert("Feed aproved");		
					});
				});
		
		
		$("#reject ").click(function(){
			var $row = $(this).closest("tr");
			var $feedId = $row.find('td:eq( 0 )').html();
			//var $jobId = $row.find('td:eq( 1 )').html();
				   $.post('${pageContext.request.contextPath}/admin/rejectBatchJob', {
					   feedId : $feedId
					}, function(data) {
						window.location.reload();
						 
						alert("Feed rejected");		
					});
	});  


		 $("#ViewDetails ").click(function(){
			var $row = $(this).closest("tr");
			var $feedId = $row.find('td:eq( 0 )').html();
			//var $jobId = $row.find('td:eq( 1 )').html();
				   $.post('${pageContext.request.contextPath}/admin/viewBatchJob', {
					   feedId : $feedId
					}, function(data) {
						/* document.getElementById('allvalues1').style.display = "block"; */
						 $('#alldetails').html(data);
						//alert("deatails");	
						
					});
						});
	});
						
	</script> 
	
	

			<div id="allvalues" style="display: block;">
				 <table class="table table-bordered"   >
                    <thead>
                      <tr style="color: green;;font: bolder;">
                      <th >
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
						<a href="#" ><img name="reject" id="reject" src="${pageContext.request.contextPath}/assets/img/reject.png"  alt="Image" height="160" width="160"class="rounded">
						</a>
						 </td>
					 <td style="max-width:30px">
						  <a href="#" ><img name="ViewDetails" id="ViewDetails" src="${pageContext.request.contextPath}/assets/img/view1.jfif"  alt="Image" style="height: 36px; width: 70px;" class="rounded">
						</a>
						
		
						
						</td>  
						</tr>
	                </c:forEach>
                     </tbody>
                  </table>
                 </div>
                  <br>
                 <br>
                
                 <div id="alldetails"></div>
               
                
               