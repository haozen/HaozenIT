<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>p2p平台首页</title>
	<link href="/static/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" type="text/css">	
    </head>
  <body>
  
  <nav class="navbar navbar-default navbar-static-top" role="navigation">
	  <div class="container">
	  	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		    <ul class="nav navbar-nav navbar-right">
	       		<li><a href="#">登陆</a></li>
	       		<li><a href="/register/index">注册</a></li>
	      	</ul>
	    </div>
	  </div>
  </nav>
  <div class="container">
	  <form class="form-horizontal" role="form" action="/register/index" method="post">
		  <div class="form-group">
		    <label for="inputUserName3" class="col-sm-2 control-label">用户名</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="inputUserName3" placeholder="UserName" name="username">
		    </div>
		  </div>
  		 <div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
		    <div class="col-sm-4">
		      <input type="password" class="form-control" id="inputPassword3" placeholder="Password" name="password">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputRePassword3" class="col-sm-2 control-label">确认密码</label>
		    <div class="col-sm-4">
		      <input type="password" class="form-control" id="inputRePassword3" placeholder="RePassword" name="repassword" >
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
		    <div class="col-sm-4">
		      <input type="email" class="form-control" id="inputEmail3" placeholder="Email" name="email">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputTel3" class="col-sm-2 control-label">电话</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="inputTel3" placeholder="Tel" name="tel">
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-default">注册</button>
		    </div>
		  </div>
		</form>
	  
	  	  	  	  		
  </div>
     
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="/static/bootstrap/3.3.0/js/bootstrap.min.js"></script>
  </body>
</html>