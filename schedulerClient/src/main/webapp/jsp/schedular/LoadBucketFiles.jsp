<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<label>Select Script Location *</label>
<select class="form-control" id="bucket_files${id}"
	name="bucket_files${id}">
	<option value="" selected disabled>Select Script Location</option>
	<c:forEach items="${bucketfiles}" var="bucketfiles">
		<option value="${bucketfiles}">${bucketfiles}</option>
	</c:forEach>
</select>
