package com.example.form.logs;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class CustomLogs {
	
	/**
	 * This method is to keep a log for application
	 * 
	 * @param logging is to pass name of class
	 * @param level type of level
	 * @param message log message
	 */
	public void demoLog(Logger logging,Level level,String message){
		LogManager logManager = LogManager.getLogManager();
		Logger lg =  logManager.getLogger(logging.getName());
		lg.log(level, message);
	}
}
