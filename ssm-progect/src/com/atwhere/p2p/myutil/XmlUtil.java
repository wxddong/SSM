/**
 * agreements. See the NOTICE file distributed with this work for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.atwhere.p2p.myutil;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * Common utilities for easily parsing XML without duplicating logic.
 * 
 * @author Scott Battaglia
 * @version $Revision: 11729 $ $Date: 2007-09-26 14:22:30 -0400 (Tue, 26 Sep 2007) $
 * @since 3.0
 */
public final class XmlUtil {

	private final static Logger logger = LoggerFactory.getLogger(XmlUtil.class);
	/**
	 * ?xml ??? map  ?? ??????
	 * @param xml
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> xml2Map(String xml) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Document document = DocumentHelper.parseText(xml);
			Element rootElement = document.getRootElement();
			for (Iterator<Element> it = rootElement.elementIterator(); it.hasNext();) {
				Element element = (Element) it.next();
				map.put(element.getName(), element.getText());
			}
		} catch (Exception e) {
			logger.error("xml2Map error:", e);
		}
		return map;
	}
	
	/**
	 * ?xml???map ?????
	 *
	 * @param xmlDoc
	 * @return
	 * @throws Exception
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> xmlStrToMap(String xmlDoc) throws Exception {
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xmlDoc);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Map map = new HashMap();
		if (doc == null)
			return map;
		Element rootElement = doc.getRootElement();
		element2map(rootElement, map);
		return map;
	}
	
	 /**
     * JavaBean???xml
     * ????UTF-8
     * @param obj
     * @return
     */
    public static String convertToXml(Object obj) {
        return convertToXml(obj, "UTF-8");
    }

    /**
     * JavaBean???xml
     * @param obj
     * @param encoding
     * @return
     */
    public static String convertToXml(Object obj, String encoding) {
        String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);

            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            result = writer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * xml???JavaBean
     * @param xml
     * @param c
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T converyToJavaBean(String xml, Class<T> c) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

	/***
	 * ????????????????????
	 *
	 * @param outele
	 * @param outmap
	 */
	public static Map element2map(Element outele, Map outmap) {
		List<Element> list = outele.elements();//???????0,1,2,3,....... ??????
		int size = list.size();
		if (size == 0) {//?????????????(outele?????????,??????????????,???????????????)
			outmap.put(outele.getName(), outele.getTextTrim());
		} else {
			Map<String, Object> innermap = new HashMap<String, Object>();
			for (Element ele1 : list) {
				String eleName = ele1.getName();
				Object obj = innermap.get(eleName);//???MASTER
				if (obj == null) {//?????MASTER??????,?????????MASTER????
					element2map(ele1, innermap);
				} else {
					if (obj instanceof Map) {//???????????list,??????????map????????list
						List<Map> list1 = new ArrayList<Map>();
						list1.add((Map) innermap.remove(eleName));
						element2map(ele1, innermap);
						list1.add((Map) innermap.remove(eleName));
						innermap.put(eleName, list1);
					} else {//???????map,?????list,????????????,???????else if ???????
						element2map(ele1, innermap);
						((List) obj).add(innermap);
					}
				}
			}
			outmap.put(outele.getName(), innermap);
		}
		return outmap;
	}
	

	@SuppressWarnings("unchecked")
	public static List<Map<String, String>> xml2MapList(String xml, String xPath) {
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		try {
			Document document = DocumentHelper.parseText(xml);
			List<Element> selectNodes = document.selectNodes(xPath);
			for (Element e : selectNodes) {
				Map<String, String> map = new HashMap<String, String>();
				for (Iterator<Element> it = e.elementIterator(); it.hasNext();) {
					Element element = (Element) it.next();
					map.put(element.getName(), element.getText());
				}
				mapList.add(map);
			}
		} catch (Exception e) {
			logger.error("xml2MapList error:", e);
		}
		return mapList;
	}

	/**
	 * Get an instance of an XML reader from the XMLReaderFactory.
	 * 
	 * @return the XMLReader.
	 */
	public static XMLReader getXmlReader() {
		try {
			return XMLReaderFactory.createXMLReader();
		} catch (final SAXException e) {
			throw new RuntimeException("Unable to create XMLReader", e);
		}
	}

	/**
	 * Retrieve the text for a group of elements. Each text element is an entry in a list.
	 * <p>
	 * This method is currently optimized for the use case of two elements in a list.
	 * 
	 * @param xmlAsString the xml response
	 * @param element the element to look for
	 * @return the list of text from the elements.
	 */
	public static List<String> getTextForElements(final String xmlAsString, final String element) {
		final List<String> elements = new ArrayList<String>(2);
		final XMLReader reader = getXmlReader();

		final DefaultHandler handler = new DefaultHandler() {

			private boolean foundElement = false;

			private StringBuilder buffer = new StringBuilder();

			public void startElement(final String uri, final String localName, final String qName,
					final Attributes attributes) throws SAXException {
				if (localName.equals(element)) {
					this.foundElement = true;
				}
			}

			public void endElement(final String uri, final String localName, final String qName) throws SAXException {
				if (localName.equals(element)) {
					this.foundElement = false;
					elements.add(this.buffer.toString());
					this.buffer = new StringBuilder();
				}
			}

			public void characters(char[] ch, int start, int length) throws SAXException {
				if (this.foundElement) {
					this.buffer.append(ch, start, length);
				}
			}
		};

		reader.setContentHandler(handler);
		reader.setErrorHandler(handler);

		try {
			reader.parse(new InputSource(new StringReader(xmlAsString)));
		} catch (final Exception e) {
			logger.error("error", e);
			return null;
		}

		return elements;
	}

	/**
	 * Retrieve the text for a specific element (when we know there is only one).
	 * 
	 * @param xmlAsString the xml response
	 * @param element the element to look for
	 * @return the text value of the element.
	 */
	public static String getTextForElement(final String xmlAsString, final String element) {
		final XMLReader reader = getXmlReader();
		final StringBuffer buffer = new StringBuffer();

		final DefaultHandler handler = new DefaultHandler() {

			private boolean foundElement = false;

			public void startElement(final String uri, final String localName, final String qName,
					final Attributes attributes) throws SAXException {
				if (localName.equals(element)) {
					this.foundElement = true;
				}
			}

			public void endElement(final String uri, final String localName, final String qName) throws SAXException {
				if (localName.equals(element)) {
					this.foundElement = false;
				}
			}

			public void characters(char[] ch, int start, int length) throws SAXException {
				if (this.foundElement) {
					buffer.append(ch, start, length);
				}
			}
		};

		reader.setContentHandler(handler);
		reader.setErrorHandler(handler);

		try {
			reader.parse(new InputSource(new StringReader(xmlAsString)));
		} catch (final Exception e) {
			logger.error("error", e);
			return null;
		}

		return buffer.toString();
	}

	public static void main(String[] args){ 
		try{
			String xmlStr = "<person><students><student><name>????</name><age>12</age></student></students><techer></techer></person>";
			Map<String, Object> xmlStrToMap = XmlUtil.xmlStrToMap(xmlStr);
			System.out.println(xmlStrToMap);
		}catch (Exception e){
			
		}
		
	}
}
