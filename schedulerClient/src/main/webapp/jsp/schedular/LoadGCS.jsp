<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	function gcs_sa_change(id, val) {
		
		id = id.substr(-1);
		var gcs_project_id = "gcs_project" + id;
		var gcs_service_account_id="gcs_service_account"+id;
		var gcs_project = document.getElementById(gcs_project_id).value;
		var gcs_service_account = val;
		if (gcs_project == "") {
			alert("Please select GCS project");
		} else {
			$.post('${pageContext.request.contextPath}/scheduler/LoadBuckets',
					{
						gcs_project : gcs_project,
						gcs_service_account : gcs_service_account,
						id : id
					}, function(data) {
						$('#bucket'+id).html(data)
					});
		}
	}
</script>


<div class="form-group row">
	<div class="col-md-12">
		<label>GCS Command Type *</label> <select class="form-control"
			id="gcs_command_type${id}" name="gcs_command_type">
			<option value="" selected disabled>Select Command Type</option>
			<option value="shell">shell</option>
			<option value="python">python</option>
			<option value="java">java</option>
			<option value="bigquery">bigquery</option>
		</select>
	</div>
	<div class="col-md-6">
		<label>Select GCP Project *</label> <select class="form-control"
			id="gcs_project${id}" name="gcs_project${id}">
			<option value="" selected disabled>Select GCP Project</option>
			<c:forEach items="${tproj}" var="tproj">
				<option value="${tproj}">${tproj}</option>
			</c:forEach>
		</select>
	</div>
	<div class="col-md-6">
		<label>Select Service Account *</label> <select class="form-control"
			id="gcs_service_Account${id}" name="gcs_service_Account${id}"
			onchange="gcs_sa_change(this.id,this.value)">
			<option value="" selected disabled>Select Service Account</option>
			<c:forEach items="${service_acc}" var="service_acc">
				<option value="${service_acc}">${service_acc}</option>
			</c:forEach>
		</select>
	</div>
	<div class="col-md-12">
		<div id="bucket${id}"></div>
	</div>
</div>