package com.xwtech.uomp.base.util.order;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 *@ClassName:XmlTools.java
 *@Description：
 *@author: Mars
 *@date： date：2013-12-19 time：上午09:14:53
 *@version 1.0
 */
public class XmlTools {

	private transient static Logger log = Logger.getLogger(Parameter.class);

	/**
	 * XML encoding，默认为“GB2312”
	 */
	private String encoding = "UTF-8";

	/**
	 * RPC调用请求参数（输入参数） － 在调用packRequest、parseRequest时赋值
	 */
	private ParamList inParamList = new ParamList();

	/**
	 * XML文件元素，"params"
	 */
	private static final String ELEMENT_PARAMS = "params";
	/**
	 * XML文件元素，"para"，"params"的子元素
	 */
	private static final String ELEMENT_PARA = "para";

	/**
	 * 取RPC调用的输入参数
	 * 
	 * @return 输入参数
	 */
	public ParamList getInParamList()
	{
		if ( inParamList != null && inParamList.size() > 0 )
			return inParamList;
		else
			return null;
	}

	/**
	 * 获取XML文件的encoding类型
	 * 
	 * @return encoding
	 */
	public String getEncoding()
	{
		return encoding;
	}

	/**
	 * 设置XML文件的encoding类型
	 * 
	 * @param encoding
	 *            encoding类型
	 */
	public void setEncoding(final String encoding)
	{
		this.encoding = encoding;
	}

	// /////////////////////////////////////////////////////////////////////////

	/**
	 * 将一个RPC调用请求封装成XML
	 * 
	 * @param servName
	 *            RPC调用的名称
	 * @param inParams
	 *            RPC调用的输入参数
	 * @param outParams
	 *            RPC调用的输出参数
	 * @return 包含为XML的字符串
	 */
	public String packRequest()
	{

		String retString = null;

		try
		{
			final Document document = DocumentHelper.createDocument();
			Element xeParams = document.addElement(ELEMENT_PARAMS);

			if ( this.inParamList != null )
			{
				for (int i = 0; i < this.inParamList.size(); i++)
				{
					// inParamList.add(inParams.get(i));
					packOneParam(xeParams, this.inParamList.get(i));
				}
			}
			else
				xeParams.addText("");

			retString = xml2String(document);
		}
		catch (Exception e)
		{
			retString = "";
			log.error(e);
			e.printStackTrace();
		}
		return retString;
	}

	/**
	 * 从XML文件中解析出RPC调用请求的各参数
	 * 
	 * @param xmlString
	 *            内容为XML的字符串
	 * @return 解析是否成功（不保证参数正确）
	 */
	public boolean parseRequest(final String xmlString)
	{
		if ( log.isDebugEnabled() )
			log.info("<parseRequest> : \n" + xmlString);

		boolean rtn = false;

		if ( xmlString != null && xmlString.length() > 0 )
		{
			final SAXReader reader = new SAXReader();
			try
			{
				final InputStream xmlFile = new ByteArrayInputStream(xmlString.getBytes());
				final Document document = reader.read(xmlFile);
				final Element root = document.getRootElement();
				if ( root != null )
				{
					inParamList = new ParamList();
					getInParams(root);

				}

				rtn = true;
			}
			catch (Exception e)
			{

			}
		}
		return rtn;
	}

	/**
	 * 将XML的Document转换成String
	 * 
	 * @param document
	 *            XML文件
	 * @return 转换后的String
	 */
	public String xml2String(final Document document)
	{
		final StringWriter retDocs = new StringWriter();
		try
		{
			// XML文件格式
			final OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding(encoding);
			// XML文件内容
			final XMLWriter writer = new XMLWriter(retDocs, format);
			writer.write(document);
			writer.close();
		}
		catch (Exception e)
		{
			log.error("<xml2String>: " + e);
		}

		return retDocs.toString();
	}

	/**
	 * 将XML的Document转换成String 具体实现见overload方法: transXMLtoString( final Document
	 * document )
	 * 
	 * @param root
	 *            根元素
	 * @return XML字符串
	 */
	public String xml2String(final Element root)
	{
		return xml2String(root.getDocument());
	}

