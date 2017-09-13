package com.crowd.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowd.bean.FileInfo;
import com.crowd.dao.FileDao;
@Service
public class FileService {
	@Autowired
	private FileDao fileDao;
	
	 public Boolean insertFile(FileInfo fileInfo, InputStream in) throws Exception {
	        int fileIsSuccess = fileDao.insertFile(fileInfo);
	       
	        String path = fileInfo.getFilePath();
	        // 创建目标文件
	        File file = new File(path);
	        //该路径不存在，则先创建文件夹
	        if (!file.getParentFile().exists()) {
	            file.getParentFile().mkdirs();
	        }
	        //创建文件
	        file.createNewFile();
	        //写入文件
	        FileOutputStream fileOutputStream = new FileOutputStream(file);
	        IOUtils.copy(in, fileOutputStream);
	        fileOutputStream.flush();
	        fileOutputStream.close();
	        return true;
	    } 
}
