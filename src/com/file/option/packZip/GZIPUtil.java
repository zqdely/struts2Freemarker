package com.file.option.packZip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

/**
 * @Title: GZIPUtil.java
 * @Description: gzip�ļ�ѹ���ͽ�ѹ��������
 * @author Ally
 * @date 2009-11-4 ����06:23:29
 * @version V1.0
 */
public class GZIPUtil {
 
	/**
	* 
	* @Title: pack
	* @Description: ��һ���ļ����tar��
	* @param sources Ҫ�����ԭ�ļ�����
	* @param target �������ļ�
	* @return File    ���ش������ļ�
	* @throws
	*/
	public static File pack(File[] sources, File target){
	FileOutputStream out = null;
	try {
		out = new FileOutputStream(target);
	} catch (FileNotFoundException e1) {
		e1.printStackTrace();
	}
	TarArchiveOutputStream os = new TarArchiveOutputStream(out);
	for (File file : sources) {
	try {
		os.putArchiveEntry(new TarArchiveEntry(file));
		IOUtils.copy(new FileInputStream(file), os);
		os.closeArchiveEntry();
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} 
	}
	if(os != null) {
	try {
		os.flush();
		os.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
	
	return target;
 }

 /**
  * @throws IOException 
  * @Title: compress
  * @Description: ���ļ���gzipѹ��
  * @param  source ��Ҫѹ�����ļ�
  * @return File    ����ѹ������ļ�
  * @throws
  */
	public static File compress(File source) throws IOException {
//		String name = source.getCanonicalPath() ;
		//����Ŀ��·���µ��ļ�
		File target = new File(source.getCanonicalPath() + ".gz");
		FileInputStream in = null;
		GZIPOutputStream out = null;
		try {
			in = new FileInputStream(source);
			out = new GZIPOutputStream(new FileOutputStream(target));
			byte[] array = new byte[1024];
			int number = -1;
			while((number = in.read(array, 0, array.length)) != -1) {
			out.write(array, 0, number);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if(in != null) {
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		}
		
		if(out != null) {
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		}
		}
		return target;
	}
	/**
	 * ���s�߆΂��w�ļ�����ں�����Linux��ʹ��
	 * @param filePath �΂��ļ���·�� {E:\\����\\IMG_1066.JPG,E:\\����\\IMG_1066.JPG}
	 * @param gZipPath ���s֮����ļ�·��and�ļ�����E:\\����\\release_package.tar
	 * @return ���s�Ƿ�ɹ�
	 * @throws IOException 
	 */
	public static boolean gZipOpen(String[] filePath ,String gZipPath) throws IOException{
		boolean jud = false;
		//����File�ļ����M
		File[] sources = new File[filePath.length] ;
		for (int i = 0;i<filePath.length;i++) {
			
			sources[i] = new File(filePath[i]);
			
		}
		File target = new File(gZipPath);
		if(compress(pack(sources, target)).exists())
			jud = true;
		return jud;
	}
	
	public static void main(String[] args) throws IOException {
		String[] name = {"E:\\����\\IMG_1066.JPG","E:\\����\\IMG_1065.JPG","E:\\����\\IMG_1067.JPG"};
		if(gZipOpen(name, "E:\\����\\���s.tar"))
			System.out.println("ѹ���ɹ�");
		else 
			System.out.println("���sʧ��");
	}
}
