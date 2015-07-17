package com.sephiroth.zzc.java_common.service;

import com.sephiroth.zzc.java_common.control.FileManager;
import com.sephiroth.zzc.java_common.control.TimeManager;

public class Logger {
	public String FilePath;

	public String Tag;
	public String Time;

	public Logger(String filePath) {
		super();
		FilePath = filePath;
		Tag = "JavaComponent";
		createLogFile();
	}

	public Logger(String filePath, String tag) {
		super();
		FilePath = filePath;
		Tag = tag;
		createLogFile();
	}
	
	public void write(String tag,String content){
		String log="["+TimeManager.getTime()+"]"+"["+tag+"]:"+content;
		writeContent(log);
	}
	
	public void write(String content){
		String log="["+TimeManager.getTime()+"]"+"["+Tag+"]:"+content;
		writeContent(log);
	}
	
	public void writeContent(String content){
		//所有log增加控制台输出控制台输出
		System.out.println(content);
		FileManager.writeLine(FilePath,content);
	}
	
	private void createLogFile(){
		FileManager.createNewFile(FilePath);
	}

	public String getFilePath() {
		return FilePath;
	}

	public void setFilePath(String filePath) {
		FilePath = filePath;
	}

	public String getTag() {
		return Tag;
	}

	public void setTag(String tag) {
		Tag = tag;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}
}
