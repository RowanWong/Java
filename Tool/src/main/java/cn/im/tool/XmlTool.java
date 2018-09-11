package cn.im.tool;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * xml相关操作
 * @author SYSTEM
 *
 */
public class XmlTool {

	//使用SAXReader工具读取xml         导入dom4j.jar包
	public static final void readXmlDemo(String xml) throws UnsupportedEncodingException, DocumentException{
		 Element rootEle  = new SAXReader().read(new ByteArrayInputStream(xml.getBytes("UTF-8"))).getRootElement();
		 List<Element> list = rootEle.element("Articles").elements();   
		 for (Element e : list) {			
			 e.elementTextTrim("title");
		}
	}
}
