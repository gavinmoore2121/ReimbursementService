package com.revature.utilities.log4j;

import org.apache.log4j.Level;

/**
 * Custom log4j level with a priority below Trace, allowing messages to the user to be printed out
 * selectively.
 */
public class UserInfoLog4JLevel extends Level {
	private static final long serialVersionUID = -5724276313898173786L;
	
	public static final int USER_INFO_INT = TRACE_INT - 50;
	
	// Note: If this is buggy, try changing the third param to 7.
	public static final Level USERINFO = new UserInfoLog4JLevel(USER_INFO_INT, "UserInfo", 8);
	
	protected UserInfoLog4JLevel(int level, String levelStr, int syslogEquivalent) {
		super(level, levelStr, syslogEquivalent);
	}

}
