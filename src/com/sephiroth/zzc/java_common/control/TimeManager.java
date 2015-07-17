package com.sephiroth.zzc.java_common.control;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeManager {

	public final static SimpleDateFormat DATE_FORMAT=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public final static SimpleDateFormat DATE_FORMAT_FOR_FILE=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	public final static SimpleDateFormat DATE_FORMAT_FOR_NUM=new SimpleDateFormat("yyyyMMddHHmmss");

	public static String getTimeForFile() {
		return DATE_FORMAT_FOR_FILE.format(new Date());
	}
	
	public static String getTimeForNum() {
		return DATE_FORMAT_FOR_NUM.format(new Date());
	}

	public static String getTime() {
		return DATE_FORMAT.format(new Date());
	}

	public static long getUnixTimeStamp() {
		return new Date().getTime();
	}
}
