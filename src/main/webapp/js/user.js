$(function() {
	f5_all_info(1);
	f5_join_info(1);
});
// 刷新所有部门信息页面
function f5_all_info(allDeptPageNum) {

	$.ajax({
		type : "post",
		url : getRootPath() + "/user/f5All",
		data : {
			"allDeptPageNum" : allDeptPageNum,
			"deptName" : $("#find_all_dept").val()
		},
		success : function(result) {
			f5_user_info(result);
			f5_all_dept(result);
			f5_nav(result.extend.allPageDept,
					"#all_dept_nav", 
					"#all_dept_page_info");//此方法在nav.js中
		}
	});

}
// 刷新加入部门页面
function f5_join_info(joinDeptPageNum) {

}
// 刷新用户信息
function f5_user_info(result) {
	$("#user_key").attr("value", result.extend.user.id)
	$("#userId").attr("value", result.extend.user.userId)
	$("#userClass").attr("value", result.extend.user.userClass)
	$("#name").attr("value", result.extend.user.name)
	$("#phone").attr("value",result.extend.user.phone);
	
	$("#user_id").empty();
	$("#user_class").empty();
	$("#user_name").empty();
	$("#user_id").append("学号：" + result.extend.user.userId);
	$("#user_class").append("班级：" + result.extend.user.userClass);
	$("#user_name").append("姓名：" + result.extend.user.name);
}
// 刷新所有部门信息
function f5_all_dept(result) {
	$("#show_all_dept").empty()
	var depts = result.extend.allPageDept.list;
	$.each(depts, function(index, item) {
		// 加入部门名称
		var tr = $("<tr></tr>")
		var td_dept_name = $("<td></td>").append(item.name);

		// 加入部门添加按钮
		var td_add_dept = $("<td></td>");
		var add_dept_button = $("<button></button>").addClass("btn").addClass(
				"btn-sm");
		if (item.isRecruit == 1) {
			add_dept_button.addClass("btn-primary").append("报名");
		} else {
			add_dept_button.addClass("btn-danger");
			add_dept_button.attr("disabled", "disabled").append("已满");
		}
		add_dept_button.click(function() {
			add_to_dept(result);
		})
		td_add_dept.append(add_dept_button);

		tr.append(td_dept_name).append(td_add_dept).appendTo("#show_all_dept");

	})

}

$("#all_dept_nav").on("click","li",function(){
	//alert(111);
	var pageNum = $(this).attr("pageNum");
	//alert(pageNum);
	f5_all_info(pageNum);
});


function add_to_dept(result) {
	alert("已添加");
}

$("#find_all_dept").keyup(function() {
	f5_all_info(1);
});

$("#up_user_info").click(function() {
	// alert("修改信息")
	$("#updata_user_info_modal").modal();
});

$("#updata_user_info_modal_submit").click(function() {

	$("#updata_user_info_modal").modal('hide');
});

// 得到绝对路径
function getRootPath() {
	// 获取当前网址，如： http://localhost:9527/zdss-web/login/login.do
	var curWwwPath = window.document.location.href;
	// console.log("当前网址：" + curWwwPath);

	// 获取主机地址之后的目录，如：zdss-web/login/login.do
	var pathName = window.document.location.pathname;
	// console.log("当前路径：" + pathName);

	var pos = curWwwPath.indexOf(pathName);
	// console.log("路径位置：" + pos);

	// 获取主机地址，如： http://localhost:9527
	var localhostPath = curWwwPath.substring(0, pos);
	console.log("当前主机地址：" + localhostPath);

	// 获取带"/"的项目名，如：/zdss-web
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	console.log("当前项目名称：" + projectName);
	console.log(localhostPath + projectName);
	return localhostPath + projectName;
}
