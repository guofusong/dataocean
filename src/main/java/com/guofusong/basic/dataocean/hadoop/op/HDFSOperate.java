package com.guofusong.basic.dataocean.hadoop.op;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.guofusong.basic.dataocean.hadoop.conn.FileSystemFactory;

/**
 * hdfs 文件操作工具类
 * @author Administrator
 *
 */
public class HDFSOperate {
	
	private FileSystemFactory factory;
	
	public HDFSOperate(FileSystemFactory factory){
		this.factory = factory;
	}
	
	/**
	 * 创建文件夹
	 * @param path
	 * @throws IOException
	 */
	public void mkdir(String path) throws IOException{
		FileSystem fileSystem = null;
		try {
			fileSystem = factory.get();
			Path p = new Path(path);
			fileSystem.mkdirs(p);
		} catch (IOException e) {
			throw e;
		}finally{
			FileSystemFactory.close(fileSystem);
		}
	}
	
	/**
	 * 遍历文件夹
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public List<String> walkDir(String path) throws IOException{
		FileSystem fileSystem = null;
		try {
			fileSystem = factory.get();
			Path p = new Path(path);
			FileStatus[] fileStatus = fileSystem.listStatus(p);
			List<String> list = new ArrayList<>(fileStatus.length);
			for (FileStatus fileStat : fileStatus) {
				list.add(fileStat.getPath().getName());
			}
			return list;
		} catch (IOException e) {
			throw e;
		}finally{
			FileSystemFactory.close(fileSystem);
		}
	}
	
	/**
	 * 上传文件
	 * @param src
	 * @param dst
	 * @throws IOException
	 */
	public void upload(String src,String dst) throws IOException{
		FileSystem fileSystem = null;
		try {
			fileSystem = factory.get();
			Path s = new Path(src);
			Path d = new Path(dst);
			fileSystem.copyFromLocalFile(s, d);
		} catch (IOException e) {
			throw e;
		}finally{
			FileSystemFactory.close(fileSystem);
		}
	}
	
	/**
	 * 下载文件
	 * @param src
	 * @param dst
	 * @throws IOException
	 */
	public void download(String src,String dst) throws IOException{
		FileSystem fileSystem = null;
		try {
			fileSystem = factory.get();
			Path s = new Path(src);
			Path d = new Path(dst);
			fileSystem.copyToLocalFile(s, d);
		} catch (IOException e) {
			throw e;
		}finally{
			FileSystemFactory.close(fileSystem);
		}
	}
	
}
