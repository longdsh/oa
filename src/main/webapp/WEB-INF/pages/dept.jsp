<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门管理页面</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<link rel="stylesheet" href="${APP_PATH}/css/bootstrap.css" />
</head>
<body>
	<div class="row">
		<div class="col-md-6">
			<table class="table table-striped" id="dept_info">
				<tbody>
					<th id="dept_name"></th>
					
					<th>
						<button id="up_dept_info" class="btn btn-primary">
							<span class="glyphicon glyphicon-pencil" aria-hidden="true">修改</span>
						</button>
					</th>
					
					<th id="is_recruit">
						<button id="is_recruit_up" class="btn btn-primary">
							<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
						</button>
					</th>
					
					<th><a href="${pageContext.request.contextPath}/logout">注销</a>
					</th>
				</tbody>
			</table>
		</div>
	</div>
	<!-- 修改模态框 -->
	<div class="modal fade" id="updata_user_info_modal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改管理员信息</h4>
				</div>
				<div class="modal-body">
					<form id="user_info">
					    <input type="hidden" id="dept_key" name="id">
						
						<div class="form-group">
							<label>管理员名</label> 
							<input
								type="text" class="form-control" placeholder="管理员名" name="name" id="name">
						</div>
						
						<div class="form-group">
							<label>密码</label> 
							<input
								type="password" class="form-control" placeholder="密码" id="password" name="password">
						</div>
						
					</form>
					<div class="form-group">
							<label>重复密码</label> 
							<input
								type="password" class="form-control" placeholder="重复密码" id="password1">
					</div>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						id="updata_user_info_modal_submit">保存</button>
					
				</div>
			</div>
		</div>
	</div>
	



	<div class="row">
		<div class="col-md-12">
			<h3>部门内所有人</h3>
			<div class="row">
				<form id="find_all_user">
					<!-- 模糊查询 -->
					<div class="col-md-3">
						<input type="text" class="form-control" name="userId"
							placeholder="学号">
					</div>
					<div class="col-md-3">
						<input type="text" class="form-control" name="userClass"
							placeholder="班级">
					</div>
					<div class="col-md-3">
						<input type="text" class="form-control" name="userName"
							placeholder="姓名">
					</div>
					<div class="col-md-3">
						<input type="text" class="form-control" name="userPhone"
							placeholder="手机号">
					</div>	
				</form>
				<button id="to_excel" class="btn btn-primary">
							<span class="glyphicon glyphicon-pencil" aria-hidden="true">导出数据</span>
			    </button>
				<!-- 页面具体信息 页数  数据条数-->
				<div class="col-md-4" id="all_user_page_info">
					<!-- ajax填充 -->
				</div>


			</div>
			<!-- 部门信息 -->
			<div class="row">
				<div class="col-md-12">
					<table class="table table-striped table-hover">

						<th>学号</th>
						<th>班级</th>
						<th>姓名</th>
						<th>手机号</th>
						<th>移除</th>
						<tbody id="show_all_user">


							<!-- ajax填充 -->

						</tbody>

					</table>
				</div>
			</div>

			<!-- 页码 -->
			<div class="row">

				<div class="col-md-12">
					<nav aria-label="Page navigation">
					<ul class="pagination" id="all_user_nav">
						<!-- ajax填充 -->
					</ul>
					</nav>
				</div>
			</div>

		</div>
		<script type="text/javascript" src="${APP_PATH}/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="${APP_PATH}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${APP_PATH}/js/nav.js"></script>
	<script type="text/javascript" src="${APP_PATH}/js/dept.js"></script>
</body>
</html>