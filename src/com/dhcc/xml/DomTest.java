package com.dhcc.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

// ~ File Information
// ====================================================================================================================

public class DomTest {

	public static void main(String[] args) throws Exception {
		
		//step1:获得dom解析器工厂（工厂的作用是创建具体的解析器）
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		//step2:获得具体的dom解析器
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		//setp3:解析一个xml文档，获得document对象（根节点）
		Document document = db.parse(new File("D:/AppConfig.xml"));
		
		NodeList list = document.getElementsByTagName("APPConfig");
		
		for(int i = 0 ; i < list.getLength(); i++){
			
			Element element = (Element)list.item(i);
			
			String content = element.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
			
			System.out.println(content);
			
		}
	}

}
