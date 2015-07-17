package com.sephiroth.zzc.java_common.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * @author leonzhong 结合DOM4J和XPATH进行通用的XML解析，需要导入jaxen.jar
 */
public class XMLUtil {

	// 文档
	private static Document mDoc = null;
	// 根节点
	private static Element mRoot = null;
	// XML文件路径
	private static String mPath = "";

	// 初始化配置文件，当需要读取新的XML文件时，需要重新初始化XMLUtil类（单例模式）
	public static void init(String path) {
		SAXReader reader = new SAXReader();
		try {
			// mDoc = DocumentHelper.parseText(xml);
			mPath = path;
			mDoc = reader.read(new File(mPath));
			mRoot = mDoc.getRootElement();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取名称为nodeName的文本
	 * 
	 * @param nodeName
	 * @return
	 */
	public static String getNodeText(String nodeName) {
		Node n = mRoot.selectSingleNode("//" + nodeName);
		return n.getText();
	}

	/**
	 * 获取所有名称为nodeName的节点
	 * 
	 * @param nodeName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Node> getNodeList(String nodeName) {
		return mRoot.selectNodes("//" + nodeName);
	}

	/**
	 * 获取节点名称为nodeName的attrName属性
	 * 
	 * @param nodeName
	 * @param attrName
	 * @return
	 */
	public static String getNodeAttribute(String nodeName, String attrName) {
		Node node = mRoot.selectSingleNode("//" + nodeName);
		return node.valueOf("@" + attrName);
	}

	/**
	 * 获取节点名称为nodeName的attrName属性
	 * 
	 * @param nodeName
	 * @param attrName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getNodeAttribute(String nodeName, String attrName,
			int index) {
		List<Node> nodes = mRoot.selectNodes("//" + nodeName + "[@" + attrName
				+ "]");
		return nodes.get(index).valueOf("@" + attrName);
	}

	/**
	 * 获取节点名称为nodeName的所有节点 条件：该节点里面有个属性为key值为value的属性 用于处理获取重名节点属性的问题
	 * 如果不希望引入dom4j相关的包，调用部分可以先用这个接口获取节点数，再通过调用getNodeAttribute(Node nodeName,
	 * String attrName, String key, String value, int index) 函数来遍历满足条件的节点获取对应属性
	 * 
	 * @param nodeName
	 * @param attrName
	 * @param key
	 * @param value
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Node> getNodeList(String nodeName, String key,
			String value) {
		List<Node> nodes = mRoot.selectNodes("//" + nodeName + "[@" + key
				+ "='" + value + "']");
		return nodes;
	}

	/**
	 * 获取节点名称为nodeName的attrName属性 获取节点名称为nodeName的attrName属性
	 * 条件：该节点里面有个属性为key值为value的属性 主要用于遍历查询结果
	 * 
	 * @param nodeName
	 * @param attrName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getNodeAttribute(String nodeName, String attrName,
			String key, String value, int index) {
		List<Node> nodes = mRoot.selectNodes("//" + nodeName + "[@" + key
				+ "='" + value + "']");
		return nodes.get(index).valueOf("@" + attrName);
	}

	/**
	 * 获取节点名称为nodeName的attrName属性 条件：该节点里面有个属性为key值为value的属性
	 * 用于处理获取重名节点属性的问题，4个参数均为查询条件 如果有多个满足条件的节点，只返回第一个
	 * 
	 * @param nodeName
	 * @param attrName
	 * @param key
	 * @param value
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getNodeAttribute(String nodeName, String attrName,
			String key, String value) {
		List<Node> nodes = mRoot.selectNodes("//" + nodeName + "[@" + key
				+ "='" + value + "']");
		return nodes.get(0).valueOf("@" + attrName);
	}

	// TODO 将某个节点的某个属性改成XXX
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static void setNodeAttribute(String nodeName, String attrName,
			String setValue, String key, String value) {
		List<Element> attrs = mRoot.selectNodes("//" + nodeName + "[@" + key
				+ "='" + value + "']");
		attrs.get(0).setAttributeValue(attrName, setValue);
		writeXMLFile();
	}

	private static void writeXMLFile() {
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
//			OutputFormat format = OutputFormat.createCompactFormat();
			format.setEncoding("UTF-8");
			FileOutputStream output = new FileOutputStream(new File(mPath));
			XMLWriter writer;
			writer = new XMLWriter(output, format);
			writer.write(mDoc);
			writer.flush();
			writer.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}