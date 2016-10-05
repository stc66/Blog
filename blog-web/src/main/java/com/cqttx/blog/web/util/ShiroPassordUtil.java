package com.cqttx.blog.web.util;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;

public class ShiroPassordUtil {

	/**
	 * base64加密
	 * 
	 * @param str
	 * @return
	 */
	public static String encBase64(String str) {
		return Base64.encodeToString(str.getBytes());
	}

	/**
	 * base64解密
	 * 
	 * @param str
	 * @return
	 */
	public static String decBase64(String str) {
		return Base64.decodeToString(str);
	}

	/**
	 * Md5加密
	 * 
	 * @param str
	 * @param salt
	 * @return
	 */
	public static String md5(String str, String salt) {
		return new Md5Hash(str, salt,2).toHex();
	}

	public static void main(String[] args) {
		String password = "123456";
		System.out.println("Base64加密：" + ShiroPassordUtil.encBase64(password));
		System.out.println("Base64解密：" + ShiroPassordUtil.decBase64(ShiroPassordUtil.encBase64(password)));

		System.out.println("Md5加密：" + ShiroPassordUtil.md5(password, "stc"));
		
		DefaultPasswordService dps = new DefaultPasswordService();
		System.out.println(dps.passwordsMatch(password, ShiroPassordUtil.md5(password, "stc")));
	}
}
