package com.sephiroth.zzc.java_common.control;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author sephiroth.zhong Zip接口
 */
public class Zipper {
	// 压缩比 0-9
	final static short CONPRESS_RATIO = 6;
	final static int BUFFER = 8192;

	public static void compress(String oriFilePath, String desFilePath) {
		compress(oriFilePath, desFilePath, CONPRESS_RATIO);
	}

	public static void compress(String oriFilePath) {
		File f = new File(oriFilePath);
		String des_path = f.getParent() + System.getProperty("file.separator")
				+ f.getName() + ".zip";
		compress(oriFilePath, des_path);
	}

	public static void compress(String oriFilePath, String desFilePath,
			short compressRatio) {
		File file = new File(oriFilePath);
		if (!file.exists()) {
			throw new RuntimeException(oriFilePath + "不存在！");
		}

		//创建目标文件失败
		if (createNewFile(desFilePath) == null)
			return;

		try {
			FileOutputStream fileOutputStream = new FileOutputStream(
					desFilePath);
			CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,
					new CRC32());
			ZipOutputStream out = new ZipOutputStream(cos);
			out.setLevel(compressRatio);
			String basedir = "";
			compress(file, out, basedir);
			out.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static void compress(File file, ZipOutputStream out, String basedir) {
		/* 判断是目录还是文件 */
		if (file.isDirectory()) {
			System.out.println("压缩：" + basedir + file.getName());
			compressDirectory(file, out, basedir);
		} else {
			System.out.println("压缩：" + basedir + file.getName());
			compressFile(file, out, basedir);
		}
	}

	/** 压缩一个目录 */
	private static void compressDirectory(File dir, ZipOutputStream out,
			String basedir) {
		if (!dir.exists())
			return;

		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			/* 递归 */
			compress(files[i], out, basedir + dir.getName() + "/");
		}
	}

	/** 压缩一个文件 */
	private static void compressFile(File file, ZipOutputStream out,
			String basedir) {
		if (!file.exists()) {
			return;
		}
		try {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(file));
			ZipEntry entry = new ZipEntry(basedir + file.getName());
			out.putNextEntry(entry);
			int count;
			byte data[] = new byte[BUFFER];
			while ((count = bis.read(data, 0, BUFFER)) != -1) {
				out.write(data, 0, count);
			}
			bis.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static File createNewFile(String filePath) {
		File f = new File(filePath);
		if (!f.exists()) {
			f.getParentFile().mkdirs();
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			return f;
		}
		return null;
	}

}
