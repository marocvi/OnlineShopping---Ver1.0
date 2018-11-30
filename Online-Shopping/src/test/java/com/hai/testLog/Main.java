package com.hai.testLog;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Main {

	public static void main(String[] args) {
		Logger log = Logger.getLogger(Main.class.getName());
		log.setLevel(Level.WARN);
		log.warn("hai");
		log.info("info");
	}
}
