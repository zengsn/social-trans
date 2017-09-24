package com.crowd.dao;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crowd.bean.FileInfo;

public interface FileDao {
	 int insertFile(FileInfo fileInfo) throws Exception;

	 //  Boolean saveFile(FileInfo fileInfo, InputStream in) throws Exception;
	   
	   int insertFileBatch(List<FileInfo> list)throws Exception;
	   
	   FileInfo selectFileById(@Param("fileId")String fileId);
}
