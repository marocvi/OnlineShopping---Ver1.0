package com.hai.test.util;

import org.junit.Test;

import com.hai.util.EmailUtil;
import com.hai.util.EmailUtil.EmailPurpose;
import com.hai.util.EmailUtilImpl;

public class EmailUtitlTest {
	
	@Test
	public void sendEmailVerification() {
		EmailUtil emailUtil = new EmailUtilImpl();
		emailUtil.setToEmail("kithuathatnhandalat@gmail.com")
		.setFromEmail("marocvi89@gmail.com")
		.setPurpose(EmailPurpose.VERIFICATION)
		.setVerifyID("UD123")
		.send();
	}
}
