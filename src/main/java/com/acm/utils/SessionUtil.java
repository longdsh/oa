package com.acm.utils;
/**
* @author 计算机网络软件应用1501 路素飞
* QQ 2512977541
* @version 创建时间：2017年10月4日 下午12:46:59
* 类说明
*/

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class SessionUtil {
    public static Session getSession() {
    	Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        return session;
    }
}
