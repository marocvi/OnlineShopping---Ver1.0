package com.hai.util;
/**
 * Encrypt entered string using different encryption technologies
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
public interface EncryptUtil {

	/**
	 * This method to encrypt input using MD5
	 * @param input The input enter by user
	 * @return The encrypted result.
	 */
	public String encryptMD5(String input);
}
