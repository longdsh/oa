var all_user_pageNum = 1;// 当前页码
$(function() {
	f5_all_info(all_user_pageNum);
});

function f5_all_info(allUserPageNum) {
	//var user = $("#find_all_user form").serialize();
	//alert($("#find_all_user form").serialize());
	
	$.ajax({
		type : "post",
		url : getRootPath() + "/dept/f5User",
		data : {
			"allUserPageNum" : allUserPageNum,
			//知识面短缺
			"userId":$("#userId").val(),
			"userClass":$("#userClass").val(),
			"name":$("#userName").val(),
			"phone":$("#userPhone").val(),
		},
		
		
	
		success : function(result) {
			f5_dept_info(result);
			f5_all_user(result);
			f5_nav(result.extend.allPageUser, "#all_user_nav",
					"#all_user_page_info");// 此方法在nav.js中
		}
	});
}

function f5_dept_info(result) {
	$("#dept_name").empty();
	$("#is_recruit_up span").empty();
	$("#dept_name").append("部门名：" + result.extend.dept.name);
	if (result.extend.dept.isRecruit == 1) {
		$("#is_recruit_up span").append("允许进入");
	} else {
		$("#is_recruit_up span").append("不允许进入");
	}
}

function f5_all_user(result) {
	$("#show_all_user").empty();
	all_user_pageNum = result.extend.allPageUser.pageNum;
	var users = result.extend.allPageUser.list;

	$.each(users, function(index, item) {
		// 加入用户名
		var tr = $("<tr></tr>");

		var td_user_id = $("<td></td>").append(item.userId);
		var td_user_class = $("<td></td>").append(item.userClass);
		var td_user_name = $("<td></td>").append(item.name);
		var td_user_phone = $("<td></td>").append(item.phone);

		// 加入部门添加按钮
		var td_delete_user = $("<td></td>");
		var delete_user_button = $("<button></button>").addClass("btn")
				.addClass("btn-sm").append("remove").appendTo(td_delete_user);

		delete_user_button.click(function(){
			dept_remove_user(item.id);
		});
		tr.append(td_user_id).append(td_user_class).append(td_user_name)
				.append(td_user_phone).append(td_delete_user).appendTo(
						"#show_all_user");

	});
}

$("#all_user_nav").on("click", "li", function() {
	var pageNum = $(this).attr("pageNum");
	f5_all_info(pageNum);
});

/**
 * 部门移除用户
 * @param useKey
 */
function dept_remove_user(userKey) {
   alert("remove");
   $.ajax({
		type : "post",
		url : getRootPath() + "/dept/deptRemoveUser",
		data : {
			"userKey":userKey
		},
		success : function(result) {
			f5_all_info(all_user_pageNum);
		}
	});
}
/**
 * 导出数据
 * @returns
 */
$("#to_excel").click(function(){
	$.ajax({
		type : "post",
		url : getRootPath() + "/dept/toExcel",
		//data :
		success:function(result){
			if(result.code==100){	
				alert("待完善");
				//$("#down").attr("herf",result.extend.filePath);
				//window.location.href="file://"+result.extend.filePath;
				//window.open(result.extend.filePath);
				//alert("在浏览器中输入地址"+result.extend.filePath);
			}
			else{
				alert("下载失败");
			}
		}
	});
});
/**
 * 修改是否允许加入
 */
$("#is_recruit_up").click(function(){
	$.ajax({
		type : "post",
		url : getRootPath() + "/dept/isRecruitUp",
		//data :
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
		url : getRootPath() + "/dept/upDeptInfo",
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

$("#find_all_user input").keyup(function(){
	f5_all_info(all_user_pageNum);
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