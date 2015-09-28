package com.pactera.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.xfire.client.Client;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class AgentServerUtil {
	
	private static Log log = LogFactory.getLog(AgentServerUtil.class);
	
	private static String agentServerPath;
	
	private static Properties property;
	
	static{
		property = new Properties();
		try {
			property.load(AgentServerUtil.class.getResourceAsStream("/bpoServer.properties"));
		} catch (IOException e) {
			log.error("***********��ʼ��ʧ��************");
		}
		agentServerPath = property.getProperty("agentServerPath");
	}
	
	public static String conAgentServer (String methods,String xmlStr) throws Exception
    {
		log.debug("conCustomerClient methods:"+methods+", xml: "+xmlStr);
    	Client client = null;
    	URL url = null;
    	String wsdl = agentServerPath;
    	String values = "";
    	url = new URL(wsdl);
    	HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection(); //������
        try{
            httpConnection.connect();
            
            client = new Client(httpConnection.getInputStream(),String.class); //��ȡ�ͻ���
            Object[] objs = client.invoke(methods, new Object[]{xmlStr});
            values = objs[0].toString();
            log.debug("conAgentServer methods:"+methods+" responseValue : "+values);
        }catch(Exception e)
        {
        	values = "";
        	log.error("conAgentServer error methods:"+methods+",xml="+xmlStr+",errorInfo="+e);
        }
        finally
        {
        	httpConnection.disconnect();
        }
        return values;
    }
	
	public static String mapToXML(Map<String,String> keyValues)
	{
		
		StringBuilder strBuilder = new StringBuilder();  
		String xmlBegin = "<?xml version='1.0' encoding=\"UTF-8\"?><root>";
		String xmlEnd = "</root>";
		strBuilder.append(xmlBegin);  
        Set<String> objSet = keyValues.keySet();  
        for (String key : objSet)  
        {  
            if (key == null)  
            {  
                continue;  
            }  
//            strBuilder.append("\n");  
            strBuilder.append("<").append(key.toString()).append(">");  
            Object value = keyValues.get(key);  
            strBuilder.append(coverter(value).trim());  
            strBuilder.append("</").append(key.toString()).append(">\n");  
        }  
        strBuilder.append(xmlEnd);  
        return strBuilder.toString();  
		
	}
	
	

	public static Map xmltoMapNew(String xml) {
		try {  
            Map map = new HashMap();  
            Document document = DocumentHelper.parseText(xml);  
            Element nodeElement = document.getRootElement();  
            List node = nodeElement.elements();  
            for (Iterator it = node.iterator(); it.hasNext();) {  
                Element elm = (Element) it.next();
                List cnode = elm.elements();
                for (Iterator cit = cnode.iterator(); cit.hasNext();) 
                {
                	Element celm = (Element) cit.next();
                	map.put(celm.getName(), celm.getText());  
                }
                map.put(elm.getName(), elm.getText());  
                elm = null;  
            }  
            node = null;  
            nodeElement = null;  
            document = null;  
            return map;  
        } catch (Exception e) {  
             
        }  
        return null;  
	}
	
	public static Map xmltoMap(String xml) {
		try {  
            Map map = new HashMap();  
            Document document = DocumentHelper.parseText(xml);  
            Element nodeElement = document.getRootElement();  
            List node = nodeElement.elements();  
            for (Iterator it = node.iterator(); it.hasNext();) {  
                Element elm = (Element) it.next();
                map.put(elm.getName(), elm.getText());  
                elm = null;  
            }  
            node = null;  
            nodeElement = null;  
            document = null;  
            return map;  
        } catch (Exception e) {  
             
        }  
        return null;  
	}
	
	/** 
     * Coverter. 
     * 
     * @param object the object 
     * @return the string 
     */  
    public static String coverter(Object object)  
    {  
        if (object instanceof Object[])  
        {  
            return coverter((Object[]) object);  
        }  
        if (object instanceof Collection)  
        {  
            return coverter((Collection<?>) object);  
        }  
        StringBuilder strBuilder = new StringBuilder();  
        if (isObject(object))  
        {  
            Class<? extends Object> clz = object.getClass();  
            Field[] fields = clz.getDeclaredFields();  
  
            for (Field field : fields)  
            {  
                field.setAccessible(true);  
                if (field == null)  
                {  
                    continue;  
                }  
                String fieldName = field.getName();  
                Object value = null;  
                try  
                {  
                    value = field.get(object);  
                }  
                catch (IllegalArgumentException e)  
                {  
                    continue;  
                }  
                catch (IllegalAccessException e)  
                {  
                    continue;  
                }  
                strBuilder.append("<").append(fieldName)  
                        .append(" className=\"").append(  
                                value.getClass().getName()).append("\">\n");  
                if (isObject(value))  
                {  
                    strBuilder.append(coverter(value));  
                }  
                else if (value == null)  
                {  
                    strBuilder.append("null\n");  
                }  
                else  
                {  
                    strBuilder.append(value.toString() + "\n");  
                }  
                strBuilder.append("</").append(fieldName).append(">\n");  
            }  
        }  
        else if (object == null)  
        {  
            strBuilder.append("null\n");  
        }  
        else  
        {  
            strBuilder.append(object.toString() + "\n");  
        }  
        return strBuilder.toString();  
    } 
    
    /** 
     * Checks if is object. 
     * 
     * @param obj the obj 
     * 
     * @return true, if is object 
     */  
    private static boolean isObject(Object obj)  
    {  
        if (obj == null)  
        {  
            return false;  
        }  
        if (obj instanceof String)  
        {  
            return false;  
        }  
        if (obj instanceof Integer)  
        {  
            return false;  
        }  
        if (obj instanceof Double)  
        {  
            return false;  
        }  
        if (obj instanceof Float)  
        {  
            return false;  
        }  
        if (obj instanceof Byte)  
        {  
            return false;  
        }  
        if (obj instanceof Long)  
        {  
            return false;  
        }  
        if (obj instanceof Character)  
        {  
            return false;  
        }  
        if (obj instanceof Short)  
        {  
            return false;  
        }  
        if (obj instanceof Boolean)  
        {  
            return false;  
        }  
        return true;  
    }  
    
    
    /**
	 * ������Url�������ļ�
	 * @param urlStr
	 * @param fileName
	 * @param savePath
	 * @throws IOException
	 */
	public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{
		try {
			URL url = new URL(urlStr);  
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
	                //���ó�ʱ��Ϊ3��
			conn.setConnectTimeout(3*1000);
			//��ֹ���γ���ץȡ���403����
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			//�õ�������
			InputStream inputStream = conn.getInputStream();  
			//��ȡ�Լ�����
			byte[] getData = readInputStream(inputStream);    
	
			//�ļ�����λ��
			File saveDir = new File(savePath);
			if(!saveDir.exists()){
				saveDir.mkdir();
			}
			File file = new File(saveDir+File.separator+fileName);    
			FileOutputStream fos = new FileOutputStream(file);     
			fos.write(getData); 
			if(fos!=null){
				fos.close();  
			}
			if(inputStream!=null){
				inputStream.close();
			}
		} catch (Exception e) {
			log.error("ͬ��AgentServer¼��ʧ��"+e.getMessage());
		}
	}



	/**
	 * ���������л�ȡ�ֽ�����
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static  byte[] readInputStream(InputStream inputStream) throws IOException {  
		byte[] buffer = new byte[1024];  
		int len = 0;  
		ByteArrayOutputStream bos = new ByteArrayOutputStream();  
		while((len = inputStream.read(buffer)) != -1) {  
			bos.write(buffer, 0, len);  
		}  
		bos.close();  
		return bos.toByteArray();  
	}  

	public static void main(String[] args) {
		try{
			String xml = "<?xml version='1.0' encoding=\"UTF-8\"?><root><record><recordURL>111111</recordURL></record></root>";
			Map<String,String> map =  AgentServerUtil.xmltoMap(xml);
			System.out.println(map.get("recordURL"));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
