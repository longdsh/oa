// 刷新所有部门页码
/**
 * 页码分页及分页信息
 * 
 * 通用页码分页 传入页码数据 
 *  ui的id 带#号 方便li加入ui 页码id 带#号
 *  
 *  f5_nav(result.extend.allPageDept,
					"#all_dept_nav", "#all_dept_page_info");
 *  
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


/*废弃
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
