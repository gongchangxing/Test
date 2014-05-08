package com.dhcc.xml;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxTest1 {

	public static void main(String[] args) throws Exception {

		// step1:获得sax解析工厂实例
		SAXParserFactory factory = SAXParserFactory.newInstance();

		// step2:获取sax解析器实例
		SAXParser parse = factory.newSAXParser();

		// step3:开始进行解析

		parse.parse(new File("D://AppConfig.xml"), new MyHandler());
	}

}

class MyHandler extends DefaultHandler {

	public void startDocument() {

		System.out.println("parse begin");
	}

	public void endDocument() {

		System.out.println("parse finished");
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println("start element");
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("finish element");
	}

}
