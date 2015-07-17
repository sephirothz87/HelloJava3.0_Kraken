package com.sephiroth.zzc.java_common.service;

import java.util.ArrayList;

import com.sephiroth.zzc.java_common.control.FileManager;
import com.sephiroth.zzc.java_common.util.Util;

public class CsvManager {
	final static String LOG_TAG = "CsvManager";
	public final static String CSV_BACKSTR = ".csv";

	public static boolean writeLine(String[] strings, String filePath) {
		String res = "";
		for (String s : strings) {
			res += "\"" + s + "\",";
		}
		// 便于在office中打开，默认使用GBK编码
		if (!FileManager.writeLine(filePath, res, "GBK")) {
			Util.wl(LOG_TAG, "write this line fail :" + res);
			return false;
		}
		return true;
	}

	public static boolean writeGroup(String[][] groups, String filePath) {
		for (String[] strings : groups) {
			if (!writeLine(strings, filePath)) {
				return false;
			}
		}
		return true;
	}

	public static boolean writeLine(ArrayList<String> strings, String filePath) {
		String res = "";
		for (String s : strings) {
			res += "\"" + s + "\",";
		}
		// 便于在office中打开，默认使用GBK编码
		if (!FileManager.writeLine(filePath, res, "GBK")) {
			Util.wl(LOG_TAG, "write this line fail :" + res);
			return false;
		}
		return true;
	}

	public static boolean writeGroup(ArrayList<ArrayList<String>> groups,
			String filePath) {
		for (ArrayList<String> strings : groups) {
			if (!writeLine(strings, filePath)) {
				return false;
			}
		}
		return true;
	}
}
