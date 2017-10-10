var all_dept_pageNum = 1;//当前页码
var join_dept_pageNum = 1;
$(function() {
	f5_all_info(all_dept_pageNum);
	f5_join_info(join_dept_pageNum);
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
			f5_nav(result.extend.allPageDept, "#all_dept_nav",
					"#all_dept_page_info");// 此方法在nav.js中
		}
	});

}

//刷新加入部门页面
function f5_join_info(joinDeptPageNum) {
	$.ajax({
		type : "post",
		url : getRootPath() + "/user/f5Join",
		data : {
			"joinDeptPageNum" : joinDeptPageNum,
			"deptName" : $("#find_join_dept").val()
		},
		success : function(result) {
			f5_join_dept(result);
			f5_nav(result.extend.joinPageDept,"#join_dept_nav",
					"#join_dept_page_info");// 此方法在nav.js中
		}
	});
}

// 刷新用户信息
function f5_user_info(result) {
	$("#user_key").attr("value", result.extend.user.id);
	$("#userId").attr("value", result.extend.user.userId);
	$("#userClass").attr("value", result.extend.user.userClass);
	$("#name").attr("value", result.extend.user.name);
	$("#phone").attr("value", result.extend.user.phone);

	$("#user_id").empty();
	$("#user_class").empty();
	$("#user_name").empty();
	$("#user_id").append("学号：" + result.extend.user.userId);
	$("#user_class").append("班级：" + result.extend.user.userClass);
	$("#user_name").append("姓名：" + result.extend.user.name);
}
// 刷新所有部门信息
function f5_all_dept(result) {
	$("#show_all_dept").empty();
	all_dept_pageNum = result.extend.allPageDept.pageNum;
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
			add_dept_button.click(function() {
				//传入部门id 和当前页 刷新 加入部门页码
				add_to_dept(item.id,join_dept_pageNum);
			});
		} else {
			add_dept_button.addClass("btn-danger");
			add_dept_button.attr("disabled", "disabled").append("已满");
		}

		td_add_dept.append(add_dept_button);

		tr.append(td_dept_name).append(td_add_dept).appendTo("#show_all_dept");

	})

}

//刷新加入部门数据
function f5_join_dept(result) {
	$("#show_join_dept").empty();
	join_dept_pageNum = result.extend.joinPageDept.pageNum;
	var depts = result.extend.joinPageDept.list;
	$.each(depts,function(index, item) {
				// 加入部门名称
				var tr = $("<tr></tr>")
				var td_dept_name = $("<td></td>").append(item.name);

				// 加入部门添加按钮
				var td_remove_dept = $("<td></td>");
				var remove_dept_button = $("<button></button>").addClass("btn")
						.addClass("btn-sm");

				remove_dept_button.addClass("btn-danger");
				remove_dept_button.append("移除");
				remove_dept_button.click(function() {
					
					remove_dept(item.id,join_dept_pageNum);
				});

				td_remove_dept.append(remove_dept_button);
				tr.append(td_dept_name).append(td_remove_dept).appendTo(
						"#show_join_dept");

			});
}



/**
 * 绑定页码点击事件
 */
$("#all_dept_nav").on("click", "li", function() {
	// alert(111);
	var pageNum = $(this).attr("pageNum");
	// alert(pageNum);
	f5_all_info(pageNum);
});


$("#join_dept_nav").on("click","li",function(){
	var pageNum = $(this).attr("pageNum");
	f5_join_info(pageNum);
});

/**
 * 添加部门按钮
 * 
 * @param result
 *            pagehelper 数据
 * 
 */
function add_to_dept(deptKey,pageNum) {
	
	$.ajax({
		type : "post",
		url : getRootPath() + "/user/userAddDept",
		data :{"deptKey":deptKey},
		success : function(result) {
			var code = result.code;
			if(code==100){
				f5_join_info(pageNum);
				alert("添加成功");
			}else{
				alert(result.msg);
			}
		}
	});
	//alert("已添加");
}
/**
 * 移除部门按钮
 * 
 * @param result
 * 
 */
function remove_dept(deptKey,pageNum) {
	$.ajax({
		type : "post",
		url : getRootPath() + "/user/userRemoveDept",
		data :{"deptKey":deptKey},
		success : function(result) {
			var code = result.code;
			if(code==100){
				f5_join_info(pageNum);
				alert("删除成功");
			}else{
				alert("操作失败");
			}
		}
	});
}

$("#find_all_dept").keyup(function() {
	f5_all_info(all_dept_pageNum);
});

$("#find_join_dept").keyup(function() {
	f5_join_info(join_dept_pageNum);
});

/**
 * modal
 */
$("#up_user_info").click(function() {
	// alert("修改信息")
	$("#updata_user_info_modal").modal();
});

$("#updata_user_info_modal_submit").click(function() {
	
	var password = $("#password").val();
	var password1 = $("#password1").val();
    if(password.length<1){
    	alert("密码不能为空");
    	return false;
    }
	if(password!=password1){
		alert("两次密码不同");
		return false;
	}
	//此处不明白为何 $("#user_info").serialize() 不行
	var info = $("#updata_user_info_modal form").serialize();
	alert(info);
	$("#updata_user_info_modal").modal('hide');
	$.ajax({
		type : "post",
		url : getRootPath() + "/user/upUserInfo",
		
		data : $("#updata_user_info_modal form").serialize(),
		success : function(result) {
			var code = result.code;
			if(code==100){
				f5_user_info(result);
				alert("修改成功");
			}else{
				alert(result.msg);
			}
		}
	});
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
