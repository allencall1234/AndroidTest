package com.zlt.test.xmlparse;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class XmlParseSax extends DefaultHandler {

	private List<Student> students;
	private Student student;
	private String tempGrade,tempClazz;
	private String tag;
	public static List<Student> parseXml(InputStream in) throws Exception, SAXException {
		// 先构建sax解析器工厂的实例
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// 获取解析器的实例
		SAXParser sParser = factory.newSAXParser();
		// 获取事件源的实例
		XMLReader reader = sParser.getXMLReader();
		// 构建事件处理器的实例
		// 将事件处理器设置给事件源
		// reader.setContentHandler(new XmlParseSax());
		// // 通过XmlReader的parse()注入xml数据源，并且触发事件
		// reader.parse(new InputSource(in));
		XmlParseSax handler = new XmlParseSax();

		sParser.parse(in, handler);
		
		return handler.getStudents();
	}
	
	public List<Student> getStudents() {
		return students;
	}
	
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		students = new ArrayList<Student>();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		if ("grade".equals(localName)) {
			tempGrade = attributes.getValue(0);
		}else if ("class".equals(localName)) {
			tempClazz = attributes.getValue(0);
		}else if ("student".equals(localName)) {
			student = new Student();
			student.setGrade(tempGrade);
			student.setClazz(tempClazz);
		}
		
		tag = localName;

	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		if (tag != null) {
			String text = new String(ch, start, length);
			if ("name".equals(tag)) {
				student.setName(text);
			}else if ("sex".equals(tag)) {
				student.setSex(text);
			}
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		if ("student".equals(localName)) {
			students.add(student);
			student = null;
		}
		tag = null;
	}
	
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}

}
