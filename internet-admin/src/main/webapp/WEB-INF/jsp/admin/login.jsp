<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../common/common_login.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/login.css" />


</head>
<body>
	<div class="center">
		<h1>
			<i class="ace-icon fa fa-lefa green"></i>
			<span class="red">d7kj</span>
			<span class="white" id="id-text2">d7kj</span>
		</h1>
		<h4 class="blue" id="id-company-text">&copy;d7kj</h4>
	</div>
	
	<div class="space-6"></div>
	<div class="widget-main">
		<h4 class="header blue lighter bigger">
			<i class="ace-icon fa fa-coffee green"></i>
			请输入您的账号信息
		</h4>
		
		<div class="space-6"></div>
		
		<form action="">
			<fieldset>
				<label class="block clearfix">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control" placeholder="用户名" name="username" value="admin" />
						<i class="ace-icon fa fa-user"></i>
					</span>
				</label>
				<label class="block clearfix">
					<span class="block input-icon input-icon-right">
						<input type="password" class="form-control" placeholder="密码" name="password" value="admin" />
						<i class="ace-icon fa fa-lock"></i>
					</span>
				</label>
				<label class="block">
					<input type="password" class="form-control" placeholder="验证码" name="checkcode" />
					<span><img alt="验证码" src="drawCheckCode" onclick="reCheckcode(this)"/></span>
				</label>
			</fieldset>
		</form>
	</div>
	<script type="text/javascript">
		function reCheckcode(img){
			img.src="drawCheckCode?" + Math.random();
		}
	</script>
</body>
</html>