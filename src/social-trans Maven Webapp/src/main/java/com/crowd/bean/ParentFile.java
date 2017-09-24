package com.crowd.bean;

import java.util.List;

import org.apache.ibatis.type.Alias;

public class ParentFile {
	private String id;
    //文件行数
    private int line;
    //文本内容
    private List<String> lists;

    private int wordCount;

    private String lineText;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public String getLineText() {
        return lineText;
    }

    public void setLineText(String lineText) {
        this.lineText = lineText;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public List<String> getLists() {
        return lists;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
    }

	@Override
	public String toString() {
		return "ParentFile [id=" + id + ", line=" + line + ", lists=" + lists
				+ ", wordCount=" + wordCount + ", lineText=" + lineText
				+ ", fileId=" + fileId + ", filePath=" + filePath + "]";
	}
}
