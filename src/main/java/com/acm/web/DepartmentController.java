package com.acm.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.management.relation.Relation;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acm.entity.Department;
import com.acm.entity.Message;
import com.acm.entity.User;
import com.acm.service.impl.DepartmentServiceImpl;
import com.acm.service.impl.UserDeptServiceImpl;
import com.acm.service.impl.UserServiceImpl;
import com.acm.utils.Md5Util;
import com.acm.utils.SessionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuxueli.poi.excel.ExcelExportUtil;

@Controller
@RequestMapping("/dept")
public class DepartmentController {

	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	DepartmentServiceImpl departmentServiceImpl;

	@Autowired
	UserDeptServiceImpl userDeptServiceImpl;

	List<User> allUsers = null;

	PageInfo<User> allPageUser = null;

	Department dept = null;

	@RequestMapping("deptFrist")
	public String deptFrist() {
		if (SessionUtil.getSession().getAttribute("dept") == null) {
			dept = (Department) SessionUtil.getSession().getAttribute(
					"userOrDept");
		} else {
			dept = (Department) SessionUtil.getSession().getAttribute("dept");
		}
		System.out.println(dept);
		return "dept";
	}
	
	@ResponseBody
	@RequestMapping(value = "isRecruitUp")
	public Message isRecruitUp(){
		if(dept.getIsRecruit()==0){
			dept.setIsRecruit(1);
		}else{
			dept.setIsRecruit(0);
		}
		departmentServiceImpl.updateDeparmentByDept(dept);
		SessionUtil.getSession().setAttribute("dept", dept);
		return Message.success().add("dept", dept);
	}
	
	@ResponseBody
	@RequestMapping(value = "upDeptInfo")
	public Message upDeptInfo(Department department){
		System.out.println("upDeptInfo:"+department);
		//不是原有名 并且数据库中已存在
		if(department.getName()!=dept.getName()&&departmentServiceImpl.countDeptByName(department.getName())!=0){
			return Message.fail();
		}
		department.setId(dept.getId());
		department = Md5Util.md5Dept(department);
		departmentServiceImpl.updateDeparmentByDept(department);
		this.dept = department;
		SessionUtil.getSession().setAttribute("dept", department);
		return Message.success().add("dept", department);
	}
	
	/**
	 * 导出文件
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "toExcel")
	public Message toExcel(HttpServletRequest request) {
		String realPath = request.getSession().getServletContext().getRealPath("/userFile");
		
		File file = new File(realPath);
		if (!file.exists()) {
			file.mkdir();
		}
		String filePath = realPath + "\\"+this.dept.getName() + ".xls";
		System.out.println(file.getPath());
		System.out.println(filePath);
		List<User> users = userServiceImpl.findUserByDeptIdAndUser(
				this.dept.getId(), null);
		ExcelExportUtil.exportToFile(users, filePath);
		return Message.success().add("filePath", filePath);
	}
    /**
     * 部门移除用户
     * @param userKey
     * @return
     */
	@ResponseBody
	@RequestMapping(value = "deptRemoveUser")
	public Message deptRemoveUser(Integer userKey) {
		userDeptServiceImpl.deleteContact(userKey, this.dept.getId());
		return Message.success();
	}

	@ResponseBody
	@RequestMapping(value = "f5User")
	public Message f5User(Integer allUserPageNum, String userId,String userClass,String name,String phone) {
		//System.out.println("f5User:"+user);
		User user = new User(userId, userClass, name, phone, null, null, null);
		allPageUser = getAllUser(allUserPageNum, user);

		return Message.success().add("dept", dept)
				.add("allPageUser", allPageUser);
	}

	/*****************************************************************************************/
	private PageInfo<User> getAllUser(Integer allUserPageNum, User user) {
		// TODO Auto-generated method stub
		PageHelper.startPage(allUserPageNum, 10);
		allUsers = userServiceImpl.findUserByDeptIdAndUser(dept.getId(), user);
		PageInfo<User> pageInfo = new PageInfo<>(allUsers);
		return pageInfo;
	}

}
