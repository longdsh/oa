package com.acm.test;

import static org.junit.Assert.*;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

/**
* @author 计算机网络软件应用1501 路素飞
* QQ 2512977541
* @version 创建时间：2017年10月2日 下午4:01:06
* 类说明
*/
public class Md5Test {

	@Test
	public void test() {
		String hashAlgorithmName = "MD5";
		Object credentials = "lusufei";
		//盐值为学号
		Object salt = ByteSource.Util.bytes("acm");;
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, 1024);
		System.out.println(result);
	}

}
