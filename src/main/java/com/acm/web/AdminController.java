package com.acm.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @author 计算机网络软件应用1501 路素飞
* QQ 2512977541
* @version 创建时间：2017年10月1日 下午3:42:27
* 类说明
*/
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping("adminFrist")
	public String adminFrist() {
		return "admin";
	}
}
