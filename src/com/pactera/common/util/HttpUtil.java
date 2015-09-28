package com.pactera.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServlet;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;

/**
 * HTTP工具类
 * 
 * @author cc
 * 
 */
public class HttpUtil{

	private static Log log = LogFactory.getLog(HttpUtil.class);
	
	/**
	 * 定义编码格式 UTF-8
	 */
	public static final String URL_PARAM_DECODECHARSET_UTF8 = "UTF-8";
	
	/**
	 * 定义编码格式 GBK
	 */
	public static final String URL_PARAM_DECODECHARSET_GBK = "GBK";
	
	private static final String URL_PARAM_CONNECT_FLAG = "&";
	
	private static final String EMPTY = "";

	private static MultiThreadedHttpConnectionManager connectionManager = null;

	private static int connectionTimeOut = 25000;

	private static int socketTimeOut = 25000;

	private static int maxConnectionPerHost = 20;

	private static int maxTotalConnections = 20;

	private static HttpClient client;

	private static Properties property;
	
	private static String bpoServerPath;
	
	private static String appId;
	private static String appkey;
	private static String master;
	private static String host;
	
	static{
		connectionManager = new MultiThreadedHttpConnectionManager();
		connectionManager.getParams().setConnectionTimeout(connectionTimeOut);
		connectionManager.getParams().setSoTimeout(socketTimeOut);
		connectionManager.getParams().setDefaultMaxConnectionsPerHost(maxConnectionPerHost);
		connectionManager.getParams().setMaxTotalConnections(maxTotalConnections);
		client = new HttpClient(connectionManager);
		
		property = new Properties();
		try {
			property.load(HttpUtil.class.getResourceAsStream("/bpoServer.properties"));
			bpoServerPath = property.getProperty("bpoServerPath");
			appId = property.getProperty("appId");
			appkey = property.getProperty("appkey");
			master = property.getProperty("master");
			host = property.getProperty("host");
			
		} catch (IOException e) {
			log.error("***********初始化失败************");
		}
		
	}
	
	
	
	/**
	 * POST方式提交数据
	 * @param url
	 * 			待请求的URL
	 * @param params
	 * 			要提交的数据
	 * @param enc
	 * 			编码
	 * @return
	 * 			响应结果
	 * @throws IOException
	 * 			IO异常
	 */
	public static String URLPost(String url, Map<String, String> params, String enc){
		url = bpoServerPath+url;
		String response = EMPTY;		
		PostMethod postMethod = null;
		
		try {
			postMethod = new PostMethod(url);
			//log.debug("发出的请求："+url);
			postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + URL_PARAM_DECODECHARSET_UTF8);
			//将表单的值放入postMethod中
			Set<String> keySet = params.keySet();
			for(String key : keySet){
				String value = params.get(key);
				postMethod.addParameter(key, value);
			}			
			//执行postMethod
			int statusCode = client.executeMethod(postMethod);
			if(statusCode == HttpStatus.SC_OK) {
				byte[] ba = postMethod.getResponseBody();
				response = new String(ba,URL_PARAM_DECODECHARSET_UTF8);  
			}else{
				log.error("响应状态码 = " + postMethod.getStatusCode());
			}
		}catch(HttpException e){
			log.error("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
			return "网络发生异常";
		}catch(Exception e){
			log.error("发生网络异常", e);
			return "网络发生异常";
		}finally{
			if(postMethod != null){
				postMethod.releaseConnection();
				postMethod = null;
			}
		}
		
		return response;
	}
	
	/**
	 * GET方式提交数据
	 * @param url
	 * 			待请求的URL
	 * @param params
	 * 			要提交的数据
	 * @param enc
	 * 			编码
	 * @return
	 * 			响应结果
	 * @throws IOException
	 * 			IO异常
	 */
	public static String URLGet(String url, Map<String, String> params, String enc){

		String response = EMPTY;
		GetMethod getMethod = null;		
		StringBuffer strtTotalURL = new StringBuffer(EMPTY);
		
	    if(strtTotalURL.indexOf("?") == -1) {
	      strtTotalURL.append(url).append("?").append(getUrl(params, enc));
	    } else {
	    	strtTotalURL.append(url).append("&").append(getUrl(params, enc));
	    }
	    log.debug("GET请求URL = \n" + strtTotalURL.toString());
	    
		try {
			getMethod = new GetMethod(strtTotalURL.toString());
			getMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + enc);
			//执行getMethod
			int statusCode = client.executeMethod(getMethod);
			if(statusCode == HttpStatus.SC_OK) {
				response = getMethod.getResponseBodyAsString();
			}else{
				log.debug("响应状态码 = " + getMethod.getStatusCode());
			}
		}catch(HttpException e){
			log.error("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
			e.printStackTrace();
		}catch(IOException e){
			log.error("发生网络异常", e);
			e.printStackTrace();
		}finally{
			if(getMethod != null){
				getMethod.releaseConnection();
				getMethod = null;
			}
		}
		
		return response;
	}	

	/**
	 * 据Map生成URL字符串
	 * @param map
	 * 			Map
	 * @param valueEnc
	 * 			URL编码
	 * @return
	 * 			URL
	 */
	private static String getUrl(Map<String, String> map, String valueEnc) {
		
		if (null == map || map.keySet().size() == 0) {
			return (EMPTY);
		}
		StringBuffer url = new StringBuffer();
		Set<String> keys = map.keySet();
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			String key = it.next();
			if (map.containsKey(key)) {
				String val = map.get(key);
				String str = val != null ? val : EMPTY;
				try {
					str = URLEncoder.encode(str, valueEnc);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				url.append(key).append("=").append(str).append(URL_PARAM_CONNECT_FLAG);
			}
		}
		String strURL = EMPTY;
		strURL = url.toString();
		if (URL_PARAM_CONNECT_FLAG.equals(EMPTY + strURL.charAt(strURL.length() - 1))) {
			strURL = strURL.substring(0, strURL.length() - 1);
		}
		
		return (strURL);
	}
	
	/**
	 * 个推
	 * @param cid 客户机个推ID
	 * @param title 推送标题
	 * @param text 推送内容
	 * @throws Exception
	 */
	public static void pushtoSingle(String cid,String title,String text) throws Exception
	{
		IGtPush push = new IGtPush(host,appkey, master);
		NotificationTemplate template = MyNotificationTemplate(title,text);
		SingleMessage message = new SingleMessage();
		message.setOffline(true);
		message.setOfflineExpireTime(2 * 1000 * 3600);
		message.setData(template);
		message.setPushNetWorkType(0); //

		Target target1 = new Target();
		target1.setAppId(appId);
		target1.setClientId(cid);
		try {
			IPushResult ret = push.pushMessageToSingle(message, target1);
			log.debug("正常：" + ret.getResponse().toString());
			
		} catch (RequestException e) {
			String requstId = e.getRequestId();
			IPushResult ret = push.pushMessageToSingle(message, target1,
					requstId);
			log.error("异常：" + ret.getResponse().toString());
		}
		try {
			Thread.sleep(3);
		} catch (InterruptedException e) {
		}
	}
	public static NotificationTemplate MyNotificationTemplate(String title,String text) throws Exception {
		NotificationTemplate template = new NotificationTemplate();
		template.setAppId(appId);
		template.setAppkey(appkey);
		template.setTitle(title);
		template.setText(text);
		template.setLogo("icon.png");
		template.setTransmissionType(1);
		template.setTransmissionContent("dddd");
		return template;
	}
	
	public static void main(String[] args) {
	}
}
