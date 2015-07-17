package com.sephiroth.zzc.java_common.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.poi.util.IOUtils;

public class FTP {
	final public short RES_CODE_OK = 0;
	final public short RES_CODE_UNKNOW_ERROR = 999;

	public FTP() {

	}

	public short upTest() {

		FTPClient ftpClient = new FTPClient();
		FileInputStream fis = null;

		try {
			ftpClient.connect("10.14.37.56");
			ftpClient.login("TENCENT\\LEONZHONG", "Zhong1404");

			File srcFile = new File("D:\\logcat.txt");
			fis = new FileInputStream(srcFile);
			// 设置上传目录
			ftpClient
					.changeWorkingDirectory("/tfs/文体协会/腾讯电影公社高清体验/★电影/WWDC/test");
			ftpClient.setBufferSize(1024);
			ftpClient.setControlEncoding("UTF-8");
			// 设置文件类型（二进制）
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.storeFile("log.txt", fis);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("FTP客户端出错！", e);
		} finally {
			IOUtils.closeQuietly(fis);
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("关闭FTP连接发生异常！", e);
			}
		}

		return RES_CODE_UNKNOW_ERROR;
	}

	public short uploadOneFile(String localPath, String url) {
		return RES_CODE_UNKNOW_ERROR;
	}

	public short uploadFiles(ArrayList<String> localPath, String url) {
		return RES_CODE_UNKNOW_ERROR;
	}

	public short uploadOneFolder(String localPath, String url) {
		return RES_CODE_UNKNOW_ERROR;
	}
}
