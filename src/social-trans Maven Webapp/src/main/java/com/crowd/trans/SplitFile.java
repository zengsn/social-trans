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
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import com.crowd.bean.ChildFile;
import com.crowd.bean.FileInfo;
import com.crowd.bean.ParentFile;
import com.crowd.utils.Constant;
import com.crowd.utils.StringUtils;

public class SplitFile {

	 public static void main(String[] args) throws IOException {
	        ArrayList<String> res = new ArrayList<String>();// 段落切分结果
	        StringBuilder sb = new StringBuilder();// 拼接读取的内容
	        String temp = null;// 临时变量，存储sb去除空格的内容
	       // BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\Users\\Administrator\\Desktop\\测试\\测试.txt")));
	        FileInputStream in = new FileInputStream("C:\\Users\\Administrator\\Desktop\\测试\\测试.txt");
			// 获取reader,设置编码格式
			InputStreamReader inputStreamReader = new InputStreamReader(in,
					Constant.FILE_ENCODING);
			BufferedReader reader = new BufferedReader(inputStreamReader);
	        int ch = 0;
	        
	        while ((ch = reader.read()) != -1) {
	                sb.append((char) ch);
	            }
	        String text = new String(sb);
	        text.replaceAll("(?m)^\\s*$"+System.lineSeparator(), "");
	        text.replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1").replaceAll("^((\r\n)|\n)", "");
	        System.out.print(text);
	        SplitFile  sf = new SplitFile();
	        sf.spiltText1(text);
	    
	 }
	String encoding = Constant.FILE_ENCODING;

	// 文章拆分
	public List<String> spiltString(String text, int num) {
		List<String> textList = new ArrayList<>();
		int length = text.length() - 1;

		int textLength = length / num;
		int beginIndex = 0;
		int endIndex = textLength;
		for (int i = 0; i < num; i++) {
			String childText = text.substring(beginIndex, endIndex);
			textList.add(childText);
			beginIndex = beginIndex + textLength;
			endIndex = endIndex + textLength;
		}
		return textList;
	}

	public Map<Integer,String> spiltText1(String text) {
		HashMap<Integer,String> textMap = new HashMap<Integer, String>();;
		String s[] = text.split("\n");
		int num = s.length / Constant.SIZE;
		int tmp = 0;
		for (int i = 0; i < num; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = tmp; j < s.length; j++) {
				sb.append(s[j]);
				sb.append("\n");
				if (j!=0 && j % 50 == 0) {
					tmp = ++j;
					textMap.put(i+1, new String(sb));
					break;
				}
			}
		}
		
		Set set = textMap.keySet();
		Iterator t = set.iterator();
		for(Iterator iter = set.iterator(); iter.hasNext();)
		  {
			
		   int key = (int)iter.next();
//		   System.out.println("第"+key +"段：");
		   String value = (String)textMap.get(key);
//		   System.out.println(value);
		  }
		return textMap;
	}
	//文件拆分
	public Map<Integer,String> spiltText(String text) {
		HashMap<Integer,String> textMap = new HashMap<Integer, String>();
		text.replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1").replaceAll("^((\r\n)|\n)", ""); 
		String s[] = text.split("\n");
		 System.out.println(text);
		int tmp = 0;
		int i =1;
			StringBuilder sb = new StringBuilder();
			for (int j = tmp; j < s.length; j++) {
				if(new String(sb).length()<250){
		                	sb.append(s[j]);
		                	sb.append("\n");
				}
				 else{
					 	tmp = ++j;
						textMap.put(i++, new String(sb));
						System.out.println(i+new String(sb));
						sb.delete(0, sb.length());
				 }
		}
		Set set = textMap.keySet();
		Iterator t = set.iterator();
		for(Iterator iter = set.iterator(); iter.hasNext();)
		  {
		   int key = (int)iter.next();
		   String value = (String)textMap.get(key);
		  }
		return textMap;
	}
//	public Map<String,String> spiltText1(String text,int totleNum) {
//		HashMap<Integer,String> textMap = new HashMap<Integer, String>();
//		HashMap<String,String> childMap = new HashMap<String, String>();
//		String s[] = text.split("\n");
//		int num = s.length / Constant.SIZE;
//		int tmp = 0;
//		for (int i = 0; i < num; i++) {
//			StringBuilder sb = new StringBuilder();
//			for (int j = tmp; j < s.length; j++) {
//				sb.append(s[j]);
//				sb.append("\n");
//				if (j!=0 && j % 50 == 0) {
//					tmp = ++j;
//					textMap.put(i, new String(sb));
//					break;
//				}
//			}
//		}
//		System.out.println(textMap.size());
//		SplitFile sf = new SplitFile();
//		int s1[] = sf.randomCommon(0, textMap.size()+1, textMap.size());
//		int len = s1.length/totleNum+1;
//		for(int tem=0;tem<totleNum;tem++){
//			StringBuilder sb = new StringBuilder();
//			StringBuilder sp = new StringBuilder();
//			for(int size=0;size<s1.length;size++){
//				sb.append((String)textMap.get(s1[size]));
//				sp.append(s1[size]+",");
//				if (size!=0 && size % len == 0) {
//					tmp = ++size;
//					childMap.put(new String(sp), new String(sb));
//					break;
//				}
//		}
//		}
//		Set set = childMap.keySet();
//		Iterator t = set.iterator();
//		for(Iterator iter = set.iterator(); iter.hasNext();)
//		  {
//		   String key = (String)iter.next();
//		   System.out.println(key);
//		   String value = (String)childMap.get(key);
//		   System.out.println(value);
//		  }
//		return childMap;
//	}
	

	
	public static int[] randomCommon(int min, int max, int n){  
	    if (n > (max - min + 1) || max < min) {  
	           return null;  
	       }  
	    int[] result = new int[n];  
	    int count = 0;  
	    while(count < n) {  
	        int num = (int) (Math.random() * (max - min)) + min;  
	        boolean flag = true;  
	        for (int j = 0; j < n; j++) {  
	            if(num == result[j]){  
	                flag = false;  
	                break;  
	            }  
	        }  
	        if(flag){  
	            result[count] = num;  
	            count++;  
	        }  
	    }  
	    System.out.println(result);
	    return result;  
	}
		  
	      
	  

	// 合并text
	public String combineText(List<String> textList) {

		StringBuilder text = new StringBuilder();
		Iterator<String> textL = textList.iterator();
		while (textL.hasNext()) {
			text.append(textL.next());
		}
		return text.toString();
	}

	public ParentFile getFileCountByFilePath(String path) throws IOException {
		// 获取输入流
		FileInputStream in = new FileInputStream(path);
		// 获取reader,设置编码格式
		InputStreamReader inputStreamReader = new InputStreamReader(in,
				encoding);
		BufferedReader reader = new BufferedReader(inputStreamReader);
		// 获取文件行数和文本内容
		String lineText = null;
		ParentFile parentFile = new ParentFile();
		// 行数
		int line = 0;
		// 文本字数
		int wordCount = 0;
		// 文本内容
		// List<String> contentList = new ArrayList<>();
		List<String> contentList = new LinkedList<>();
		String tmpLineTxt = null;
		while ((lineText = reader.readLine()) != null) {
			tmpLineTxt = lineText + tmpLineTxt;
			contentList.add(lineText);
			// 统计文本字数
			wordCount = wordCount + StringUtils.countSum(lineText);
			// 统计行数
			line++;
			// 每5000行手动设置章节
			// 章节
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
