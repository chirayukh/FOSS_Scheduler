<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
function getBucketFiles(id,val){
	var id1=id.substr(-1);
		var gcs_project_id="gcs_project"+id1;
		var gcs_service_Account_id="gcs_service_Account"+id1;
			var gcs_project=document.getElementById(gcs_project_id).value;
			var gcs_service_account=document.getElementById(gcs_service_Account_id).value;
			var bucket=val;
			id=id1;
			$.post('${pageContext.request.contextPath}/scheduler/LoadBucketFiles', {
				gcs_project : gcs_project,
				gcs_service_account : gcs_service_account,
				bucket : bucket,
				id : id
			}, function(data) {
				$('#bucketfiles'+id1).html(data)
			});
}
</script>


<label>Select Bucket *</label>
<select class="form-control" id="bucket${id}" name="bucket${id}"
	onchange="getBucketFiles(this.id,this.value)">
	<option value="" selected disabled>Select Bucket</option>
	<c:forEach items="${buckets}" var="buckets">
		<option value="${buckets}">${buckets}</option>
	</c:forEach>
</select>


<div id="bucketfiles${id}"></div>