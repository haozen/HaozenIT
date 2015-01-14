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
  
  <nav class="navbar navbar-default navbar-static-top" >
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
  		  	
	  <div class="platform">
	  	<form class="navbar-form navbar-left"  action="/platform/platurl" method="post">
		  		<input class="form-control" type="text" name="platformname" placeholder="如:沪琛投资" >
			  	<input class="form-control" type="text" name="platurl" placeholder="如:http://360cb.net/"/> 	
			  	<button type="submit" class="btn btn-default">添加</button>
		  </form>		  
	  </div>	  	  	  	  		
  </div>
     
  <script src="/static/jquery/jquery-1.8.3.min.js"></script>
  <script src="/static/bootstrap/3.3.0/js/bootstrap.min.js"></script>
  <script type="text/javascript">
  $(function(){
	 
  });
  </script>
  </body>
</html>