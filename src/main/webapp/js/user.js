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

$(function() {
	f5_all_info(1);
	f5_join_info(1);
});
// 刷新所有部门信息页面
function f5_all_info(allDeptPageNum) {

	$.ajax({
		type : "post",
		url : getRootPath() + "/user/f5All",
		data : "allDeptPageNum=" + allDeptPageNum,
		success : function(result) {
			f5_user_info(result);
			f5_all_dept(result);
			f5_all_nav(result);
		}
	});

}
// 刷新加入部门页面
function f5_join_info(joinDeptPageNum) {

}
// 刷新用户信息
function f5_user_info(result) {
	$("#user_id").append(result.extend.user.userId);
	$("#user_class").append(result.extend.user.userClass);
	$("#user_name").append(result.extend.user.name);
}
// 刷新所有部门信息
function f5_all_dept(result) {

}
// 属性所有部门页码
function f5_all_nav(result) {

}