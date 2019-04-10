<jsp:include page="cdg_header.jsp" />

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
{
  box-sizing: border-box;
}

.zoom {
  padding: 50px;
  background-color: #fff !important;
  transition: transform .2s;
  width: 200px;
  height: 200px;
  margin: 0 auto;
}

.zoom:hover {
  -ms-transform: scale(1.5); /* IE 9 */
  -webkit-transform: scale(1.5); /* Safari 3-8 */
  transform: scale(1.5); 
}
body {
	  padding-top: 50px;
	}
	 
	.thumbnail {
	    position:relative;
	    overflow:hidden;
	}
	 
	.caption {
	    position:absolute;
	    top:0;
	    right:0;
	    background:rgba(90, 90, 90, 0.75);
	    width:100%;
	    height:100%;
	    padding:2%;
	    display: none;
	    text-align: left;
	    color:#fff !important;
	    z-index:2;
	}
	
	
	
</style>
</head>
</html>
<script type="text/javascript">
$(document).ready(function() {
    $("[rel='tooltip']").tooltip();    
 
    $('.thumbnail').hover(
        function(){
            $(this).find('.caption').slideDown(250); //.fadeIn(250)
        },
        function(){
            $(this).find('.caption').slideUp(250); //.fadeOut(205)
        }
    ); 	
 });
</script>

<style>
</style>
 <div class="main-panel">
        <div class="content-wrapper" >

		<div class="row">
			<div class="col-12 grid-margin stretch-card">
			    
				<div class="card">
					<div class="card-body">
					<div class="form-group row">
								<label class="col-sm-3 col-form-label"><b><i>Create new batches</i></b></label>
								</div>
								  <div class="row text-center text-lg-left">
								   <div class="thumbnail col-lg-3 col-md-4 col-xs-6">
								      	<a class="d-block mb-4 h-100" href="/scheduler/AddBatch">
								      		<img class="zoom img-fluid img-thumbnail" src="${pageContext.request.contextPath}/assets/img/bb.png" >
								      	</a>
								    </div>
								    <div class="thumbnail col-lg-3 col-md-4 col-xs-6">
								      	<a class="d-block mb-4 h-100" href="/scheduler/AddTask">
								      		<img class="zoom img-fluid img-thumbnail" src="${pageContext.request.contextPath}/assets/img/tt.png" >
								      	</a>
								    </div>
								    <div class="thumbnail col-lg-3 col-md-4 col-xs-6">
								      	<a class="d-block mb-4 h-100" href="/scheduler/CreateSequence">
								      		<img class="zoom img-fluid img-thumbnail" src="${pageContext.request.contextPath}/assets/img/create1.png" >
								      	</a>
								    </div> 
								  </div>
								<div class="form-group row">
								<label class="col-sm-3 col-form-label"><b><i>View Deployed Batches</i></b></label>
								</div>
							
								  <div class="row text-center text-lg-left">
								     <div class="thumbnail col-lg-3 col-md-4 col-xs-6">
								      	<a class="d-block mb-4 h-100" href="/scheduler/CreateBatchSequence">
								      		<img class="zoom img-fluid img-thumbnail" src="${pageContext.request.contextPath}/assets/img/batchseq.png" >
								      	</a>
								    </div> 
								   
									 <div class="thumbnail col-lg-3 col-md-4 col-xs-6">
								      	<a class="d-block mb-4 h-100" href="/scheduler/viewAllJobs">
								      		<img class="zoom img-fluid img-thumbnail" src="${pageContext.request.contextPath}/assets/img/feedsch5.png" >
								      	</a>
								    </div>
								    <div class="thumbnail col-lg-3 col-md-4 col-xs-6">
								      	<a class="d-block mb-4 h-100" href="/scheduler/scheduledjobs">
								      		<img class="zoom img-fluid img-thumbnail" src="${pageContext.request.contextPath}/assets/img/feedprog.png">
								      	</a> 
								    </div>
						
								    
								  </div>
								</div>
					</div>
				</div>
				
		
			
		
		<jsp:include page="cdg_footer.jsp" />