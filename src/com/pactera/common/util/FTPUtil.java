package com.pactera.common.util;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;



/**
 * 
 * 〈FTP工具类>
 * 
 */
public class FTPUtil
{
	private static Log log = LogFactory.getLog(HttpUtil.class);
	
	private static Properties property;
	
	private static String ftp_ip;
	
	private static String ftp_port;
	
	private static String ftp_user;
	
	private static String ftp_pwd;
	
	static{
		property = new Properties();
		try {
			property.load(FTPUtil.class.getResourceAsStream("/bpoServer.properties"));
		} catch (IOException e) {
			log.error("***********初始化失败************");
		}
		ftp_ip = property.getProperty("ftp_ip");
		ftp_port = property.getProperty("ftp_port");
		ftp_user = property.getProperty("ftp_user");
		ftp_pwd = property.getProperty("ftp_pwd");
	}
	
	private static Logger logger = Logger.getLogger(FTPUtil.class);
	/**
	* Description: 向FTP服务器上传文件
	* @param url FTP服务器hostname
	* @param port FTP服务器端口
	* @param username FTP登录账号
	* @param password FTP登录密码
	* @param path FTP服务器保存目录
	* @param filename 上传到FTP服务器上的文件名
	* @param input 输入流
	* @return 成功返回true，否则返回false
	*/ 
	public static boolean uploadFile( String path,String today, String filename, InputStream input) { 
	    boolean success = false; 
	    FTPClient ftp = new FTPClient(); 
	    try { 
	        int reply; 
	        ftp.connect(ftp_ip, Integer.valueOf(ftp_port));//连接FTP服务器 
	        //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器 
	        ftp.login(ftp_user, ftp_pwd);//登录 
	        reply = ftp.getReplyCode(); 
	        if (!FTPReply.isPositiveCompletion(reply)) { 
	            ftp.disconnect(); 
	            return success; 
	        } 
	        boolean change = ftp.changeWorkingDirectory(path);
            if ( !change)
            {
            	ftp.makeDirectory(path);
            	change = ftp.changeWorkingDirectory(path);
            }
            
            boolean flag = ftp.changeWorkingDirectory(today);
            if ( !flag)
            {
            	ftp.makeDirectory(today);
            	flag = ftp.changeWorkingDirectory(today);
            }
            
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
	        ftp.storeFile(filename, input);          
	         
	        input.close(); 
	        ftp.logout(); 
	        success = true; 
	    } catch (IOException e) {
	    	logger.error("录音上传错误"+filename);
	        e.printStackTrace(); 
	    } finally { 
	        if (ftp.isConnected()) { 
	            try { 
	                ftp.disconnect(); 
	            } catch (IOException ioe) { 
	            } 
	        } 
	    } 
	    return success; 
	}
	
	/**
	* Description: 从FTP服务器下载文件
	* @param url FTP服务器hostname
	* @param port FTP服务器端口
	* @param username FTP登录账号
	* @param password FTP登录密码
	* @param remotePath FTP服务器上的相对路径
	* @param fileName 要下载的文件名
	* @param localPath 下载后保存到本地的路径
	* @return
	*/ 
	  public static boolean downFile(String remotePath,String fileName,String localPath) { 
	         boolean success = false; 
	         FTPClient ftp = new FTPClient(); 
	         try {
	             int reply; 
	             ftp.connect(ftp_ip, Integer.valueOf(ftp_port)); 
	             //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器 
	             ftp.login(ftp_user, ftp_pwd);//登录 
	             reply = ftp.getReplyCode(); 
	             if (!FTPReply.isPositiveCompletion(reply)) { 
	                 ftp.disconnect(); 
	                 return success; 
	             }
	             String[] dirs = remotePath.split("/");
	             boolean flag  = false;
	             for (int i = 0; i < dirs.length; i++ )
	             {
	                flag = ftp.changeWorkingDirectory(dirs[i]);
	             }
	             File localFile = new File(localPath+"/"+fileName); 
	             OutputStream is = new FileOutputStream(localFile);
	             ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
	             ftp.retrieveFile(fileName, is); //-->
	             is.close(); 
	             ftp.logout(); 
	             success = true; 
	         } catch (Exception e) {
	             logger.error("录音下载错误"+fileName);
	         } finally { 
	             if (ftp.isConnected()) { 
	                 try { 
	                     ftp.disconnect(); 
	                 } catch (IOException ioe) { 
	                 } 
	             } 
	         } 
	         return success; 
	 }
	  
	  /**
		* Description: 从FTP服务器下载文件 返回文件的大小  
		* @param url FTP服务器hostname
		* @param port FTP服务器端口
		* @param username FTP登录账号
		* @param password FTP登录密码
		* @param remotePath FTP服务器上的相对路径
		* @param fileName 要下载的文件名
		* @param localPath 下载后保存到本地的路径
		* @return
		*/ 
		 public static double downFile(String remotePath,String fileName,String localPath,String temp) { 
		         double fileSize = -0.0; 
		         FTPClient ftp = new FTPClient(); 
		         try {
		             int reply; 
		             ftp.connect(ftp_ip, Integer.valueOf(ftp_port)); 
		             //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器 
		             ftp.login(ftp_user, ftp_pwd);//登录 
		             reply = ftp.getReplyCode(); 
		             if (!FTPReply.isPositiveCompletion(reply)) { 
		                 ftp.disconnect(); 
		             }
		             String[] dirs = remotePath.split("/");
		             boolean flag  = false;
		             for (int i = 0; i < dirs.length; i++ )
		             {
		                flag = ftp.changeWorkingDirectory(dirs[i]);
		             }
		             File localFile = new File(localPath+"/"+fileName); 
		             OutputStream is = new FileOutputStream(localFile);
		             ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
		             ftp.retrieveFile(fileName, is); //-->
		             is.close(); 
		             ftp.logout(); 
		             double localFileSize = localFile.length();
		             fileSize = localFileSize; 
		         } catch (Exception e) {
		             logger.error("录音下载错误"+fileName);
		         } finally { 
		             if (ftp.isConnected()) { 
		                 try { 
		                     ftp.disconnect(); 
		                 } catch (IOException ioe) { 
		                 } 
		             } 
		         } 
		         return fileSize; 
		 }
	  
	public static void changeToMp3(String sourcePath, String targetPath) {  
        File source = new File(sourcePath);  
        File target = new File(targetPath);  
        AudioAttributes audio = new AudioAttributes();  
        Encoder encoder = new Encoder();  
  
        audio.setCodec("libmp3lame");  
        EncodingAttributes attrs = new EncodingAttributes();  
        attrs.setFormat("mp3");  
        attrs.setAudioAttributes(audio);  
  
        try {  
            encoder.encode(source, target, attrs); 
            source.delete();
        } catch (IllegalArgumentException e) {  
        } catch (InputFormatException e) {  
        } catch (EncoderException e) {  
        }  
    }

}
