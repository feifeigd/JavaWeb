package com.internet.cms.basic.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import com.internet.cms.basic.model.BackupFile;

public class BackupFileUtil {

	private static String realPath;
	private static BackupFileUtil util;
	private String database;
	private String username;
	private String password;
	private List<String> backupFiles;
	private String backupFile;
	private final static String DATABASE_NAME = "database";
	private final static String BACKUP_NAME = "backup";

	private BackupFileUtil() throws IOException{
		Properties prop = new Properties();
		prop.load(BackupFileUtil.class.getClassLoader().getResourceAsStream("backup.properties"));
		database = prop.getProperty("database");
		username = prop.getProperty("database_username");
		password = prop.getProperty("database_password");
		backupFile = prop.getProperty("backupFile");
		File bf = new File(realPath + File.separator + backupFile);
		if(!bf.exists())bf.mkdirs();
		backupFiles = new ArrayList<String>();
		//添加要备份或者要恢复的文件夹
		String fs = prop.getProperty("file");
		String[] fas = fs.split(",");
		for(String f : fas){
			backupFiles.add(f);
		}
	}
	
	public static BackupFileUtil getInstance(String realPath) {
		try{
			BackupFileUtil.realPath = realPath;
			if(util == null)util = new BackupFileUtil();
			return util;
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}

	/// 所有的备份文件
	public List<BackupFile> listBackups() {
		File[] fs = new File(realPath + File.separator + backupFile).listFiles(new FileFilter(){
			public boolean accept(File pathname){
				return !pathname.isDirectory();
			}
		});
		List<BackupFile> bs = new ArrayList<BackupFile>();
		for (File f : fs){
			BackupFile bf = new BackupFile();
			bf.setName(f.getName());
			bf.setSize((int)(f.length() / 1024));
			bf.setTime(new Date(f.lastModified()));
			bf.setFiletype(f.getName().substring(f.getName().lastIndexOf(".") + 1));
			bs.add(bf);
		}
		Collections.sort(bs);
		return bs;
	}

	/// 恢复的name就是整个文件的名称
	public void resume(String name) {
		// TODO Auto-generated method stub
		String op = realPath + File.separator + backupFile + File.separator + BACKUP_NAME;
		try{
			// 1、将文件进行解压
			String fp = realPath + File.separator + backupFile + File.separator + name;
			TarAndGzipUtil tagu = TarAndGzipUtil.getInstance();
			tagu.unTarFile(new File(fp), realPath + File.separator + backupFile);
			// 2、拷贝并且覆盖相应的文件夹
			for(String f :  backupFiles){
				// 先删除原有的文件夹
				String src = op + f;
				String dest = realPath + File.separator + f;
				File dfd = new File(dest);
				if(!dfd.exists())dfd.mkdirs();
				FileUtils.deleteDirectory(dfd);
				FileUtils.copyDirectory(new File(src), dfd);
			}
			// 3、恢复数据库
			MySQLUtil msu = MySQLUtil.getInstance();
			msu.setCfg(DATABASE_NAME, op, database, username, password);
			msu.resume();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				FileUtils.deleteDirectory(new File(op));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void backup(String backupFilename) {
		// TODO Auto-generated method stub
		String bp = realPath + File.separator + backupFile + File.separator + BACKUP_NAME;
		try {
			// 1. 创建备份文件夹对象
			File bpf = new File(bp);
			bpf.mkdirs();
			// 导出数据库
			MySQLUtil msu = MySQLUtil.getInstance();
			msu.setCfg(DATABASE_NAME, bp, database, username, password);
			msu.backup();
			// 将要备份的文件夹拷贝到目标文件夹中
			for (String f : backupFiles){
				String src = realPath + File.separator;
				String dest = bp + f;
				FileUtils.copyDirectory(new File(src), new File(dest));
			}
			// tar和gz
			TarAndGzipUtil tagu = TarAndGzipUtil.getInstance();
			tagu.tarFile(bp, realPath + File.separator + backupFile + File.separator + new Date().getTime() + "_" + backupFilename + ".tar");
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			FileUtils.deleteDirectory(new File(bp));
		}
	}

}
