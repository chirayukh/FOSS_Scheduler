<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
							
							<div id="detailvalues1" style="display: block;">
				 <table class="table table-bordered"   >
                    <thead>
                      <tr style="color: green;;font: bolder;">
                      <th >
                         Job Name
                        </th>
                
                       <th>
                       Command Type
                       </th>
                       <th>
                       Updated Date
                       </th>
                       <th>
                       Updated By
                       </th>
                       <th>
                       Host Name
                       </th>
                       <th>
                       User Name
                       </th>
                       <th>
                       Is Dependent Job
                       </th>
                         <th>
                        created date
                        </th> 
                      </tr>
                    </thead>
                    <tbody>
                   <c:forEach var="row" items="${viewFeedJob}">
	                    <tr>
	                   <td style="max-width:150px"><c:out value="${row.job_name}" /></td>
						
						<td style="max-width:150px"><c:out value="${row.command_type}" /></td>
						<td style="max-width:150px"><c:out value="${row.updated_date}" /></td>
						<td style="max-width:150px"><c:out value="${row.updated_by}" /></td>
						<td style="max-width:150px"><c:out value="${row.host_name}" /></td>
						<td style="max-width:150px"><c:out value="${row.user_name}" /></td>
						<td style="max-width:150px"><c:out value="${row.is_dependent_job}" /></td>
						<td style="max-width:150px"><c:out value="${row.created_date}" /></td>
						
						</tr>
	                </c:forEach>
                     </tbody>
                  </table>
                 </div>
                 </form>