package com.sephiroth.zzc.java_common.service;

import java.io.File;
import java.util.ArrayList;

import com.sephiroth.zzc.java_common.control.FileManager;
import com.sephiroth.zzc.java_common.control.MD5Manager;
import com.sephiroth.zzc.java_common.control.TimeManager;
import com.sephiroth.zzc.java_common.util.Util;

public class LinkExporter {
	private final static String DEFAULT_EXPORT_PATH=Util.JAVA_COMMON_PATH+Util.FILE_SEPAERATOR+"LinkExporter";
	
	private static class LinkOutput {
		static final String HEADER = "http://dldir1.qq.com/qqmi/";

		@SuppressWarnings("unused")
		String title;
		String md5;
		String link;

		void setLink(String fileName) {
			if (fileName != null) {
				link = HEADER + fileName;
			}
		}

		String getToWriteString() {
			String res = "URL:" + link + Util.LINE_SEPARATOR + "MD5:" + md5;
			return res;
		}
	}

	/**
	 * 输出结果文件
	 * 
	 * @param oriPath
	 *            源文件路径
	 * @param desFile
	 *            输入文本文件的路径
	 */
	public static void ExportLinkFile(String oriPath, String desFile) {
		File input_dir_f = new File(oriPath);
		ArrayList<LinkOutput> res = new ArrayList<LinkOutput>();
		if (input_dir_f.isDirectory()) {
			// File[] package_dir_f=input_dir_f.listFiles();
			// for(int index=0;index<package_dir_f.length;index++){
			// if(package_dir_f[index].isDirectory()){
			// LinkOutput l=new LinkOutput();
			// l.title=package_dir_f[index].getName();
			// File[] inner_files=package_dir_f[index].listFiles();
			// if(inner_files.length==1){
			// // l.link=LinkOutput.HEADER+inner_files[0].getName();
			// l.setLink(inner_files[0].getName());
			// l.md5=MD5Manager.getFileMD5(inner_files[0]);
			// res.add(l);
			// }else if(inner_files.length==0){
			// Util.PL("error:"+package_dir_f[index].getName()+" folder is null");
			// return;
			// }else{
			// Util.PL("error:"+package_dir_f[index].getName()+" has more than one file");
			// return;
			// }
			//
			//
			// }else{
			// Util.PL("error:input dir contains file"+package_dir_f[index].getName());
			// return;
			// }
			// }

			File[] apk_files = input_dir_f.listFiles();
			for (int index = 0; index < apk_files.length; index++) {
				if (!apk_files[index].isDirectory()) {
					LinkOutput l = new LinkOutput();
					l.title = "title";
					l.setLink(apk_files[index].getName());
					l.md5 = MD5Manager.getFileMD5(apk_files[index]);
					res.add(l);
				} else {
					Util.pl("error:input dir contains directory"
							+ apk_files[index].getName());
					continue;
				}
			}
		} else {
			Util.pl("error:input dir is not a directory");
			return;
		}
		if (!new File(desFile).exists()) {
			FileManager.createNewFile(desFile);
		}
		if (new File(desFile).isDirectory()) {
			Util.pl("output file is a directory");
			return;
		}
		for (LinkOutput l_to_write : res) {
			FileManager.writeLine(desFile, l_to_write.getToWriteString());
		}
		return;
	}

	/**
	 * 无需输入目标路径，会在默认目录下输入文本
	 * 
	 * @param oriPath
	 *            源文件路径
	 */
	public static void ExportLinkFile(String oriPath) {
		ExportLinkFile(oriPath, DEFAULT_EXPORT_PATH + Util.FILE_SEPAERATOR
				+ "link_export_" + TimeManager.getTimeForFile() + ".txt");
	}
}
