package com.zlt.test.xmlparse;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlParseDom {

	public static List<Student> parseXml(InputStream in) throws Exception {
		List<Student> students = new ArrayList<Student>();
		Student student = null;
		String tempGrade, tempClass;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(in);

		Element element = document.getDocumentElement();

		NodeList nodeList = element.getElementsByTagName("grade");
		for (int i = 0; i < nodeList.getLength(); i++) {

			Element gradeItem = (Element) nodeList.item(i); // 获取每一个grade节点
			tempGrade = gradeItem.getAttribute("name");// 获取grade节点值
			NodeList classList = gradeItem.getElementsByTagName("class");// 获取所有的class节点
			for (int j = 0; j < classList.getLength(); j++) {

				Element classItem = (Element) classList.item(j); // 获取每一个class节点
				tempClass = classItem.getAttribute("name");// 获取class节点值
				NodeList studentList = classItem
						.getElementsByTagName("student"); // 获取所有的student节点
				for (int k = 0; k < studentList.getLength(); k++) {
					student = new Student();
					student.setGrade(tempGrade);
					student.setClazz(tempClass);
					for (int l = 0; l < studentList.item(k).getChildNodes()
							.getLength(); l++) {
						Node childNode = studentList.item(k).getChildNodes()
								.item(l);//获取student节点下的支节点
						if (childNode.getNodeType() == Node.ELEMENT_NODE) {
							if ("name".equals(childNode.getNodeName())) {
								student.setName(childNode.getFirstChild()
										.getNodeValue());
							} else if ("sex".equals(childNode.getNodeName())) {
								student.setSex(childNode.getFirstChild()
										.getNodeValue());
							}
						}
					}
					students.add(student);
					student = null;
				}

			}

		}
		return students;
	}

}
