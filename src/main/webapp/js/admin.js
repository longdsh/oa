var all_dept_pageNum = 1;//当前页码
$(function() {
	f5_all_info(all_dept_pageNum);
});


function f5_all_info(allDeptPageNum){
	$.ajax({
		type : "post",
		url : getRootPath() + "/admin/f5All",
		data : {
			"allDeptPageNum" : allDeptPageNum,
			"deptName" : $("#find_all_dept").val()
		},
		success : function(result) {
			f5_dept_info(result);
			f5_all_dept(result);
			f5_nav(result.extend.allPageDept, "#all_dept_nav",
			"#all_dept_page_info");// 此方法在nav.js中
		}
	});
}

function f5_dept_info(result) {
	$("#dept_key").attr("value", result.extend.admin.id);
	$("#name").attr("value", result.extend.admin.name);
	$("#dept_name").empty();
	$("#dept_name").append("管理员："+result.extend.admin.name);

}

function f5_all_dept(result) {
	$("#show_all_dept").empty();
	all_dept_pageNum = result.extend.allPageDept.pageNum;
	var depts = result.extend.allPageDept.list;

	$.each(depts, function(index, item) {
		// 加入部门名称
		var tr = $("<tr></tr>");
		var td_dept_name = $("<td></td>").append(item.name);
		
		

		// 加入部门添加按钮
		var td_delete_dept = $("<td></td>");
		var delete_dept_button = $("<button></button>").addClass("btn").addClass(
				"btn-sm");
		if (item.power != 3) {
			td_dept_name.click(function(){
				to_dept_page(item.id);
			});
			
			delete_dept_button.addClass("btn-primary").append("删除部门");
			delete_dept_button.click(function() {
				//传入部门id 和当前页 刷新 加入部门页码
			    delete_dept(item.id,all_dept_pageNum);
			});
		}
		td_delete_dept.append(delete_dept_button);

		tr.append(td_dept_name).append(td_delete_dept).appendTo("#show_all_dept");

	});

}

$("#all_dept_nav").on("click", "li", function() {
	var pageNum = $(this).attr("pageNum");
	f5_all_info(pageNum);
});


function delete_dept(dept_key,allDeptPageNum){
	if(confirm("确认要删除吗？")){
		$.ajax({
			type : "post",
			url : getRootPath() + "/admin/deleteDept",
			data : {"deptKey":dept_key},
			success:function(result){
				if(result.code==100){		
					alert("删除成功");
					f5_all_info(allDeptPageNum);
				}
				else{
					alert("删除失败");
				}
			}
		});
	}
	
	//f5_all_info(allDeptPageNum);
}

function to_dept_page(dept_key){
	//alert(dept_key);
	if(confirm("确认要跳转吗？")){
		$.ajax({
			type : "post",
			url : getRootPath() + "/admin/toDeptPage",
			data : {"deptKey":dept_key},
			success:function(result){
				if(result.code==100){		
					top.location.href=result.extend.path;
				}
				else{
					alert("跳转失败");
				}
			}
		});
	}
	
}

$("#add_dept").click(function(){
	$("#add_dept_info_modal").modal();
});

$("#add_dept_modal").click(function(){
	$.ajax({
		type : "post",
		url : getRootPath() + "/admin/addDept",
		data : $("#add_dept_info_modal form").serialize(),
		success:function(result){
			if(result.code==100){		
				alert("添加成功");
				$("#add_dept_info_modal").modal('hide');
				f5_dept_info(result);
			}
			else{
				alert("已存在");
			}
		}
	});
});

//模态框修改
$("#up_dept_info").click(function(){
	$("#updata_user_info_modal").modal();
});

$("#updata_user_info_modal_submit").click(function(){
	if($("#password").val().length<1){
		alert("密码长度不能小于1");
	}
	if($("#password").val()!=$("#password1").val()){
		alert("两次密码不同");
	}
	//alert("已修改")
	$.ajax({
		type : "post",
		url : getRootPath() + "/admin/upAdminInfo",
		data : $("#updata_user_info_modal form").serialize(),
		success:function(result){
			if(result.code==100){		
				alert("修改成功");
				f5_dept_info(result);
				$("#updata_user_info_modal").modal('hide');
			}
			else{
				alert("已存在");
			}
		}
	});
})
//



$("#find_all_dept").keyup(function() {
	f5_all_info(all_dept_pageNum);
});

/**
 * 跳转到user页面
 */
$("#to_user_page").change(function(){
	//alert($(this).val())
	$.ajax({
		type : "post",
		url : getRootPath() + "/admin/toUserPage",
		data : {
			"userId":$(this).val()
		},
		success:function(result){
			if(result.code==100){
				top.location.href=result.extend.path;
			}
			else{
				alert("无此学号");
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
