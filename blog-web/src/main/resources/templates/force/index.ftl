<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<#include "basePath.ftl"/>
		<title>首页</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
	</head>
	<body>
		<!--<header>
			<div class="container-fluid">
				<span class="pull-left">欢迎光临石廷春个人博客 !</span>
				<span class="pull-right">QQ:196655494</span>
			</div>
		</header>-->
		<nav class="navbar navbar-inverse navbar-fixed-top my-navbar">
			<div class="container-fluid">
				<div class="navbar-header">
					<a href="index.html" class="navbar-brand logo">
						<img src="force/img/logo.png" alt="我的博客"/>
					</a>
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button> 
				</div>
				<div class="collapse navbar-collapse" id="navbar-collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="index.html">首页</a></li>						
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">前端 <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="category.html">HTML5</a></li>
								<li class="divider" role="separator"></li>
								<li><a href="category.html">JavaScript</a></li>
								<li class="divider" role="separator"></li>
								<li><a href="category.html">网页设计</a></li>									
							</ul>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Java <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">Spring</a></li>
								<li class="divider" role="separator"></li>									
								<li><a href="#">Struts2</a></li>								
								<li class="divider" role="separator"></li>
								<li><a href="#">Hibernate</a></li>								
								<li class="divider" role="separator"></li>
								<li><a href="#">Spring MVC</a></li>
								<li class="divider" role="separator"></li>
								<li><a href="#">Spring Boot</a></li>
								<li class="divider" role="separator"></li>
								<li><a href="#">Spring Data JPA</a></li>
							</ul>
						</li>	
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">.NET<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">Asp.net</a></li>
								<li class="divider" role="separator"></li>									
								<li><a href="#">Asp.net MVC</a></li>								
								<li class="divider" role="separator"></li>
								<li><a href="#">Entity Framework </a></li>								
							</ul>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">数据库<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">MySQL</a></li>
								<li class="divider" role="separator"></li>
								<li><a href="#">SQL Server</a></li>
							</ul>
						</li>						
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">操作系统 <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">Windows</a></li>
								<li class="divider" role="separator"></li>
								<li><a href="#">Linux</a></li>
								<li class="divider" role="separator"></li>
								<li><a href="#">Mac</a></li>								
							</ul>
						</li>	
						<li><a href="index.html">其它类</a></li>
						<li><a href="index.html">关于我</a></li>						
					</ul>
					<form action="" class="navbar-form navbar-right navbar-form-top" role="search">
						<div class="input-group">
							<input type="text" class="form-control input-sm" placeholder="站内搜索" />
							<span class="input-group-btn">
								<button type="submit" class="btn btn-sm"><span class="glyphicon glyphicon-search"></span></button>
							</span>
						</div>
					</form>
				</div>
			</div>
		</nav>
		
		<div class="navbar-fixed-height"></div>
		
		<div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="5000">
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>
			<div class="carousel-inner">
				<div class="item active" style="background:#223240">
					<img src="force/img/slide1.png" alt="第一张"/>
				</div>
				<div class="item" style="background:#F5E4DC;"> 
					<img src="force/img/slide2.png" alt="第二张"/>
				</div>	
				<div class="item" style="background:#DE2A2D;">
					<img src="force/img/slide3.png" alt="第三张"/>
				</div>				
			</div>
			<a href="#myCarousel" class="carousel-control left" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left glyphicon-menu-left"></span>
			</a>
			<a href="#myCarousel" class="carousel-control right" data-slide="next">
				<span class="glyphicon glyphicon-chevron-right glyphicon-menu-right"></span>
			</a>			
		</div>
		
		<div id="why">
			<div class="container">
				<h2>「为什么要经常观注本博客」</h2>
				<p id="why-p">丰常的IT技术文章，全面的软硬件技术支持，随时与博主技术交流学习！</p>
				<div class="row">
					<div class="col-md-6 col">
						<div class="media">
							<div class="media-left">
								<a href="#"><img src="force/img/tab1-1.png" class="media-object" alt=""/></a>
							</div>
							<div class="media-body">
								<h4 class="media-heading">博客内容</h4>
								<p class="text-muted">其它：内容零散单一，无完整可运行的示例代码，不能系统的学习！</p>
								<p>我们：内容详细，前后端技术灌连，有可运行的代码和电子书下载！</p>
							</div>
						</div>
					</div>
					<div class="col-md-6 col">
						<div class="media">
							<div class="media-left">
								<a href="#"><img src="force/img/tab1-2.png" class="media-object" alt=""/></a>
							</div>
							<div class="media-body">
								<h4 class="media-heading">项目合作</h4>
								<p class="text-muted">其它：无项目合作往来，无法技术上更进一步的学习交流！</p>
								<p>我们：无论前后端多大多小的项目都可以合作，全栈服务！</p>
							</div>
						</div>
					</div>	
					<div class="col-md-6 col">
						<div class="media">
							<div class="media-left">
								<a href="#"><img src="force/img/tab1-3.png" class="media-object" alt=""/></a>
							</div>
							<div class="media-body">
								<h4 class="media-heading">主机域名</h4>
								<p class="text-muted">其它：开发完的项目找不到合适的主机域名，浪费大量的时间搜索！</p>
								<p>我们：提供高效网站空间、域名、云服务器、翻墙VPN等优惠服务！</p>
							</div>
						</div>
					</div>
					<div class="col-md-6 col">
						<div class="media">
							<div class="media-left">
								<a href="#"><img src="force/img/tab1-4.png" class="media-object" alt=""/></a>
							</div>
							<div class="media-body">
								<h4 class="media-heading">技术支持</h4>
								<p class="text-muted">其它：学习使用过程中遇到问题束手无策，让人知难而退！</p>
								<p>我们：远程技术支持，详细讲解分析案例，适当有偿服务！</p>
							</div>
						</div>
					</div>					
				</div>
			</div>
		</div>
		
		<div id="study">
			<div class="container">
				<div class="row">
					<div class="col-sm-6 index-image">
						<img src="force/img/tab2.png" class="img-responsive center-block auto" alt="" />
					</div>
					<div class="col-sm-6 index-text">
						<h3>强大的学习体系</h3>
						<p>经过博主学习实践经验总结、让您学习少走弯路。</p>
					</div>
				</div>
			</div>
		</div>
		<div id="manager">
			<div class="container">
				<div class="row">
					<div class="col-sm-6">
						<img src="force/img/tab3.png" class="img-responsive center-block auto" alt="" />
					</div>
					<div class="col-sm-6 index-text">
						<h3>全面的技术支持</h3>
						<p>JavaEE、.NET、服务器、空间域名、VPN、IT硬件。</p>						
					</div>
				</div>
			</div>
		</div>
		
		<footer>
			<div class="container">
				<p>本站共 998 次访问</p>
				<p>&copy; 2016-2016 滔天蟹博客, All Rights Reserved</p>				
			</div>
		</footer>
		<script type="text/javascript" src="force/js/rem.min.js"></script>
		<script type="text/javascript" src="force/js/jquery.placeholder.min.js" ></script>
		<script type="text/javascript" src="force/js/myJs.js" ></script>
	</body>
</html>