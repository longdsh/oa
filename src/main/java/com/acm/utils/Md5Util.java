package com.acm.utils;
/**
* @author 计算机网络软件应用1501 路素飞
* QQ 2512977541
* @version 创建时间：2017年10月2日 上午9:55:07
* 类说明
*/

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.acm.entity.User;

public class Md5Util {
	
	public static User md5User(User user) {
		System.out.println(user);
		String hashAlgorithmName = "MD5";
		Object credentials = user.getPassword();
		//盐值为学号
		Object salt = ByteSource.Util.bytes(user.getUserId());;
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, 1024);
		System.out.println(result);
		user.setPassword(result.toString());
		System.out.println(user.getPassword());
		user.setPower(1);
		user.setImg(null);
		return user;
		
	}

}
