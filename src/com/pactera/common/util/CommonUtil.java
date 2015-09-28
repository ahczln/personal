package com.pactera.common.util;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;


public class CommonUtil
{
	/**
     * ��ֱ�����û�����ָ����Ϣ��
     * 
     * @param response
     *            response
     * @param msg
     *            void
     */
    public static void returnMsg(HttpServletResponse response, String msg)
    {
        if (msg == null || "".equals(msg))
        {
            return;
        }
        Writer out = null;
        try
        {
            response.setCharacterEncoding("utf-8");
            out = response.getWriter();
            out.write(msg);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                out.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public static boolean isNotEmpty(String str)
    {
    	if(null != str && !"".equals(str))
    	{
    		return true;
    	}
    	return false;
    }
    
    public static String getRole(String roleName)
    {
    	Map<String,String> map = new HashMap<String,String>();
    	map.put("סԺҽ��", "0");
    	map.put("��������", "1");
    	map.put("Ժ�쵼", "2");
    	String res = map.get(roleName);
    	if(res == null)
    	{
    		return "0";
    	}
    	return res;
    }
    
    public static String getStrFromJson(JSONObject obj,String str)
    {
    	String res = "";
    	try {
    		res = obj.getString(str);
		} catch (Exception e) {
			res = "unknow";
		}
		return res;
    }
    

}