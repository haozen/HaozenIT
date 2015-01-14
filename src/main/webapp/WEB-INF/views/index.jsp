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
		  	
		
  <div class="container-fluid">	 
  	  <div class="row">	  
	  	  <div class="col-md-1">
		  </div>
		  <div class="col-md-11">
		  		没有网址请添加<a  href="/platform/platurl">添加网址</a>  
		  </div>
	  </div>  
	  <br>
	  <div class="row">	  
	  	    <div class="col-md-1"></div>
		  	<form action="/platform/search" method="post">
				<div class="col-md-2">
					<input class="form-control" type="text"  name="q_eq_i_city.id" value="1" placeholder="例:上海"  >
				</div>			  		
				<div class="col-md-2">
					<input class="form-control" type="text"  name="q_like_s_platcompanyaddress" value="${q_like_s_platcompanyaddress}" placeholder="例:上海市青浦区"/>
				</div>				  	 	
				<div class="col-md-2">
					<input class="form-control" type="text"  name="q_like_s_platregistertime" value="${q_like_s_platregistertime}" placeholder="例:2014-11-11"/>
				</div>				  	
				<div class="col-md-2">
					<input class="form-control" type="text"  name="q_like_s_platregistermoney" value="${q_like_s_platregistermoney}" placeholder="例:20万"/>
				</div>				  	 	
				<div class="col-md-2">
					<input class="form-control" type="text"  name="q_like_s_platstoremoney" value="${q_like_s_platstoremoney}" placeholder="例:20万"/>
				</div>				  	
				<div class="col-md-1">
					<button type="submit" class="btn btn-default">搜索</button>
				</div>				  	
			 </form>		  
	  </div>  
	  <br>
	  <div class="row">
	  	  <div class="col-md-1">
		  </div>
		  <div class="col-md-8">
			 <div class="row">
				  <div class="col-md-3">          
		              <div class="c-ptmz">
		                  <img src="/static/images/huchentouzi.png" width="20" />沪臣投资<span>&nbsp;&nbsp;<a href="http://360cb.net/" target="_blank">&gt;&gt;</a></span>
		              </div>
		              <div class="c-ptxi"><span class="c-ad">年化收益：</span><span class="c-59">7%~18%</span></div>
		              <div class="c-ptxi"><span class="c-ad">期限范围：</span><span class="c-59">1~12个月</span></div>
		   		  </div>
				  <div class="col-md-3">
				      <div class="c-ptmz">
		                  <img src="" width="20" /><span>PPmoney&nbsp;&nbsp;<a href="">&gt;&gt;</a></span>
		              </div>
		              <div class="c-ptxi"><span class="c-ad">年化收益：</span><span class="c-59">7%~18%</span></div>
		              <div class="c-ptxi"><span class="c-ad">期限范围：</span><span class="c-59">1~12个月</span></div>
				  </div>
				  <div class="col-md-3">
				      <div class="c-ptmz">
		                  <img src="" width="20" /><span>PPmoney&nbsp;&nbsp;<a href="">&gt;&gt;</a></span>
		              </div>
		              <div class="c-ptxi"><span class="c-ad">年化收益：</span><span class="c-59">7%~18%</span></div>
		              <div class="c-ptxi"><span class="c-ad">期限范围：</span><span class="c-59">1~12个月</span></div>
				  </div>
				  <div class="col-md-3">
				      <div class="c-ptmz">
		                  <img src="" width="20" /><span>PPmoney&nbsp;&nbsp;<a href="">&gt;&gt;</a></span>
		              </div>
		              <div class="c-ptxi"><span class="c-ad">年化收益：</span><span class="c-59">7%~18%</span></div>
		              <div class="c-ptxi"><span class="c-ad">期限范围：</span><span class="c-59">1~12个月</span></div>
				  </div> 	  
				</div> 
		  </div>
		  <div class="col-md-3">
		  </div>
	  </div>
	  <br>
	  <div class="row">
	  	  <div class="col-md-1">
		  </div>
		  <div class="col-md-8">
			 <div class="row">
				  <div class="col-md-3">          
		              <div class="c-ptmz">
		                  <img src="" width="20" /><span>PPmoney&nbsp;&nbsp;<a href="">&gt;&gt;</a></span>
		              </div>
		              <div class="c-ptxi"><span class="c-ad">年化收益：</span><span class="c-59">7%~18%</span></div>
		              <div class="c-ptxi"><span class="c-ad">期限范围：</span><span class="c-59">1~12个月</span></div>
		   		  </div>
				  <div class="col-md-3">
				      <div class="c-ptmz">
		                  <img src="" width="20" /><span>PPmoney&nbsp;&nbsp;<a href="">&gt;&gt;</a></span>
		              </div>
		              <div class="c-ptxi"><span class="c-ad">年化收益：</span><span class="c-59">7%~18%</span></div>
		              <div class="c-ptxi"><span class="c-ad">期限范围：</span><span class="c-59">1~12个月</span></div>
				  </div>
				  <div class="col-md-3">
				      <div class="c-ptmz">
		                  <img src="" width="20" /><span>PPmoney&nbsp;&nbsp;<a href="">&gt;&gt;</a></span>
		              </div>
		              <div class="c-ptxi"><span class="c-ad">年化收益：</span><span class="c-59">7%~18%</span></div>
		              <div class="c-ptxi"><span class="c-ad">期限范围：</span><span class="c-59">1~12个月</span></div>
				  </div>
				  <div class="col-md-3">
				      <div class="c-ptmz">
		                  <img src="" width="20" /><span>PPmoney&nbsp;&nbsp;<a href="">&gt;&gt;</a></span>
		              </div>
		              <div class="c-ptxi"><span class="c-ad">年化收益：</span><span class="c-59">7%~18%</span></div>
		              <div class="c-ptxi"><span class="c-ad">期限范围：</span><span class="c-59">1~12个月</span></div>
				  </div> 	  
				</div> 
		  </div>
		  <div class="col-md-3">
		  </div>
	  </div>
	  <br>
	  <div class="row">
	  	  <div class="col-md-1">
		  </div>
		  <div class="col-md-8">
			 <div class="row">
				  <div class="col-md-3">          
		              <div class="c-ptmz">
		                  <img src="" width="20" /><span>PPmoney&nbsp;&nbsp;<a href="">&gt;&gt;</a></span>
		              </div>
		              <div class="c-ptxi"><span class="c-ad">年化收益：</span><span class="c-59">7%~18%</span></div>
		              <div class="c-ptxi"><span class="c-ad">期限范围：</span><span class="c-59">1~12个月</span></div>
		   		  </div>
				  <div class="col-md-3">
				      <div class="c-ptmz">
		                  <img src="" width="20" /><span>PPmoney&nbsp;&nbsp;<a href="">&gt;&gt;</a></span>
		              </div>
		              <div class="c-ptxi"><span class="c-ad">年化收益：</span><span class="c-59">7%~18%</span></div>
		              <div class="c-ptxi"><span class="c-ad">期限范围：</span><span class="c-59">1~12个月</span></div>
				  </div>
				  <div class="col-md-3">
				      <div class="c-ptmz">
		                  <img src="" width="20" /><span>PPmoney&nbsp;&nbsp;<a href="">&gt;&gt;</a></span>
		              </div>
		              <div class="c-ptxi"><span class="c-ad">年化收益：</span><span class="c-59">7%~18%</span></div>
		              <div class="c-ptxi"><span class="c-ad">期限范围：</span><span class="c-59">1~12个月</span></div>
				  </div>
				  <div class="col-md-3">
				      <div class="c-ptmz">
		                  <img src="" width="20" /><span>PPmoney&nbsp;&nbsp;<a href="">&gt;&gt;</a></span>
		              </div>
		              <div class="c-ptxi"><span class="c-ad">年化收益：</span><span class="c-59">7%~18%</span></div>
		              <div class="c-ptxi"><span class="c-ad">期限范围：</span><span class="c-59">1~12个月</span></div>
				  </div> 	  
				</div> 
		  </div>
		  <div class="col-md-3">
		  </div>
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