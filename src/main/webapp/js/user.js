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
					"#all_dept_nav", "#all_dept_page_info");
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
	$("#show_all_dept").empty();
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
// 刷新所有部门页码
/**
 * 通用页码分页 传入页码数据 
 *  ui的id 方便li加入ui 页码id
 */
function f5_nav(pageInfo,uiId,pageInfoId) {
	$(uiId).empty();
	// 首页和前一页
	var frist_page_li = $("<li></li>").append($("<span></span>").append("首页"))
			;
	var pre_page_li = $("<li></li>").append(
			$("<span></span>").append("&laquo;"));
	// 添加事件
	if (pageInfo.hasPreviousPage == false) {
		frist_page_li.addClass("disabled");
		pre_page_li.addClass("disabled");
		frist_page_li.attr("pageNum", 1);
		// 当前页码减一
		pre_page_li.attr("pageNum", 1);
	} else {
		frist_page_li.attr("pageNum", 1);
		pre_page_li.attr("pageNum", pageInfo.pageNum - 1);
	}
	// 加入到ui
	frist_page_li.appendTo(uiId);
	pre_page_li.appendTo(uiId);
	// 中间页集合
	var page_num = pageInfo.navigatepageNums;
	// 遍历中间页
	$.each(page_num, function(index, item) {
		var page_li = $("<li></li>").append($("<span></span>").append(item));
		// 添加页码信息
		page_li.attr("pageNum",item);
		// 为当前页高亮显示
		if (item == pageInfo.pageNum) {
			page_li.addClass("active");
		}
		page_li.appendTo(uiId);
	})

	var last_page_li = $("<li></li>").append($("<span></span>").append("末页"));
	var next_page_li = $("<li></li>").append(
			$("<span></span>").append("&raquo;"));
	// 添加事件
	if (pageInfo.hasNextPage == false) {

		last_page_li.addClass("disabled");
		next_page_li.addClass("disabled");
		next_page_li.attr("pageNum", pageInfo.pageNum + 1);
		last_page_li.attr("pageNum", pageInfo.pages);
	} else {
		next_page_li.attr("pageNum", pageInfo.pageNum + 1);
		last_page_li.attr("pageNum", pageInfo.pages);
	}

	// 加入到ui
	next_page_li.appendTo(uiId);
	last_page_li.appendTo(uiId);

	var pages = pageInfo.pages;
	var size = pageInfo.total;

	$(pageInfoId).empty();
	$(pageInfoId).append(
			$("<span></span>").append("共").append(
					$("<kbd></kbd>").append(pages)).append("页   ")).append(
			$("<span></span>").append(" ")
					.append($("<kbd></kbd>").append(size)).append("条数据"));

}

$("#all_dept_nav").on("click","li",function(){
	//alert(111);
	var pageNum = $(this).attr("pageNum");
	//alert(pageNum);
	f5_all_info(pageNum);
});
/*
 * function f5_all_nav(result,liClass,ulClass) { $("#ulClass").empty(); //
 * 首页和前一页 var frist_page_li = $("<li></li>").append($("<span></span>").append("首页"));
 * var pre_page_li = $("<li></li>").append( $("<span></span>").append("&laquo;")); //
 * 添加事件 if (result.extend.allPageDept.hasPreviousPage) {
 * frist_page_li.click(function() { f5_all_info(1); });
 * pre_page_li.click(function() { f5_all_info(result.extend.allPageDept.pageNum -
 * 1); }) } else { // 如果当前页为第一页不能点 frist_page_li.addClass("disabled");
 * pre_page_li.addClass("disabled"); } // 加入到ui
 * frist_page_li.appendTo("#all_dept_nav");
 * pre_page_li.appendTo("#all_dept_nav"); var page_num =
 * result.extend.allPageDept.navigatepageNums; // 遍历中间页 $.each(page_num,
 * function(index, item) { var page_li = $("<li></li>").append($("<span></span>").append(item));
 * page_li.click(function() { f5_all_info(item); }) // 为当前页高亮显示 if (item ==
 * result.extend.allPageDept.pageNum) { page_li.addClass("active"); }
 * page_li.appendTo("#all_dept_nav"); })
 * 
 * var last_page_li = $("<li></li>").append($("<span></span>").append("末页"));
 * var next_page_li = $("<li></li>").append( $("<span></span>").append("&raquo;")); //
 * 添加事件 if (result.extend.allPageDept.hasNextPage) {
 * last_page_li.click(function() { f5_all_info(result.extend.allPageDept.pages);
 * }); next_page_li.click(function() {
 * f5_all_info(result.extend.allPageDept.pageNum + 1); }) } else { //
 * 如果当前页为第一页不能点 last_page_li.addClass("disabled");
 * next_page_li.addClass("disabled"); }
 *  // 加入到ui next_page_li.appendTo("#all_dept_nav");
 * last_page_li.appendTo("#all_dept_nav");
 * 
 * var pages = result.extend.allPageDept.pages; var size =
 * result.extend.allPageDept.size;
 * 
 * $("#all_dept_page_info").empty(); $("#all_dept_page_info").append( $("<span></span>").append("共").append(
 * $("<kbd></kbd>").append(pages)) .append("页 ")) .append( $("<span></span>").append("
 * ").append( $("<kbd></kbd>").append(size)) .append("条数据"));
 *  }
 */

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
