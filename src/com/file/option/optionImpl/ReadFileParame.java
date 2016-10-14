package com.file.option.optionImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadFileParame{
	private static Properties properties = null;
	
	/*static{
		//��֤�����л��ߴ����м��ر�ʾһ���־õ����Լ�
		properties = new Properties();
		
		InputStream is = null;
		
		try {
			
			is = getURL("test.properties").openStream();
			//���������ж�ȡ��ֵ��
			properties.load(is);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(is != null){
				
				try {
					
					is.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}*/
	
	/**
	 * ��ȡ�����ļ�����������Ӧ��ֵ
	 * @param name ������������
	 * @param filePath �����������ļ�λ��src�±ߵ�·��
	 * @return String���͵�����ֵ
	 */
	
	public static String fileValue(String name, String filePath){

		//��֤�����л��ߴ����м��ر�ʾһ���־õ����Լ�
		properties = new Properties();
		
		InputStream is = null;
		
		try {
			
			is = getURL(filePath).openStream();
			//���������ж�ȡ��ֵ��
			properties.load(is);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(is != null){
				
				try {
					
					is.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		return properties.getProperty(name);
	}
	/**
	 * �� URL ����һ��ͳһ��Դ��λ��������ָ����������Դ����ָ�롣��Դ�����Ǽ򵥵��ļ���Ŀ¼��Ҳ�����ǶԸ�Ϊ���ӵĶ�������ã���������ݿ����������Ĳ�ѯ��
	 * @param FilePath
	 * @return
	 */
	public static URL getURL(String FilePath){
		URL url = null;
		//������ ClassLoader ���̴߳������ṩ���������ڸ��߳��еĴ����ڼ��������Դʱʹ�á����δ�趨����Ĭ��Ϊ���̵߳� ClassLoader �����ġ�
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		//���Ҿ��и������Ƶ���Դ���������ʵ��Ӧ����д�˷�������ָ���Ӻδ�������Դ
		url = cl.getResource(FilePath);
		
		if(url == null){
			
			url = ClassLoader.getSystemResource(FilePath);
					
		}
		
		if (null == url){
			
			try {
				
				File file = new File(FilePath);
				
				//�鿴�Ƿ����
				if(file.exists()){
					url = file.toURI().toURL();
				}
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(url == null){
			/******
			 * *����չ*
			 * ******
			 * */
			
			
		}
		return url;
	} 
	
	
	/**
	 * ��ں���
	 * @param keyName
	 * @return
	 */
	/*public static String getProp(String keyName){
		
		return properties.getProperty(keyName);
		
	}*/
	
	
	/*public static void main(String[] args) {

		String info = ReadFileParame.getProp("showName");
		System.out.println(info);
		
	}*/
}
