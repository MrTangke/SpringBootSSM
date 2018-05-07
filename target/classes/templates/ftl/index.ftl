<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"></meta>
<meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
<title>Insert title here</title>
<link rel="stylesheet" href="${basePath}static/bootstrap/css/bootstrap.min.css"></link>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
		  <div class="col-md-12 col-xs-12"> <center> <h1>  Hello Spring Boot ! <div class="col-xs-12"><small>你将开启Spring Boot之旅</small></div> </h1> </center> </div>
		</div>
		<div class="row">
		  <div class="col-xs-12 col-md-12">
		  	<table class="table table-hover">
		  		<div class="col-xs-12 col-md-12">
		  			<center>
			  			<form action="${basePath}food/selectFoodList" method="post" id="sub">
							<input type="hidden" name="currentPage" id="cur"/>
							美食搜索：<input type="text" name="foodName" value="${foodName!""}" onblur="fenye()"/>
							<button type="button" class="btn btn-primary btn-xs" onclick="toHtml()">生成去往HTML</button>
						</form>
					</center>
		  		</div>
				<div class="row">
				  <tr>
					  <div class="col-xs-6 col-md-4"><th>美食序号</th></div>
					  <div class="col-xs-6 col-md-4"><th>美食名称</th></div>
					  <div class="col-xs-6 col-md-4"><th>美食价格</th></div>
					  <div class="col-xs-6 col-md-4"><th>美食简介</th></div>
				  </tr>
				</div>
				<div class="row">
				  <#list list as item>
					  <tr>
						  <div class="col-xs-6 col-md-4"><td>${item.foodId}</td></div>
						  <div class="col-xs-6 col-md-4"><td>${item.foodName}</td></div>
						  <div class="col-xs-6 col-md-4"><td>${item.foodPrice}(RMB)</td></div>
						  <div class="col-xs-6 col-md-4"><td>${item.foodMessage}</td></div>
					  </tr>
				  </#list>
				</div>
				<div class="row">
				  <tr>
					  <div class="col-xs-12 col-md-12"><th colspan="10"><center>${page.page}</center></th></div>
				  </tr>
				</div>
			</table>
		  </div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${basePath}static/bootstrap/js/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">

	//分页模糊
	function fenye(page){
		$("#cur").val(page);
		$("#sub").submit();
	}
	
	function toHtml(){
		location.href="${basePath}food/toCreateHtml"
	}
</script>
</html>