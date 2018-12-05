package com.hai.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtilImpl implements EncryptUtil{

	@Override
	public String encryptMD5(String input) {
		byte[] defaultBytes = input.getBytes();
		   try {
		       MessageDigest algorithm = MessageDigest.getInstance("MD5");
		       algorithm.reset();
		       algorithm.update(defaultBytes);
		       byte messageDigest[] = algorithm.digest();
		       StringBuffer hexString = new StringBuffer();
		       for (int i = 0; i < messageDigest.length; i++) {
		          String hex = Integer.toHexString(0xFF & messageDigest[i]);
		          if (hex.length() == 1) {
		              hexString.append('0');
		          }   
		          hexString.append(hex);
		      }
		      input = hexString + "";
		   } catch (NoSuchAlgorithmException e) {
		      e.printStackTrace();
		   }
		return input;
	}
	public static void main(String[] args) {
		EncryptUtilImpl encrypt = new EncryptUtilImpl();
		
		System.out.println(encrypt.encryptMD5(""));
	}

}
