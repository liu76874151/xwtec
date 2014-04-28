package com.xwtech.uomp.base.util.order;




import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class Sendhttp {


	/**
	 * 日志对象
	 */
//	private static final Logger LOG = Logger.getLogger(Sendhttp.class);

	/**
	 * 发送http求，通过get方式发送参数
	 * 
	 * @param httpurl
	 *            请求对象
	 * @param params
	 *            参数
	 * @return
	 */
	public static String send(String httpurl, Map<String, String> params)
	{
		URL url = null;
		HttpURLConnection conn = null;
		OutputStream output = null;
		BufferedReader in = null;
		try
		{
			// 设置参数信息
			StringBuilder sb = new StringBuilder();

			// 设置参数
			sb = setReqParam(params, sb);

			if ( sb.length() > 0 )
			{
				url = new URL(httpurl + "?" + sb.toString());
			}
			else
			{
				url = new URL(httpurl);
			}

			conn = getHttpConn(url);

			output = conn.getOutputStream();

			output.flush();

			int responseCode = conn.getResponseCode();
			if ( responseCode == HttpURLConnection.HTTP_OK )
			{
				in = new BufferedReader(new InputStreamReader(conn
				        .getInputStream(), "utf-8"));

				// 读取流记录
				return readStream(in);
			}
			return null;
		} catch (MalformedURLException e)
		{
		//	LOG.error("send MalformedURLException", e);
			e.printStackTrace();
		} catch (IOException e)
		{
			//LOG.error("send IOException", e);
			e.printStackTrace();
		} finally
		{
			url = null;
			try
			{
				if ( null != in )
				{
					in.close();
				}
				if ( null != conn )
				{
					conn.disconnect();
				}
			} catch (Exception ex)
			{
				//LOG.error("send Exception", ex);
				conn = null;
			}

			if ( null != output )
			{
				try
				{
					output.close();
				} catch (IOException e)
				{
					//LOG.error("send Exception", e);
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 设置请求参数
	 * 
	 * @param params
	 *            参数
	 * @param sb
	 *            填充字符
	 * @return 结果
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unused")
	private static StringBuilder setReqParam(Map<String, String> params,
	        StringBuilder sb) throws UnsupportedEncodingException
	{
		if ( params != null && !params.isEmpty() )
		{

			Iterator<Entry<String, String>> iterator = params.entrySet()
			        .iterator();
			String key = null;
			while (iterator.hasNext())
			{
				Entry<String, String> entry = iterator.next();
				key = entry.getKey();
				sb.append("&");
				sb.append(key);
				sb.append("=");
				sb.append(URLEncoder.encode(entry.getValue(), "UTF-8"));

			}

			if ( sb.length() > 0 )
			{
				sb.deleteCharAt(0);
			}

		}
		return sb;
	}
	public static String moveSendPost(String httpurl, Map<String, Object> params) throws Exception
	{

		URL url = null;
		HttpURLConnection conn = null;
		PrintWriter pw = null;
		try
		{

			StringBuffer sb = new StringBuffer("");
			if ( params != null && !params.isEmpty() )
			{

				Iterator<String> iterator = params.keySet().iterator();
				String key = null;
				while (iterator.hasNext())
				{
					key = iterator.next();
					sb.append("&");
					sb.append(key);
					sb.append("=");
					sb.append(URLEncoder.encode((String) params.get(key), "UTF-8"));

				}

				if ( sb.length() > 0 )
				{
					sb.deleteCharAt(0);
				}
			}
			System.out.println(sb.toString()+"====sb.toString()=====");
			url = new URL(httpurl);

			conn = getHttpPostConn(url);

			conn.connect();

			DataOutputStream out = new DataOutputStream(conn.getOutputStream());

			out.writeBytes(sb.toString());
			out.flush();
			out.close();

			int responseCode = conn.getResponseCode();
			if ( responseCode == HttpURLConnection.HTTP_OK )
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
				StringBuffer sbuff = new StringBuffer(2048);

				int len = 1024;
				char[] cbuf = new char[len];
				int read = -1;
				while ((read = in.read(cbuf, 0, len)) != -1)
				{
					sbuff.append(cbuf, 0, read);
				}

				in.close();
				conn.disconnect();
				// System.out.println("--" + sbuff.toString() + "--");
				return (sbuff.toString() == null ? "" : sbuff.toString().trim());
			}
		}
		finally
		{
			url = null;
			try
			{
				if ( pw != null )
				{
					pw.close();
					pw = null;
				}
				conn.disconnect();
			}
			catch (Exception ex)
			{
				conn = null;
			}
		}
		return null;
	}
	
	/**
	 * 发送post请求
	 * 
	 * @param httpurl
	 *            通讯地址
	 * @param params
	 *            参数
	 * @return
	 */
	public static String sendPost(String httpurl, Map<String, String> params)
	{
		// 得到一个返回流对象
		BufferedReader in = sendPostRetStream(httpurl, params);

		// 读取流对象
		return readStream(in);
	}

	/**
	 * 读取流的数据将其转化为String类型数据
	 * 
	 * @param in
	 *            读取的流对象
	 * @return 结果信息
	 */
	private static String readStream(BufferedReader in)
	{
		StringBuilder result = new StringBuilder();

		int len = 100;
		char[] cbuf = new char[len];
		int read = -1;
		try
		{
			while ((read = in.read(cbuf, 0, len)) != -1)
			{
				result.append(cbuf, 0, read);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return result.toString();
	}

	/**
	 * 发送一个请求，并返回一个写入流对象
	 * 
	 * @param httpurl
	 *            请求对象
	 * @param params
	 *            参数
	 * @return
	 */
	public static BufferedReader sendPostRetStream(String httpurl,
	        Map<String, String> params)
	{
		URL url = null;
		HttpURLConnection conn = null;
		DataOutputStream out = null;
		try
		{
			// 设置参数信息
			StringBuilder sb = new StringBuilder();

			// 设置参数
			sb = setReqParam(params, sb);

			url = new URL(httpurl);

			conn = getHttpPostConn(url);
			conn.connect();

			out = new DataOutputStream(conn.getOutputStream());
//System.out.println(sb.toString()+"===sb.toString()====");
			// 写入参数
			out.writeBytes(sb.toString());
			out.flush();

			int responseCode = conn.getResponseCode();
			if ( responseCode == HttpURLConnection.HTTP_OK )
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(
				        conn.getInputStream(), "utf-8"));

				return in;
			}
			return null;
		} catch (MalformedURLException e)
		{
			//LOG.error("send MalformedURLException", e);
			e.printStackTrace();
		} catch (IOException e)
		{
			//LOG.error("send IOException", e);
			e.printStackTrace();
		} finally
		{
			url = null;
			if ( null != out )
			{
				try
				{
					out.close();
				} catch (IOException e)
				{
					//LOG.error("IOException", e);
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 得到http请求对象，此连接一般用于小流量请求
	 * 
	 * @param url
	 *            地址
	 * @return 连接对象
	 * @throws IOException
	 *             异常
	 */
	private static HttpURLConnection getHttpConn(URL url) throws IOException
	{
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setConnectTimeout(30000);
		conn.setReadTimeout(60000);
		conn.setRequestProperty("Connection", "Close");
		conn.setRequestProperty("Content-Type", "text/XML;");
		return conn;
	}

	/**
	 * 得到请求对象，一般用于响应的大量信息，一般为网络流对象
	 * 
	 * @param url
	 *            请求地址对象
	 * @return 连接
	 * @throws IOException
	 *             异常
	 */
	private static HttpURLConnection getHttpPostConn(URL url)
	        throws IOException
	{
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		// 设置是否向connection输出，因为这个是post请求，参数要放在
		// http正文内，因此需要设为true
		conn.setDoOutput(true);
		// Post 请求不能使用缓存
		conn.setUseCaches(false);
		conn.setConnectTimeout(30000);
		conn.setReadTimeout(60000);
		// URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数
		conn.setInstanceFollowRedirects(true);
		conn.setRequestProperty("Content-Type",
		        "application/x-www-form-urlencoded");

		return conn;

	}



}
