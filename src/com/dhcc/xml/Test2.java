package com.dhcc.xml;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.dom4j.io.SAXReader;


public class Test2 {

	public static void main(String[] args) throws Exception {
		
		SAXReader saxReader = new SAXReader();
		
		Document doc = saxReader.read(new File("D://student2.xml"));
		
		Element root = doc.getRootElement();
		
		System.out.println("root element"+root.getName());
		
		List childList = root.elements();
		
		System.out.println(childList.size());
		
		List childList2 = root.elements("hello");
		
		System.out.println(childList2.size());
		
		Element first = root.element("hello");
		
		System.out.println(first.attributeValue("age"));
		
		for(Iterator iter = root.elementIterator();iter.hasNext();){
			
			Element e = (Element)iter.next();
			
			System.out.println(e.attributeValue("age"));
			
		}
		
		System.out.println("-----------------------------");
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		org.w3c.dom.Document document = db.parse(new File("D://student2.xml"));  
		
		DOMReader domReader = new DOMReader();
		
		//将jaxp的document转换为dom4J的document
		
		Document d = domReader.read(document);
		
		Element rootEle = d.getRootElement();
		
		System.out.println(rootEle.getName());
		
		
		
		
		
		
		
	}

}
