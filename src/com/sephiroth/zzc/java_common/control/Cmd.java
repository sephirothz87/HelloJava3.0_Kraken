package com.sephiroth.zzc.java_common.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.sephiroth.zzc.java_common.util.Util;

public class Cmd {
	public static String excute(String cmd) {
		String res = Util.LINE_SEPARATOR;
		Process process;

		try {
			process = Runtime.getRuntime().exec(cmd);

			//2014-5-15 15:23:43	为了兼顾windows下的中文输出，这里的默认编码改为gbk
			BufferedReader in = new BufferedReader(new InputStreamReader(
					process.getInputStream(),"gbk"));
			BufferedReader err = new BufferedReader(new InputStreamReader(
					process.getErrorStream(),"gbk"));

			String line = "";
			
			while ((line = in.readLine()) != null) {
				res += line + Util.LINE_SEPARATOR;
			}
			in.close();

			while ((line = err.readLine()) != null) {
				res += line + Util.LINE_SEPARATOR;
			}
			err.close();

			process.destroy();
		} catch (IOException e) {
			e.printStackTrace();
			Util.pl("cmd excute fail");
		}

		return res;
	}
}
