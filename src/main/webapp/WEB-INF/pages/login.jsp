<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  -->
<title>Login and Registration Form with HTML5 and CSS3</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Login and Registration Form with HTML5 and CSS3" />
<meta name="keywords"
	content="html5, css3, form, switch, animation, :target, pseudo-class" />
<meta name="author" content="Codrops" />
<link rel="shortcut icon" href="../../favicon.ico">
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="../css/animate-custom.css" />
<title>登录</title>
</head>
<body>
	<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6 lt8"> <![endif]-->
	<!--[if IE 7 ]>    <html lang="en" class="no-js ie7 lt8"> <![endif]-->
	<!--[if IE 8 ]>    <html lang="en" class="no-js ie8 lt8"> <![endif]-->
	<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
	<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<body>
	<div class="container">

		<div style="text-align: center; clear: both;">
			<script src="/gg_bd_ad_720x90.js" type="text/javascript"></script>
			<script src="/follow.js" type="text/javascript"></script>
		</div>
		<section>
		<div id="container_demo">
			<a class="hiddenanchor" id="toregister"></a> <a class="hiddenanchor"
				id="tologin"></a>
			<div id="wrapper">
				<div id="login" class="animate form">
					<form action="#" autocomplete="on">
						<h1>登录</h1>
						<p>
							<label for="idsignup" class="youid" data-icon="u">长学号</label> <input
								id="idsignup" name="idsignup" required="required" type="text"
								placeholder="长学号-10位" />
						</p>

						<p>
							<label for="usernamesignup" class="uname" data-icon="u">姓名</label>
							<input id="usernamesignup" name="usernamesignup"
								required="required" type="text" placeholder="姓名" />
						</p>
						<p>
							<label for="password" class="youpasswd" data-icon="p"> 密码</label>
							<input id="password" name="密码" required="required" type="密码"
								placeholder="密码" />
						</p>

						<p class="login button">
							<input type="submit" value="登录" />
						</p>
						<p class="change_link">
							没有账号 <a href="#toregister" class="to_register">注册</a>
						</p>
					</form>
				</div>

				<div id="register" class="animate form">
					<form action="#" autocomplete="on">
						<h1>注册</h1>

						<p>
							<label for="classsignup" class="youclass" data-icon="u">
								班级</label> <input id="classsignup" name="classsignup"
								required="required" type="text" placeholder="班级" />
						</p>
						<p>
							<label for="idsignup" class="youid" data-icon="u"> 长学号</label> <input
								id="idsignup" name="idsignup" required="required" type="text"
								placeholder="长学号" />
						</p>

						<p>
							<label for="usernamesignup" class="uname" data-icon="u">姓名</label>
							<input id="usernamesignup" name="usernamesignup"
								required="required" type="text" placeholder="姓名" />
						</p>

						<p>
							<label for="passwordsignup" class="youpasswd" data-icon="p">密码</label>
							<input id="passwordsignup" name="passwordsignup"
								required="required" type="password" placeholder="密码" />
						</p>
						<p>
							<label for="passwordsignup_confirm" class="youpasswd"
								data-icon="p">重复密码</label> <input id="passwordsignup_confirm"
								name="passwordsignup_confirm" required="required"
								type="password" placeholder="密码" />
						</p>
						<p class="signin button">
							<input type="submit" value="注册" />
						</p>
						<p class="change_link">
							已有账号 <a href="#tologin" class="to_register"> 登录 </a>
						</p>
					</form>
				</div>

			</div>
		</div>
		</section>
	</div>
	<script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="../js/login.js"></script>
</body>
</html>
</body>
</html>