package com.sephiroth.zzc.java_common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Util {
	public final static String COMMON_VERSION_CODE = "3.1.0008";
	public final static String COMMON_VERSION_NAME = "Kraken";
	public final static String COMPONENT_SIGN = "leonzhong";

	public final static String LOG_TAG = "JavaComponent";

	public final static String TEST_PATH_WINDOWS = "E:\\92_TESTPATH\\";
	public final static String TEST_PATH_MAC = "/Users/zhongzhicong/Documents/01_development/debug_path/";

	public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public final static SimpleDateFormat DATE_FORMAT_FOR_FILE = new SimpleDateFormat(
			"yyyy-MM-dd-HH-mm-ss");
	public final static DecimalFormat DECIMAL_FORMAT_DEFAULT = new DecimalFormat("###0.00"); 
	public final static DecimalFormat DECIMAL_FORMAT_PERCENT = new DecimalFormat("##00.00"); 
	public final static DecimalFormat DECIMAL_FORMAT_INTEGER_5 = new DecimalFormat("00000"); 

	// 跨平台解决方案
	public final static String USER_HOME = System.getProperty("user.home");
	public final static String USER_DIR = System.getProperty("user.dir");
	// 临时文件目录
	public final static String TMP_PATH = System.getProperty("java.io.tmpdir");

	/*
	 * 发现在Windows上也不会用\n\r这样二逼的换行符了，这里暂时统一成\n，测试在各个系统上都会正确打出回车，
	 * 除非在unix上写的文件在windows上打开 2014-5-15 15:25:31
	 * 发现这里并不是之前说的打出了两个回车，而是各个命令的执行方式不同，
	 * 比如adb在执行的时候会因为匹配wiondws的输入打出\n\r导致读取两行而多打了一个回车。
	 * 而ipconfig这样的命令就可以正常进入下一行不会多执行一次readline的动作。
	 */
//	public final static String LINE_SEPARATOR = System
//			.getProperty("line.separator");
	 public final static String LINE_SEPARATOR = "\n";
	public final static String FILE_SEPAERATOR = System
			.getProperty("file.separator");

	// 各平台的换行符，仅在比对时使用，不要直接拿来用
	public final static String LINE_SEPARATOR_WINDOWS = System
			.getProperty("\n\r");
	public final static String LINE_SEPARATOR_UNIX = System.getProperty("\n");
	public final static String LINE_SEPARATOR_MACOS = System.getProperty("\r");

	public final static String JAVA_COMMON_PATH = USER_HOME + FILE_SEPAERATOR
			+ "JavaCommon";

	public final static String JAVA_COMMON_LOG_PATH = JAVA_COMMON_PATH
			+ FILE_SEPAERATOR + "java_common.log";

	public final static void pl(String s) {
		pl(LOG_TAG, s);
	}

	public final static void pl(String tag, String s) {
		System.out.println("[" + getTime() + "]" + "[" + tag + "]" + s);
	}

	public final static void pl(int i) {
		pl(LOG_TAG, ""+i);
	}

	public final static void pl(float f) {
		pl(LOG_TAG, ""+f);
	}

	public final static void pl(double d) {
		pl(LOG_TAG, ""+d);
	}
	
	public final static void pls(String s[]){
		if(s==null){
			pl("strings is null");
			return;
		}
		
		for(String tmp:s){
			pl(tmp);
		}
	}
	
	public final static void pla(ArrayList<String> s){
		if(s==null){
			pl("string array is null");
			return;
		}
		
		for(String tmp:s){
			pl(tmp);
		}
	}
	
	public final static void pls(int i[]){
		if(i==null){
			pl("ints is null");
			return;
		}
		
		for(int tmp:i){
			pl(tmp);
		}
	}
	
	public final static void pls(float f[]){
		if(f==null){
			pl("floats is null");
			return;
		}
		
		for(float tmp:f){
			pl(tmp);
		}
	}
	
	public final static void pls(double d[]){
		if(d==null){
			pl("doubles is null");
			return;
		}
		
		for(double tmp:d){
			pl(tmp);
		}
	}
	
	public final static void psl(String s[]){
		if(s==null){
			pl("strings is null");
			return;
		}
		
		String res="{";
		
		for(int j=0;j<s.length;j++){
			res=res+"\""+s[j]+"\"";
			if(j!=s.length-1){
				res=res+",";
			}
		}
		
		res=res+"}";
		
		pl(res);
	}
	
	public final static void psl(ArrayList<String> s){
		if(s==null){
			pl("strings is null");
			return;
		}
		
		String[] ss=new String[s.size()];
		
		for(int i=0;i<s.size();i++){
			ss[i]=s.get(i);
		}
		
		psl(ss);
	}
	
	public final static void psl(int i[]){
		if(i==null){
			pl("ints is null");
			return;
		}
		
		String res="{";
		
		for(int j=0;j<i.length;j++){
			res=res+i[j];
			if(j!=i.length-1){
				res=res+",";
			}
		}
		
		res=res+"}";
		
		pl(res);
	}
	
	public final static void psl(float f[]){
		if(f==null){
			pl("ints is null");
			return;
		}
		
		String res="{";
		
		for(int j=0;j<f.length;j++){
			res=res+f[j];
			if(j!=f.length-1){
				res=res+",";
			}
		}
		
		res=res+"}";
		
		pl(res);
	}
	
	public final static void psl(double d[]){
		if(d==null){
			pl("ints is null");
			return;
		}
		
		String res="{";
		
		for(int j=0;j<d.length;j++){
			res=res+d[j];
			if(j!=d.length-1){
				res=res+",";
			}
		}
		
		res=res+"}";
		
		pl(res);
	}

	public final static void wl(String s) {
		pl(s);
		writeLine(JAVA_COMMON_LOG_PATH, "[" + getTime() + "]" + "[" + LOG_TAG
				+ "]" + s);
	}

	public final static void wl(String tag, String s) {
		pl(tag, s);
		writeLine(JAVA_COMMON_LOG_PATH, "[" + getTime() + "]" + "[" + tag + "]"
				+ s);
	}

	private static boolean writeLine(String filePath, String str) {
		File f = new File(filePath);
		if (!f.exists()) {
			f.getParentFile().mkdirs();
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				pl(e.toString());
			}
		}

		try {
			FileOutputStream fos = new FileOutputStream(filePath, true);
			String s = str + LINE_SEPARATOR;
			fos.write(s.getBytes());
			fos.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void printComponentInfo() {
		pl("component_version_code = " + COMMON_VERSION_CODE);
		pl("component_version_name = " + COMMON_VERSION_NAME);
		pl("component_sign = " + COMPONENT_SIGN);
	}

	public static String getTimeForFile() {
		return DATE_FORMAT_FOR_FILE.format(new Date());
	}

	public static String getTime() {
		return DATE_FORMAT.format(new Date());
	}
}
