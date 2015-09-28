package com.pactera.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 录音处理任务
 * @author Administrator
 *
 */
public class QuartzJob {
	
	private static Log log = LogFactory.getLog(QuartzJob.class);
	
	private static  final String FTP_PATH = "voice";
	
	public static final URL URL_ROOT = QuartzJob.class.getResource("/");
	
	public static final String PATH_CLASS_ROOT = URL_ROOT.getPath();
	public static final String File_Path = PATH_CLASS_ROOT.substring(0,PATH_CLASS_ROOT.length());
	public void work()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(new Date());
		String appRes = QuartzJob.File_Path.split("WEB-INF")[0]+"voice/UpLoad";
		//log.debug(new Date()+"****开始处理*****"+appRes);
		//迭代目录下所有的录音
		File path = new File(appRes);
		File[] files = path.listFiles();
		for (File file : files) 
		{
			String sfId = file.getName().split("_")[0];
			if(file.getName().endsWith(".amr"))
			{
				String srcVoice = file.getAbsolutePath();
				String target = file.getAbsolutePath().split(".amr")[0]+".mp3";
				FTPUtil.changeToMp3(srcVoice, target);
				log.debug(new Date()+"****开始处理*****"+target);
				file = new File(target);
			}
			String res = this.modifyVoicePath(sfId,FTP_PATH+"/"+today+"/"+file.getName());
			log.debug(new Date()+"****更新路径录音地址返回*****"+res);
			JSONObject jObject = JSONObject.fromObject(res);
			String result = jObject.getString("retCount");
			if(CommonUtil.isNotEmpty(result) && "1".equals(result))
			{
				log.debug(file.getName());
				boolean flag = this.uploadVoice(file);
				if(flag)
				{
					file.delete();
				}
			}
		}
	}
	/**
	 * 更新数据库
	 * @param coustomId
	 * @param filePath
	 * @return
	 */

	public String modifyVoicePath(String coustomId, String filePath) {
		String url = "/server/modifyVoicePath.action" +
		"?uuid=c4ca4238a0b923820dcc509a6f75849b";
		Map<String,String> con = new HashMap<String, String>();
		con.put("backRecode1",filePath);
		con.put("customerId", coustomId);
		String res = HttpUtil.URLPost(url, con, "");
		return res;
	}
	/**
	 * 录音上传
	 * @param file
	 */
	public boolean uploadVoice(File file)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(new Date());
		boolean flag = false;
		try {
			flag = FTPUtil.uploadFile(FTP_PATH,today, file.getName(),new FileInputStream(file));
		} catch (FileNotFoundException e) {
			log.error("文件未找到："+":::"+file.getName());
		}
		return flag;
	}
}
