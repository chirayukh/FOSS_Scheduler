<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


	<div class="col-sm-12">
		<label>Command/BQL name*</label> <select class="form-control"
			id="command${id}" name="command${id}">
			<option value="" selected disabled>Select Command/BQL name</option>
			<c:forEach items="${script_files}" var="script_files">
					<option value="${script_files.files}">${script_files.files}</option>
			</c:forEach>
			
		
		</select>
	</div>
