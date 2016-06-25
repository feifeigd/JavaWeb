package com.internet.cms.basic.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;

public class TarAndGzipUtil {

	private static TarAndGzipUtil util;

	private TarAndGzipUtil(){}
	
	public static TarAndGzipUtil getInstance() {
		if(util == null)util = new TarAndGzipUtil();
		return util;
	}

	public void unTarFile(File file, String path) {
		TarArchiveInputStream tais = null;
		File tf = null;
		try{
			tf = unGzipFile(file);
			tais = new TarArchiveInputStream(new FileInputStream(tf));
			TarArchiveEntry tae = null;
			while((tae = tais.getNextTarEntry()) != null){
				if(tae.isDirectory())continue;
				String name = path + File.separator + tae.getName();
				FileOutputStream fos = null;
				File ff = new File(name);
				if(!ff.getParentFile().exists())ff.getParentFile().mkdirs();
				try{
					fos = new FileOutputStream(ff);
					IOUtils.copy(tais, fos);
				}finally{
					if(fos != null)fos.close();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{			
			try {
				if(tais != null )tais.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(tf != null){
				tf.delete();
				tf.deleteOnExit();
			}
		}
	}

	private File unGzipFile(File file) {
		GZIPInputStream gis = null;
		FileOutputStream fos = null;
		try{
			gis = new GZIPInputStream(new FileInputStream(file));
			String path = file.getAbsolutePath();
			path = path.substring(0, path.lastIndexOf("."));
			// 要返回的文件
			File f = new File(path);
			fos = new FileOutputStream(f);
			IOUtils.copy(gis, fos);
			return f;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(gis != null){
				try {
					gis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
