package com.sephiroth.zzc.java_common.control;

import java.util.ArrayList;
import java.util.Properties;

public class ZProperties extends Properties {

	private static final String ARRAY_SPLIT = ",";

	private static final long serialVersionUID = 1L;

	public String[] getProperties(String key) {
		String s = getProperty(key);
		if(s==null){
			return null;
		}
		String[] res = s.split(ARRAY_SPLIT);
		return res;
	}
	
	public ArrayList<String> getArrayProperties(String key){
		String[] res_strs=getProperties(key);

		if(res_strs==null){
			return null;
		}
		
		ArrayList<String> res=new ArrayList<String>();
		for(String s:res_strs){
			res.add(s);
		}
		
		return res;
	}

	public int getIntProperty(String key) {
		return parserInt(getProperty(key));
	}

	public int[] getIntProperties(String key) {
		String s = getProperty(key);

		if(s==null){
			return null;
		}
		
		String[] res_str = s.split(ARRAY_SPLIT);
		int[] res = new int[res_str.length];
		for (int i = 0; i < res_str.length; i++) {
			res[i] = parserInt(res_str[i]);
		}

		return res;
	}
	
	public ArrayList<Integer> getArrayIntProperties(String key){
		int[] res_ints=getIntProperties(key);

		if(res_ints==null){
			return null;
		}
		
		ArrayList<Integer> res=new ArrayList<Integer>();
		for(int i:res_ints){
			res.add(i);
		}
		
		return res;
	}
	

	public float getFloatProperty(String key) {
		return parserFloat(getProperty(key));
	}

	public float[] getFloatProperties(String key) {
		String s = getProperty(key);

		if(s==null){
			return null;
		}
		
		String[] res_str = s.split(ARRAY_SPLIT);
		float[] res = new float[res_str.length];
		for (int i = 0; i < res_str.length; i++) {
			res[i] = parserFloat(res_str[i]);
		}

		return res;
	}
	
	public ArrayList<Float> getArrayFloatProperties(String key){
		float[] res_floats=getFloatProperties(key);

		if(res_floats==null){
			return null;
		}
		
		ArrayList<Float> res=new ArrayList<Float>();
		for(float f:res_floats){
			res.add(f);
		}
		
		return res;
	}

	public double getDoubleProperty(String key) {
		return parserDouble(getProperty(key));
	}

	public double[] getDoubleProperties(String key) {
		String s = getProperty(key);

		if(s==null){
			return null;
		}
		
		String[] res_str = s.split(ARRAY_SPLIT);
		double[] res = new double[res_str.length];
		for (int i = 0; i < res_str.length; i++) {
			res[i] = parserDouble(res_str[i]);
		}

		return res;
	}
	
	public ArrayList<Double> getArrayDoubleProperties(String key){
		double[] res_doubles=getDoubleProperties(key);

		if(res_doubles==null){
			return null;
		}
		
		ArrayList<Double> res=new ArrayList<Double>();
		for(double d:res_doubles){
			res.add(d);
		}
		
		return res;
	}

	private int parserInt(String s) {
		try {
			int res = Integer.parseInt(s);
			return res;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}	
	
	private float parserFloat(String s) {
		try {
			float res = Float.parseFloat(s);
			return res;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}	
	
	private double parserDouble(String s) {
		try {
			double res = Double.parseDouble(s);
			return res;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
