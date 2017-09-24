package com.crowd.trans;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import java.util.UUID;

import com.crowd.bean.ChildFile;
import com.crowd.bean.FileInfo;
import com.crowd.bean.ParentFile;
import com.crowd.utils.Constant;
import com.crowd.utils.StringUtils;

public class SplitFile {
	
	public static void main(String[] args) throws IOException {
		SplitFile sf  =new SplitFile();
		String filePath1 = "C:\\Users\\Administrator\\Desktop\\测试\\测试.txt";
		String filePath2 = "C:\\Users\\Administrator\\Desktop\\测试\\测试2.txt";
		String filePath3 = "C:\\Users\\Administrator\\Desktop\\测试\\测试3.txt";
		  ParentFile parentFile1 = sf.getFileCountByFilePath(filePath1);
		  ParentFile parentFile2 = sf.getFileCountByFilePath(filePath2);
		  ParentFile parentFile3 = sf.getFileCountByFilePath(filePath3);
		  List<String> textList = new ArrayList<>();
		  textList.add(parentFile1.getLists().toString());
		  textList.add(parentFile2.getLists().toString());
		  textList.add(parentFile3.getLists().toString());
		  String text = sf.combineText(textList);
		  System.out.println();
		//String text="如果我说一直不甘示弱、总以为自己是世界第一富豪的安德鲁·卡内基先生来拜访我，并向我讨教了一个非常严肃的问题，你会不会感到惊讶？事实上，那位伟大的铁匠就是这么做的。两天前，卡内基先生来到我们的基奎特。或许是我笑容可掬的态度，和我们轻松的谈话气氛，熔化了卡内基先生钢铁般的自尊，让他放下架子问我：“约翰，我知道，你领导着一群很能干的人。不过，我不认为他们的才干不可匹敌，但令我疑惑的是，他们似乎无坚不摧，总能轻松击败你们的竞争对手。我想知道，你施了什么魔法让他们有那种精神的，难道是金钱的力量？”我告诉他，金钱的力量当然不可低估，但责任的力量更是巨大。有时，行动并非源于想法，而是源自揽起责任。标准石油公司的人都有负责精神，都知道“我的责任是什么？我做什么可以把事情做得更好？”但我从不高谈阔论责任或义务，我只是通过我的领导方式来创造具有责任感的企业。";
		
		//List<String> list = sf.spiltString(parentFile.getLists().toString(), 3);
//		Iterator<String> l = list.iterator();
//		int i=1;
//		while(l.hasNext()){
//			System.out.println(i++ + l.next());
//		}
		  
	}
	
    String encoding = Constant.FILE_ENCODING;
    //文章拆分
    public List<String> spiltString(String text,int num){
    	List<String> textList = new ArrayList<>();
    	int length = text.length()-1;
    	
    	int textLength = length/num;
    	int beginIndex = 0;
    	int endIndex = textLength;
    	for(int i = 0; i<num;i++){
    		String childText = text.substring(beginIndex, endIndex);
    		textList.add(childText);
    		beginIndex = beginIndex+textLength;
    		endIndex = endIndex+textLength;
    	}
    	return textList;
    }
    
    //合并text
    public String combineText(List<String> textList){
    	
    	StringBuilder text = new StringBuilder();
    	Iterator<String> textL = textList.iterator();
    	while(textL.hasNext()){
    		text.append(textL.next());
    	}
    	return text.toString();
    }
    
    public ParentFile getFileCountByFilePath(String path) throws IOException {
        //获取输入流
        FileInputStream in = new FileInputStream(path);
        //获取reader,设置编码格式
        InputStreamReader inputStreamReader = new InputStreamReader(in, encoding);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        //获取文件行数和文本内容
        String lineText = null;
        ParentFile parentFile = new ParentFile();
        //行数
        int line = 0;
        //文本字数
        int wordCount = 0;
        //文本内容
//        List<String> contentList = new ArrayList<>();
        List<String> contentList = new LinkedList<>();
        String tmpLineTxt = null;
        while ((lineText = reader.readLine()) != null) {
            tmpLineTxt = lineText + tmpLineTxt;
            contentList.add(lineText);
            //统计文本字数
            wordCount = wordCount + StringUtils.countSum(lineText);
            //统计行数
            line++;
            //每5000行手动设置章节
            //章节
        }
        System.out.println(line);
        reader.close();
        parentFile.setLine(line);
        
        parentFile.setLists(contentList);
        System.out.println(contentList);
        parentFile.setWordCount(wordCount);
        parentFile.setLineText(tmpLineTxt);
        parentFile.setFilePath(path);
        return parentFile;
    }
}
