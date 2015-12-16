package com.zlt.test.xmlparse;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class XmlParsePull {

	public static List<Student> parseXml(InputStream in) throws Exception {
		List<Student> students = null;
		Student student = null;
		String tempGrade = "", tempClass = "";

		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();

		parser.setInput(in, "utf-8");

		int eventType = parser.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				students = new ArrayList<Student>();
				break;
			case XmlPullParser.START_TAG:
				if ("grade".equals(parser.getName())) {
					tempGrade = parser.getAttributeValue(0);
				} else if ("class".equals(parser.getName())) {
					tempClass = parser.getAttributeValue(0);
				} else if ("student".equals(parser.getName())) {
					student = new Student();
					student.setGrade(tempGrade);
					student.setClazz(tempClass);
				} else if ("name".equals(parser.getName())) {
					student.setName(parser.nextText());
				} else if ("sex".equals(parser.getName())) {
					student.setSex(parser.nextText());
				}

				break;
			case XmlPullParser.END_TAG:
				if ("student".equals(parser.getName())) {
					students.add(student);
					student = null;
				}
				break;
			default:
				break;
			}

			eventType = parser.next();
		}
		return students;
	}

}