	/**
	 * 将一个参数写入XML文件
	 * 
	 * @param params
	 *            XML文件的一个Element
	 * @param oneParam
	 *            一个输入或输出参数
	 */
	private static void packOneParam(final Element params, final Parameter oneParam)
	{
		final Element para = params.addElement(ELEMENT_PARA);
		if ( oneParam != null )
		{
			int count = 0;
			if ( oneParam.getValues() != null )
			{
				count = oneParam.getValues().size();
				for (int i = 0; i < count; i++)
				{
					if ( oneParam.getValue(i) != null )
						para.addElement("value").addCDATA(oneParam.getValue(i));
					else
						para.addElement("value").addCDATA("");
				}
			}
			para.addAttribute("name", oneParam.getName());
			para.addAttribute("count", Integer.toString(count));
		}
	}

	/**
	 * 从XML的Element中解析出RPC调用请求参数
	 * 
	 * @param element
	 *            XML文件的一个Element
	 */
	private void getInParams(final Element element)
	{
		if ( element == null )
			return;

		if ( element.getName() != null && element.getName().equals(ELEMENT_PARAMS) )
		{
			inParamList = new ParamList();
			final List elements = element.elements(ELEMENT_PARA);
			if ( elements != null )
			{
				for (int i = 0; i < elements.size(); i++)
				{
					final Parameter tmpPara = getOnePara((Element) elements.get(i));
					if ( tmpPara != null )
						inParamList.add(tmpPara);
				}
			}
		}
		else
		{
			final List elements = element.elements(ELEMENT_PARAMS);
			if ( elements != null && elements.size() == 1 )
				getInParams((Element) elements.get(0));

		}
	}

	/**
	 * 从XML的Element中解析出RPC请求或响应的一个参数
	 * 
	 * @param element
	 *            XML文件的一个Element
	 * @return 解析出的一个参数
	 */
	private static Parameter getOnePara(final Element element)
	{
		Parameter para = null;
		if ( element != null )
		{
			if ( element.getName() != null && element.getName().equals(ELEMENT_PARA) )
			{
				para = new Parameter(element.attributeValue("name"));
				for (Iterator i = element.elementIterator(); i.hasNext();)
				{
					para.addValue(getParaValue((Element) i.next()));
				}
			}
		}
		return para;
	}

	/**
	 * 从XML的Element中解析出RPC请求或响应的一个参数中的一个值
	 * 
	 * @param element
	 *            XML文件的一个Element
	 * @return 参数的值
	 */
	private static String getParaValue(final Element element)
	{
		String paraValue = null;
		if ( element != null )
		{
			if ( element.getName() != null && element.getName().equals("value") )
			{
				paraValue = (String) element.getData();
			}
		}
		return paraValue;
	}

	/**
	 * 添加参数
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public XmlTools addParameter(String key, String value)
	{
		if ( key != null && !"".equals(key.trim()) )
		{
			if ( this.inParamList == null )
			{
				this.inParamList = new ParamList();
			}

			this.inParamList.add(new Parameter(key, value));
		}
		return this;
	}

	/**
	 * 获得参数内容
	 * 
	 * @param key
	 * @return
	 */
	public String getParameter(String key)
	{
		if ( this.inParamList != null )
		{
			Parameter parameter = this.inParamList.get(key);
			return parameter == null ? null : parameter.getValue();
		}
		return null;
	}

	/**
	 * 清除所有参数
	 */
	public void clear()
	{
		this.inParamList = new ParamList();
	}

	public String toString()
	{
		return this.inParamList == null ? "" : this.inParamList.toString();
	}

	public static void main(String[] args)
	{
		XmlTools xmlTools = new XmlTools();

		xmlTools.addParameter("keyIndex", "1");
		xmlTools.addParameter("keyVersion", "1");
		xmlTools.addParameter("keyType", "11");
		xmlTools.addParameter("lsData", "4201010001000091");
		xmlTools.addParameter("ivData", "3030303030303031");
		xmlTools.addParameter("macData", "30303030303031303030303230303032");

		System.out.println(xmlTools.packRequest());
		System.out.println("----------");
		xmlTools.parseRequest(xmlTools.packRequest());
		System.out.println(xmlTools.toString());
		System.out.println("----------");
		System.out.println(xmlTools.getParameter("lsData"));
	}



}
