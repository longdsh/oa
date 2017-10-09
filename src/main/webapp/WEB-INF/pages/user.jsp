<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户页面</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<link rel="stylesheet" href="${APP_PATH }/css/bootstrap.css" />
</head>
<body>
	<!--
        	作者：offline
        	时间：2017-10-09
        	描述：用户信息
        -->
	<div class="row">
		<div class="col-md-6">
			<table class="table table-striped" id="user_info">
				<tbody>
					<th id="user_id">学号：</th>
					<th id=user_class>班级：</th>
					<th id="user_name">姓名：</th>
					<th>
						<button id="up_user_info" class="btn btn-primary">
							<span class="glyphicon glyphicon-pencil" aria-hidden="true">修改</span>
						</button>
					</th>
					<th>
						<a href="${pageContext.request.contextPath}/logout">注销</a>
					</th>
				</tbody>
			</table>
		</div>
	</div>

	<!--
        	作者：offline
        	时间：2017-10-09
        	描述：所有部门
        -->
	<div class="row">
		<div class="col-md-6">
			<h3>所有部门</h3>
			<table class="table table-striped table table-hover">
				<tbody id="show_all_dept">

					<th>部门名</th>
					<th>加入</th>
					<!-- ajax填充 -->

				</tbody>

			</table>

		</div>
		<!-- 加入的部门 -->
		<div class="col-md-6">
			<h3>已加入部门</h3>
			<table class="table table-striped table table-hover">
				<tbody id="show_join_dept">
					<th>部门名</th>
					<th>移除</th>
					<!-- ajax填充 -->
				</tbody>
			</table>
		</div>
	</div>

	<div class="row">
		<div class="col-md-6">
			<nav aria-label="Page navigation">
			<ul class="pagination" id="all_dept_nav">
				<!-- ajax填充 -->
			</ul>
			</nav>
		</div>

		<div class="col-md-6">
			<nav aria-label="Page navigation">
			<ul class="pagination" id="join_dept_nav">
				<!-- ajax填充 -->
			</ul>
			</nav>
		</div>
	</div>
	<script type="text/javascript"
		src="${APP_PATH }/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="${APP_PATH }/js/bootstrap.min.js"></script>
	
	 <script type="text/javascript">
	//刷新页面
	$(function(){
	    f5_all_info(1);
	    f5_join_info(1);
	});
    //刷新所有部门信息页面
	function f5_all_info(allDeptPageNum) {
		
		$.ajax({
			type:"post",
			url:"${APP_PATH}/user/f5All",
			data:"allDeptPageNum="+allDeptPageNum,
			success:function(result){
				f5_user_info(result);
				f5_all_dept(result);
				f5_all_nav(result);
			}
		});
			
	}
    //刷新加入部门页面
	function f5_join_info(joinDeptPageNum){
		
	}
	//刷新用户信息
	function f5_user_info(result){
		$("#user_id").append(result.extend.user.userId);
		$("#user_class").append(result.extend.user.userClass);
		$("#user_name").append(result.extend.user.name);
	}
	//刷新所有部门信息
	function f5_all_dept(result){
		
	}
	//属性所有部门页码
	function f5_all_nav(result){
		
	}
	</script> 
</body>
</html>