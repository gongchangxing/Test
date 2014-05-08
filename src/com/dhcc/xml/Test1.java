package com.dhcc.xml;

import java.io.FileOutputStream;
import java.io.FileWriter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class Test1 {

	public static void main(String[] args) throws Exception {

		// 创建文档，并创建文档的根元素节点：第一种方式：
		// Document document = DocumentHelper.createDocument();

		// Element root = DocumentHelper.createElement("student");

		// document.setRootElement(root);
		// 第二种方式：两种方式本质上是一样的。只是赋予根节点的时间不一样而已。

		Element root = DocumentHelper.createElement("studnet");
		Document document = DocumentHelper.createDocument(root);

		root.addAttribute("name", "zhangsan");

		Element helloElement = root.addElement("hello");

		Element worldElement = root.addElement("world");

		helloElement.setText("hello");

		worldElement.setText("world");

		helloElement.addAttribute("age", "20");

		XMLWriter xmlWriter = new XMLWriter();

		xmlWriter.write(document);

		OutputFormat format = new OutputFormat(" ", true);

		XMLWriter xmlWriter2 = new XMLWriter(new FileOutputStream("D://student2.xml"), format);

		xmlWriter2.write(document);

		XMLWriter xmlWriter3 = new XMLWriter(new FileWriter("D://student3.xml"), format);

		xmlWriter3.write(document);
		
		xmlWriter3.close();

	}

}
