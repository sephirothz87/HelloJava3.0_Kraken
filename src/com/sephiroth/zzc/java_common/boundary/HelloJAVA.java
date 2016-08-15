package com.sephiroth.zzc.java_common.boundary;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.awt.*;
import java.awt.event.*;

import com.sephiroth.zzc.java_common.control.FileManager;
import com.sephiroth.zzc.java_common.control.MD5Manager;
import com.sephiroth.zzc.java_common.control.TimeManager;
import com.sephiroth.zzc.java_common.control.XMLUtil;
import com.sephiroth.zzc.java_common.control.ZProperties;
import com.sephiroth.zzc.java_common.control.Zipper;
import com.sephiroth.zzc.java_common.entity.Match;
import com.sephiroth.zzc.java_common.service.CsvManager;
import com.sephiroth.zzc.java_common.service.FTP;
import com.sephiroth.zzc.java_common.service.FileCreater;
import com.sephiroth.zzc.java_common.service.LinkExporter;
import com.sephiroth.zzc.java_common.service.MMManager;
import com.sephiroth.zzc.java_common.service.MatchManager;
import com.sephiroth.zzc.java_common.service.SystemInfoManager;
import com.sephiroth.zzc.java_common.util.Util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class HelloJAVA {

	private static String mTestPath = Util.JAVA_COMMON_PATH
			+ Util.FILE_SEPAERATOR;

	public static void main(String... args) throws IOException {
		Util.pl("Component Version : " + Util.COMMON_VERSION_CODE + "  "
				+ Util.COMMON_VERSION_NAME);

		Util.pl("function start");

		// 临时测试入口
		function20160815114540();

		// 输出可执行jar包使使用
		// function(args);

		Util.pl("function end");
	}

	// 临时测试入口
	public static void function_temp(String... args) {
		FTP ftp = new FTP();
		ftp.upTest();
	}

	static void f(String... p) {
		Util.pl("length - " + String.valueOf(p.length));
		for (String s : p) {
			Util.pl(s);
		}
	}

	// 封装jar包的入口

	// 测试命令行接口
	public static void function(String... args) {
	}

	// 克苏恩杀怪测试
	static class BattleField {
		// 地方角色血量
		int playerHealth = 30;
		// 敌方随从
		ArrayList<Integer> minons;

		BattleField(int playerHealth, ArrayList<Integer> minons) {
			this.playerHealth = playerHealth;
			this.minons = minons;
		}

		public String toString() {
			String ret = "" + this.playerHealth;
			for (Integer i : this.minons) {
				ret += " " + i;
			}
			return ret;
		}
	}

	static class CthunReport {
		int statusCount;
		double statusRate;

		public CthunReport(int statusCount, double statusRate) {
			super();
			this.statusCount = statusCount;
			this.statusRate = statusRate;
		}

		public String toString() {
			return "" + this.statusCount + "\t\t"
					+ Util.DECIMAL_FORMAT_PERCENT.format(statusRate * 100)
					+ "%";
		}
	}

	public static void function20160815114540(String... args) {
		MatchManager m = new MatchManager();
		for (int i = 0; i < 100; i++) {
			Match match = m.genMatch();
			Util.pl(m.genMatchResult(match).toString());
			Util.pl(Util.JAVA_COMMON_LOG_PATH);
			// File log = new File(Util.JAVA_COMMON_LOG_PATH);
			FileManager.writeLine(Util.JAVA_COMMON_LOG_PATH, match.toCsv());
		}
	}

	public static void function20160722113357(String... args) {
		// 敌方随从
		ArrayList<Integer> minons = new ArrayList<Integer>();
		// 假设是吉祥三宝
		minons.add(2);
		// minons.add(2);
		minons.add(4);
		minons.add(4);

		// 敌方角色血量
		int player_health = 30;

		// BattleField b_a = new BattleField(player_health,minons);

		// if(equalBattleField(b_a,b_b)){
		// Util.pl("true");
		// }else{
		// Util.pl("false");
		// }

		// 克苏恩的攻击力（攻击次数）
		// int cthun_attack = 3;
		int cthun_attack = 12;

		// for(int i=0;i<cthun_attack;i++){
		//
		// }

		baseBattle = new BattleField(player_health, minons);
		curBattle = new BattleField(player_health, minons);

		BattleField bf = new BattleField(player_health, minons);

		attack(bf, "", 1.0, cthun_attack);

		Util.pl(counter);

		Iterator iter = retMap.entrySet().iterator();

		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();

			// Util.pl("["+entry.getKey()+"]:"+"\t"+Util.DECIMAL_FORMAT_PERCENT.format((double)(Integer)entry.getValue()/counter*100)+"%\t"+entry.getValue());
			Util.pl("[" + entry.getKey() + "]:"
					+ ((CthunReport) entry.getValue()).toString());
		}

		// Util.pla(ret);
		// PrintBattle(retBf);
	}

	// private static void attack(BattleField bf){
	//
	// }

	static BattleField baseBattle;
	static BattleField curBattle;
	static int counter = 0;
	static ArrayList<String> ret = new ArrayList<String>();
	static ArrayList<BattleField> retBf = new ArrayList<BattleField>();
	static String root = "";
	static HashMap<String, CthunReport> retMap = new HashMap<String, CthunReport>();

	public static void attack(BattleField bf, String rt, double rate,
			int max_attack) {
		// PrintBattle(bf);
		// BattleField bs_battle = new BattleField(bf.playerHealth,bf.minons);
		ArrayList<Integer> nx_minons = new ArrayList<Integer>();
		// nx_minons = (ArrayList<Integer>) bf.minons.clone();
		BattleField nx_battle = new BattleField(bf.playerHealth,
				(ArrayList<Integer>) bf.minons.clone());
		String nx_rt = new String("");
		double nx_rate = rate;

		int minons_alive_count = 0;
		for (int i = 0; i < bf.minons.size(); i++) {
			if (bf.minons.get(i) > 0) {
				minons_alive_count++;
			}
		}

		double base_rate = 1.0 / (minons_alive_count + 1);

		if (rt.length() < max_attack) {
			// 攻击角色
			nx_rt = rt + "0";
			nx_battle.playerHealth -= 1;
			nx_rate *= base_rate;

			attack(nx_battle, nx_rt, nx_rate, max_attack);

			// 攻击随从
			for (int i = 0; i < bf.minons.size(); i++) {
				int minon_health = bf.minons.get(i);
				if (minon_health > 0) {
					nx_battle = new BattleField(bf.playerHealth,
							(ArrayList<Integer>) bf.minons.clone());
					nx_rt = new String("");
					nx_rate = rate;

					nx_rt = rt + (i + 1);
					nx_battle.minons.set(i, minon_health - 1);
					nx_rate *= base_rate;

					attack(nx_battle, nx_rt, nx_rate, max_attack);
				}
			}
		} else {
			ret.add(rt);
			retBf.add(bf);

			counter++;

			String bf_status = bf.toString();
			if (retMap.containsKey(bf_status)) {
				// CthunReport cr = retMap.get(bf_status);
				retMap.get(bf_status).statusCount += 1;
				retMap.get(bf_status).statusRate += rate;
			} else {
				retMap.put(bf_status, new CthunReport(1, rate));
			}

			Util.pl(rt);
			PrintBattle(bf);

			// root = "";
			// // tmp_battle = new
			// BattleField(baseBattle.playerHealth,baseBattle.minons);
			// curBattle = new
			// BattleField(baseBattle.playerHealth,baseBattle.minons);

			return;
		}
	}

	private static void PrintBattleArray(ArrayList<BattleField> retBf) {
		String ret = "B :";
		for (BattleField bf : retBf) {
			ret += " " + bf.playerHealth;
			for (Integer i : bf.minons) {
				ret += " " + i;
			}
		}
		Util.pl(ret);
	}

	private static void PrintBattle(BattleField bf) {
		String ret = "B :";
		ret += " " + bf.playerHealth;
		for (Integer i : bf.minons) {
			ret += " " + i;
		}
		Util.pl(ret);
	}

	private static boolean equalBattleField(BattleField a, BattleField b) {
		if (a.playerHealth != b.playerHealth) {
			return false;
		}

		if (a.minons.size() != b.minons.size()) {
			return false;
		}

		for (int i = 0; i < a.minons.size(); i++) {
			if (a.minons.get(i) != b.minons.get(i)) {
				return false;
			}
		}

		return true;
	}

	// awt鼠标点击测试
	public static void function20160504171725(String... args) {
		try {
			final Robot rb = new Robot();
			new Thread() {
				public void run() {
					Util.pl("run");
					for (int i = 0; i < 10; i++) {
						rb.delay(2000);
						// 回车
						// rb.keyPress(KeyEvent.VK_ENTER);
						// rb.keyRelease(KeyEvent.VK_ENTER);

						int x1 = (int) (Math.random() * 500);
						int y1 = (int) (Math.random() * 500);
						int x2 = (int) (Math.random() * 500);
						int y2 = (int) (Math.random() * 500);
						rb.mouseMove(x1, y1);
						rb.mousePress(InputEvent.BUTTON1_MASK);
						rb.mouseMove(x2, y2);
						rb.mouseRelease(InputEvent.BUTTON1_MASK);
					}
					Util.pl("over");
				}
			}.start();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void pressMouse(Robot r, int m, int delay) {
		r.mousePress(m);
		r.delay(10);
		r.mouseRelease(m);
		r.delay(delay);
	}

	// 2015-12-30 8:04:08 ==和equal的验证
	public static void function2015123080428(String... args) {
		String a = "123";
		String b = a;
		String c = "123";
		String d = new String("123");

		if (a == b) {
			Util.pl("a==b");
		} else {
			Util.pl("a!=b");
		}

		if (a.equals(b)) {
			Util.pl("a.equals(b)");
		} else {
			Util.pl("!a.equals(b)");
		}

		if (a == c) {
			Util.pl("a==c");
		} else {
			Util.pl("a!=c");
		}

		if (a.equals(c)) {
			Util.pl("a.equals(c)");
		} else {
			Util.pl("!a.equals(c)");
		}

		if (a == d) {
			Util.pl("a==d");
		} else {
			Util.pl("a!=d");
		}

		if (a.equals(d)) {
			Util.pl("a.equals(d)");
		} else {
			Util.pl("!a.equals(d)");
		}

		class C {
			int aa;
			int bb;
			String ss;

			C(int a, int b, String s) {
				aa = a;
				bb = b;
				ss = s;
			}
		}

		C c_a = new C(1, 2, "cc");
		C c_b = c_a;
		C c_c = new C(1, 2, "cc");

		if (c_a == c_b) {
			Util.pl("c_a==c_b");
		} else {
			Util.pl("c_a!=c_b");
		}

		if (c_a.equals(c_b)) {
			Util.pl("c_a.equals(c_b)");
		} else {
			Util.pl("!c_a.equals(c_b)");
		}

		if (c_a == c_c) {
			Util.pl("c_a==c_c");
		} else {
			Util.pl("c_a!=c_c");
		}

		if (c_a.equals(c_c)) {
			Util.pl("c_a.equals(c_c)");
		} else {
			Util.pl("!c_a.equals(c_c)");
		}

		String weekend = "weekend";

		if (weekend == "weekend") {
			Util.pl("weekend == weekend");
		} else {
			Util.pl("weekend != weekend");
		}

		if (weekend.equals("weekend")) {
			Util.pl("weekend  .equals weekend");
		} else {
			Util.pl("weekend  !.equals weekend");
		}

		if (weekend == new String("weekend")) {
			Util.pl("weekend == new String weekend");
		} else {
			Util.pl("weekend != new String weekend");
		}

		if (weekend.equals(new String("weekend"))) {
			Util.pl("weekend  .equals new String weekend");
		} else {
			Util.pl("weekend  !.equals new String weekend");
		}
	}

	// 2015-12-11 16:38:48 逐星的期望计算
	public static void function20151211163909(String... args) {
		ArrayList<Float> rate_find_map = new ArrayList<Float>();
		int temp_add = 0;
		for (int i = 1; i <= 24; i++) {
			temp_add += i;
		}
		float exp_find_map = temp_add / 24;
		Util.pl(exp_find_map);
	}

	// 2015-10-27 15:32:07 批量重命名文件
	public static void function20151027153226(String... args) {
		DecimalFormat df1 = new DecimalFormat("00");
		String path = "F:\\tmp\\img\\";
		String path_des = "F:\\tmp\\img_rename\\";

		File p = new File(path);
		File fs[] = p.listFiles();
		for (File f : fs) {
			String s_ori = f.getName();
			Util.pl(s_ori);
			// short index = Short.parseShort(s_ori.substring(5, 7));
			short index = Short.parseShort(s_ori.substring(4, 6));
			Util.pl(index);
			String s_des = "img_p" + df1.format(index + 55) + ".jpg";
			// File des_file = new File(path_des+s_des);
			FileManager.copyFile(f.getPath(), path_des + s_des, true);
		}
	}

	// 2014-12-31 17:13:19 测试Zip模块
	public static void function20141231171345(String... args) {
		Util.pl(mTestPath);

		// Zipper.compress(mTestPath+"RoboTest.xml");
		Zipper.compress(mTestPath + "RoboTest.xml", mTestPath + "1.zip");
	}

	// 2014-12-31 17:13:19 测试Zip模块

	// 2014-12-28 17:23:13 测试XMLUtil，写修改属性
	public static final String NODE_NAME_TEST_CASE = "TestCase";

	public static final String NODE_ATTRIBUTE_ID = "id";
	public static final String NODE_ATTRIBUTE_NAME = "name";
	public static final String NODE_ATTRIBUTE_DES_SIM = "decription_sim";
	public static final String NODE_ATTRIBUTE_DES_DTL = "decription_dtl";
	public static final String NODE_ATTRIBUTE_LAST_ABOUT_TIME = "last_about_time";
	public static final String NODE_ATTRIBUTE_IS_ABLE = "is_able";
	public static final String NODE_ATTRIBUTE_IS_OPEN = "id_open";

	public static void function20141228172349(String... args) {
		Util.pl(mTestPath);

		XMLUtil.init(mTestPath + "RoboTest.xml");

		Util.pl(XMLUtil.getNodeAttribute(NODE_NAME_TEST_CASE,
				NODE_ATTRIBUTE_DES_SIM, 1));
		Util.pl(XMLUtil.getNodeAttribute(NODE_NAME_TEST_CASE,
				NODE_ATTRIBUTE_DES_SIM, NODE_ATTRIBUTE_ID, "3"));
		Util.pl(XMLUtil.getNodeAttribute(NODE_NAME_TEST_CASE,
				NODE_ATTRIBUTE_DES_SIM, NODE_ATTRIBUTE_DES_DTL, "详细", 4));
		Util.pl(XMLUtil.getNodeAttribute(NODE_NAME_TEST_CASE,
				NODE_ATTRIBUTE_DES_SIM, NODE_ATTRIBUTE_IS_ABLE, "false"));
		Util.pl(XMLUtil.getNodeAttribute(NODE_NAME_TEST_CASE,
				NODE_ATTRIBUTE_DES_SIM, 4));
		Util.pl(XMLUtil.getNodeAttribute(NODE_NAME_TEST_CASE,
				NODE_ATTRIBUTE_DES_SIM, NODE_ATTRIBUTE_IS_ABLE, "true", 4));

		String s = XMLUtil.getNodeAttribute(NODE_NAME_TEST_CASE,
				NODE_ATTRIBUTE_DES_SIM, NODE_ATTRIBUTE_ID, "2");
		s = s + TimeManager.getTime();
		Util.pl(s);
		XMLUtil.setNodeAttribute(NODE_NAME_TEST_CASE, NODE_ATTRIBUTE_DES_SIM,
				s, NODE_ATTRIBUTE_ID, "2");
	}

	// 2014-12-28 17:23:13 测试XMLUtil，写修改属性

	// 2014-12-10 22:30:02 测试java property
	public static void function20141210222850(String... args) {
		// Util.pl(mTestPath);
		String test_path = "E:\\92_TESTPATH\\";
		String propeties_file_name = "property.properties";
		String propeties_file_path = test_path + propeties_file_name;

		ZProperties pps = new ZProperties();

		try {
			InputStream ins = new BufferedInputStream(new FileInputStream(
					propeties_file_path));
			pps.load(ins);

			String p1 = pps.getProperty("p1");
			String p2 = pps.getProperty("p2");
			String p3 = pps.getProperty("p3");
			String p4 = pps.getProperty("p4");

			Util.pl("p1 = " + p1);
			Util.pl("p2 = " + p2);
			Util.pl("p3 = " + p3);
			Util.pl("p4 = " + p4);

			ArrayList<String> p5 = pps.getArrayProperties("p5");
			Util.pl("p5:");
			Util.psl(p5);

			String[] p6 = pps.getProperties("p6");
			Util.pl("p6:");
			Util.psl(p6);

			int p7 = pps.getIntProperty("p7");
			Util.pl(p7);

			int[] p8 = pps.getIntProperties("p8");
			Util.psl(p8);

			float p9 = pps.getIntProperty("p9");
			Util.pl(p9);

			float[] p10 = pps.getFloatProperties("p10");
			Util.psl(p10);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// String s= "123456a";
		//
		// try{
		//
		// int i=Integer.parseInt(s);
		// Util.pl(""+i);
		// }catch(NumberFormatException e){
		// Util.pl("NumberFormatException");
		// e.printStackTrace();
		// }

	}

	// 2014-07-06 19:29:31 尝试写一个算法
	/*
	 * 随机出现M个正整数中的其中一个，到X位时，包含有Y个不同的数字
	 */
	public static void function20140706193136(String... args) {
		AlCauculater al = new AlCauculater();
		al.setM(9);

		Util.pl("总数为：" + al.M);
		for (int x = 1; x <= 50; x++) {
			for (int y = 1; y <= x && y <= al.M; y++) {
				if (y == 9) {
					Util.pl("第 " + x + " 位出现 " + y + " 个数的概率是：" + al.al(x, y));
				}
				// Util.pl("第 " + x + " 位出现 " + y + " 个数的概率是：" + al.al(x, y));
			}
		}
	}

	static class AlCauculater {
		public AlCauculater() {
			super();
		}

		void setM(int m) {
			this.M = m;
		}

		// 总数
		int M;

		// 递归算法，求随机至X位时，一共出现过Y个数的概率
		double al(int x, int y) {
			double result = 0;

			if (x < y) {
				Util.wl("error:x < y");
			}

			if (x == 1) {
				result = 1;
			} else if (y == 1) {
				result = al(x - 1, y) / M;
			} else if (y == x) {
				result = al(x - 1, y - 1) * (M - y + 1) / M;
			} else {
				result = (al(x - 1, y - 1) * (M - y + 1) + al(x - 1, y) * y)
						/ M;
			}
			return result;
		}
	}

	// 2014-07-06 19:29:31 尝试写一个算法

	// 2014-04-21 15:07:44 test csv
	public static void function20140421150744(String... args) {
		String file_path = Util.JAVA_COMMON_PATH + Util.FILE_SEPAERATOR
				+ TimeManager.getTimeForFile() + CsvManager.CSV_BACKSTR;
		FileManager.createNewFile(file_path);
		String[] s1 = { "232", "34342", "1231231", "3333", "4444", "55555",
				"66666" };
		String[] s2 = { "232", "343\n\r42", "1231\n\r231", "3333", "4444",
				"55\n\r555", "66\n\r666" };
		String[] s3 = { "232", "34342", "12312\n\r31", "333\n\r3", "4444\n\r",
				"55555", "66666" };
		String[] s4 = { "大水\n\r法", "企鹅入侵我入群哦", "实打实风骚的费", "阿的", "不错",
				"雀肉去\n\r", "而且我若气温", "而且我若气温", "而且我若气\n\r温", "而且\n\r我若气温" };

		ArrayList<String[]> strings = new ArrayList<String[]>();
		strings.add(s1);
		strings.add(s2);
		strings.add(s3);
		strings.add(s4);

		String[][] groups = { s1, s2, s3, s4 };

		// CsvManager.writeLine(s1, file_path);
		// CsvManager.writeLine(s2, file_path);
		// CsvManager.writeLine(s3, file_path);
		// CsvManager.writeLine(s4, file_path);

		CsvManager.writeGroup(groups, file_path);
	}

	// 2014-04-21 15:07:44 test csv

	// 2014-04-10 16:58:42 test link exporter
	public static void function20140410165842(String... args) {
		if (args.length == 1) {
			LinkExporter.ExportLinkFile(args[0]);
		} else if (args.length == 0) {
			Util.pl("please enter param");
		} else {
			LinkExporter.ExportLinkFile(args[0], args[1]);
		}
	}

	// 2014-04-10 16:58:42 test link exporter

	// 2014-04-10 16:27:39 test new util logger
	public static void function20130410162739() {
		Util.pl("c1");
		Util.pl("tag2", "c2");
		Util.wl("c3");
		Util.wl("tag4", "c4");
	}

	// 2014-04-10 16:27:39 test new util logger

	// 2014-04-09 02:01:20 test printSystemInfo
	public static void function20140409020120() {
		SystemInfoManager sysinfo = new SystemInfoManager();
		sysinfo.printAllSystemInfo();
	}

	// 2014-04-09 02:01:20 test printSystemInfo

	// 2014-04-02 20:13:12 test MD5 tools
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
			// String res=title+"\nURL:"+link+"\nMD5:"+md5;
			String res = "URL:" + link + "\nMD5:" + md5;
			return res;
		}
	}

	// 测试方法
	public static void function20140402201312() {
		// String
		// s="/Users/zhongzhicong/Documents/01_development/debug_path/qqlive_3.2.0.5853_115_140404002611a.apk";
		// File f=new File(s);
		// Util.PL(MD5Manager.getFileMD5(f));

		String input_dir = "/Users/zhongzhicong/Documents/01_development/debug_path/links";
		String output_dir = "/Users/zhongzhicong/Documents/01_development/debug_path/link2014-04-04-143821.txt";
		File input_dir_f = new File(input_dir);
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

		if (!new File(output_dir).exists()) {
			FileManager.createNewFile(output_dir);
		}
		for (LinkOutput l_to_write : res) {
			FileManager.writeLine(output_dir, l_to_write.getToWriteString());
		}
	}

	public static void ExportLinkFile(String oriPath, String desFile) {
		// String
		// s="/Users/zhongzhicong/Documents/01_development/debug_path/md5s/f3/QQLive_3.2.0.
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

	// 2014-03-14 20:47:15 test log4j

	// 2014-02-26 10:37:35 test create directory
	public static void function20140226103735() {
		FileManager.createNewPath(Util.TEST_PATH_MAC + "createpath");
	}

	// 2014-02-26 10:37:35 test create directory

	// 2014-02-25 19:35:29 test folder copy
	public static void function20140225193529() {
		// FileManager.copyFile(Util.TEST_PATH_MAC+"folder1/testfile.log",
		// Util.TEST_PATH_MAC+"folder2/file1", true);
		FileManager.copyDirectory(Util.TEST_PATH_MAC + "folder1",
				Util.TEST_PATH_MAC + "folder2/folder1111", true);
	}

	// 2014-02-25 19:35:29 test folder copy

	// 2014-02-11 15:55:18 test�??mac读�?????�??
	public static void function20140211155518() {
		try {
			// FileCreater.CreateSpeFileByMB(1024, testPath + "testfile");
			FileCreater.CreateSpeFileByKB(1, mTestPath + "testfile");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 2014-02-11 15:55:18 test�??mac读�?????�??

	// 2014-01-01-201801 test ??��??�??�??�??
	public static void function20140101201801() {
		@SuppressWarnings("unused")
		int numbers[] = { 1, 2, 4, 6, 7, 9 };
		ArrayList<Integer[]> zuhe = new ArrayList<Integer[]>();
		// ??????�?????�??�??�????????
		// zuhe=getZuhe(numbers,3);
		Integer[] i1 = { 1, 2, 4, 6 };
		Integer[] i2 = { 1, 2, 4, 7 };
		Integer[] i3 = { 1, 2, 4, 9 };
		Integer[] i4 = { 1, 2, 6, 7 };
		Integer[] i5 = { 1, 2, 6, 9 };
		Integer[] i6 = { 1, 2, 7, 9 };
		Integer[] i7 = { 1, 4, 6, 7 };
		Integer[] i8 = { 1, 4, 6, 9 };
		Integer[] i9 = { 1, 4, 7, 9 };
		Integer[] i10 = { 1, 6, 7, 9 };
		Integer[] i11 = { 2, 4, 6, 7 };
		Integer[] i12 = { 2, 4, 6, 9 };
		Integer[] i13 = { 2, 4, 7, 9 };
		Integer[] i14 = { 2, 6, 7, 9 };
		Integer[] i15 = { 4, 6, 7, 9 };
		zuhe.add(i1);
		zuhe.add(i2);
		zuhe.add(i3);
		zuhe.add(i4);
		zuhe.add(i5);
		zuhe.add(i6);
		zuhe.add(i7);
		zuhe.add(i8);
		zuhe.add(i9);
		zuhe.add(i10);
		zuhe.add(i11);
		zuhe.add(i12);
		zuhe.add(i13);
		zuhe.add(i14);
		zuhe.add(i15);

		for (Integer[] i : zuhe) {

			int add_res = 0;
			int sub_res = 0;
			int[][] n = { { i[0] * 10 + i[1], i[2] * 10 + i[3] },
					{ i[0] * 10 + i[1], i[3] * 10 + i[2] },
					{ i[0] * 10 + i[2], i[1] * 10 + i[3] },
					{ i[0] * 10 + i[2], i[3] * 10 + i[1] },
					{ i[0] * 10 + i[3], i[1] * 10 + i[2] },
					{ i[0] * 10 + i[3], i[2] * 10 + i[1] },
					{ i[1] * 10 + i[0], i[2] * 10 + i[3] },
					{ i[1] * 10 + i[0], i[3] * 10 + i[2] },
					{ i[1] * 10 + i[2], i[0] * 10 + i[3] },
					{ i[1] * 10 + i[2], i[3] * 10 + i[0] },
					{ i[1] * 10 + i[3], i[0] * 10 + i[2] },
					{ i[1] * 10 + i[3], i[2] * 10 + i[0] },
					{ i[2] * 10 + i[0], i[1] * 10 + i[3] },
					{ i[2] * 10 + i[0], i[3] * 10 + i[1] },
					{ i[2] * 10 + i[1], i[0] * 10 + i[3] },
					{ i[2] * 10 + i[1], i[3] * 10 + i[0] },
					{ i[2] * 10 + i[3], i[0] * 10 + i[1] },
					{ i[2] * 10 + i[3], i[1] * 10 + i[0] },
					{ i[3] * 10 + i[0], i[1] * 10 + i[2] },
					{ i[3] * 10 + i[0], i[2] * 10 + i[1] },
					{ i[3] * 10 + i[1], i[0] * 10 + i[2] },
					{ i[3] * 10 + i[1], i[2] * 10 + i[0] },
					{ i[3] * 10 + i[2], i[0] * 10 + i[1] },
					{ i[3] * 10 + i[2], i[1] * 10 + i[0] } };
			for (int[] n2 : n) {
				if ((n2[0] % 10 + n2[1] % 10) > 10 && n2[0] + n2[1] <= 70) {
					Util.pl(n2[0] + " + " + n2[1] + " = " + (n2[0] + n2[1]));
					add_res += 1;
				}
				if (n2[0] % 10 < n2[1] % 10 && n2[0] - n2[1] >= 40) {
					Util.pl(n2[0] + " - " + n2[1] + " = " + (n2[0] - n2[1]));
					sub_res += 1;
				}
			}
			add_res /= 2;
			Util.pl("numbers : " + i[0] + "," + i[1] + "," + i[2] + "," + i[3]);
			Util.pl("add_res = " + add_res);
			Util.pl("sub_res = " + sub_res);
			if (add_res < 4 || sub_res < 4) {
				Util.pl("ABANDON!!!");
			} else {
				Util.pl("numbers : " + i[0] + "," + i[1] + "," + i[2] + ","
						+ i[3]);
				Util.pl("numbers : " + i[0] + "," + i[1] + "," + i[2] + ","
						+ i[3]);
				Util.pl("OK!!!");
				Util.pl("numbers : " + i[0] + "," + i[1] + "," + i[2] + ","
						+ i[3]);
				Util.pl("numbers : " + i[0] + "," + i[1] + "," + i[2] + ","
						+ i[3]);
			}
		}

	}

	// 2014-01-01-201801 test ??��??�??�??�??

	// 2013-12-29 16:35:34 test excel model

	public static void function20140101201823() {
		// String file_path=testPath+"write-excel-test.xls";
		// File f=FileManager.createNewFile(testPath+"write-excel-test.xls");
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("Tcase_list");
		HSSFRow row = sheet.createRow(0);
		@SuppressWarnings("unused")
		HSSFCell cell = row.createCell(0);

		// do it on one line
		row.createCell(1).setCellValue(1.2);
		row.createCell(2).setCellValue("test");
		row.createCell(3).setCellValue(true);
		HSSFCellStyle cellStyle = wb.createCellStyle();// new cell style
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));// set
																				// date
																				// style
		HSSFCell dcell = row.createCell(4);// create new cell
		dcell.setCellValue(new Date());
		dcell.setCellStyle(cellStyle);
		HSSFCell csCell = row.createCell(5);
		csCell.setCellType(HSSFCell.ENCODING_UTF_16);
		csCell.setCellValue("�?????�??�??_Chinese Words Test");// set cell code

		row.createCell(6).setCellType(HSSFCell.CELL_TYPE_ERROR);

		// write the output to a file
		try {
			FileOutputStream fileOut = new FileOutputStream(mTestPath
					+ "write-excel-test.xls");
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
			Util.pl("The cells have been added.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 2013-12-29 16:35:34 test excel model

	// 2013-12-25 11:24:35 test string
	// ???�??�??�??串中�??�??�??�?????空�?��??�?????
	public static void function20131229163559() {
		String s = "          0123456789  0123456789";
		int i = s.lastIndexOf("  ");
		Util.pl("s.lastIndexOf(\"  \") = " + i);
		boolean b = s.startsWith("  ");
		Util.pl("s.startsWith(\"  \") = " + b);
		byte[] bytes = s.getBytes();

		for (int index = 0; index < bytes.length; index++) {
			if (bytes[index] != 0x20) {
				Util.pl("first not blank is = " + index);
				break;
			}
		}

		String s2 = s.substring(11);
		Util.pl("s2 = " + s2);

		String s3 = "333222";
		Util.pl("s3.lastIndexOf(\"-\")" + s3.lastIndexOf("-"));
		String s4 = s3.substring(0, s3.lastIndexOf("-"));
		Util.pl("s4 = " + s4);
		s4 += "-" + "555";
		Util.pl("s4 = " + s4);
	}

	// 2013-12-25 11:24:35 test string
	// ???�??�??�??串中�??�??�??�?????空�?��??�?????

	// 2013-12-23 16:16:46 leonzhong file delete
	public static void function20131225104706() throws IOException {
		// if(FileManager.DeleteFile(testPath+"file_100m_2013-12-18-17-26-09")){
		if (FileManager.delFolder(mTestPath + "delete_path")) {
			// if(FileManager.delOneFile(testPath+"delete_path")){
			Util.pl("delete success");
		} else {
			Util.pl("delete failed");
		}
	}

	// 2013-12-23 16:16:46 leonzhong file delete

	// 2013-12-18 17:19:59 leonzhong file creater
	public static void function20131223161709() throws IOException {
		// FileCreater.CreateSpeFileByGB(1,
		// testPath+"file_1g_"+Util.getTimeForFile());
		FileCreater.CreateSpeFileByMB(100, mTestPath + "file_100m_"
				+ TimeManager.getTimeForFile());
		// FileCreater.CreateSpeFileByMB(10,
		// testPath+"file_10m_"+Util.getTimeForFile());
		// FileCreater.CreateSpeFileByMB(1,
		// testPath+"file_1m_"+Util.getTimeForFile());
	}

	// 2013-12-18 17:19:59 leonzhong file creater

	// 2013-12-13 10:35:17 leonzhong change mindmap to excel
	public static void function20131213103517() {
		MMManager mm_manager = new MMManager();
		if (mm_manager.ChangeMMToTestCase("", ""))
			;
	}

	// 2013-12-13 10:35:17 leonzhong change mindmap to excel

	// 2013-11-18 12:45:21 leonzhong filecreatetest
	public static void function20131118124521() {
		System.out.println("hello world");
		// FileCreater.CreateSpeFileByKB(50,
		// Util.TEST_PATH+"test_file_50kb.data");
		// FileCreater.CreateSpeFileByMB(50, Util.TEST_PATH
		// + "test_file_50mb.data");

		// FileCreater.CreateSpeFileByGB(2, Util.TEST_PATH
		// + "test_file_2gb.data");

		Date d = new Date();
		long s = d.getTime();
		// String date = new
		// java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(d);
		System.out.println(d);
		System.out.println(s);
	}
	// 2013-11-18 12:45:21 leonzhong filecreatetest
}
