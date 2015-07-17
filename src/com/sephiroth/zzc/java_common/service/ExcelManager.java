package com.sephiroth.zzc.java_common.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.sephiroth.zzc.java_common.util.Util;

public class ExcelManager {
	
	public static boolean writeExcelSimple(ArrayList<String[]> contents,String outFilePath){
		writeExcelSimple(contents,outFilePath,"sheet1");
		return false;
	}
	
	public static boolean writeExcelSimple(ArrayList<String[]> contents,String outFilePath,String sheetName){

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet  = wb.createSheet(sheetName);
		
		for(int i=0;i<contents.size();i++){
			HSSFRow row = sheet.createRow(i);
			for(int j=0;j<contents.get(i).length;j++){
				row.createCell(j).setCellValue(contents.get(i)[j]);
			}
		}
          
        //write the output to a file  
        try {
            FileOutputStream fileOut = new FileOutputStream(outFilePath);  
			wb.write(fileOut);
	        fileOut.flush();
	        fileOut.close();  
			Util.pl("The cells have been added.");
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Util.pl(e.toString());
			return false;
		}  
	}
}
