package com.crowd.bean;

import org.apache.ibatis.type.Alias;
/*
 * 子文件
 */
public class ChildFile {
	 	@Override
	public String toString() {
		return "ChildFile [id=" + id + ", parentId=" + parentId
				+ ", chapterId=" + chapterId + ", chapterName=" + chapterName
				+ ", content=" + content + ", sort=" + sort + ", fileId="
				+ fileId + ", filePath=" + filePath + "]";
	}

		private String id;
	    private String parentId;
	    private int chapterId;
	    private String chapterName;   //章节名
	    private String content;			//
	    private int sort;
	    private String fileId;
	    private String filePath;

	    public String getFilePath() {
	        return filePath;
	    }

	    public void setFilePath(String filePath) {
	        this.filePath = filePath;
	    }

	    public String getFileId() {
	        return fileId;
	    }

	    public void setFileId(String fileId) {
	        this.fileId = fileId;
	    }

	    public int getSort() {
	        return sort;
	    }

	    public void setSort(int sort) {
	        this.sort = sort;
	    }

	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getParentId() {
	        return parentId;
	    }

	    public void setParentId(String parentId) {
	        this.parentId = parentId;
	    }

	    public int getChapterId() {
	        return chapterId;
	    }

	    public void setChapterId(int chapterId) {
	        this.chapterId = chapterId;
	    }

	    public String getChapterName() {
	        return chapterName;
	    }

	    public void setChapterName(String chapterName) {
	        this.chapterName = chapterName;
	    }

	    public String getContent() {
	        return content;
	    }

	    public void setContent(String content) {
	        this.content = content;
	    }
}
