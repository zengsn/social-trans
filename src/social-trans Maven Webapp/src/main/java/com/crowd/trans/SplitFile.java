package com.crowd.trans;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
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
	
	public static void main(String argv[]) {
        String filePath = "D:\\aa.txt";
       

        try {
            SplitFile sf = new SplitFile();
            ParentFile parentFile = sf.getFileCountByFilePath(filePath);
            System.out.println(parentFile.getLine());
            System.out.println(parentFile.getLineText());
            System.out.println(parentFile.getLists());
            System.out.println(parentFile.getWordCount());
            parentFile.getLine();
            parentFile.getLineText();
            parentFile.getLists();
            parentFile.getWordCount();
            System.out.println("hello");
        } catch (Exception x) {
        	
        }

//        ===============================测试sqlitFile1==========================

    }
	  //按行存放文本内容
    public List<String> lists = new ArrayList<>();
    //文件编码格式
    String encoding = Constant.FILE_ENCODING;
    
    public final static String separator = File.separator;
    
  
    
    /**
     * 通过inputstream来获取处理后的文件
     * 文本字数
     * @param path
     * @return
     */
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
        parentFile.setWordCount(wordCount);
        parentFile.setLineText(tmpLineTxt);
        parentFile.setFilePath(path);
        return parentFile;
    }
    
    /**
     * 分割文件
     *
     * @param pf
     * @return
     * @throws IOException
     */
    public Map<String,Object> sqlitFile1(ParentFile pf,int receivePeopleNum) throws Exception {

        //获取父文本地址
        String sourceFilePath = pf.getFilePath();

        //获取文件
        File file = new File(sourceFilePath);
        if (!file.isFile() || !file.exists()) {
            throw new Exception("指定文件不存在");
        }
        //存储文件list
        List<FileInfo> fileInfoList = new ArrayList<>();
        //存储子文本list
        List<ChildFile> childFileList = new ArrayList<>();
        //返回map
        List<Map<String,Object>> reMapList = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        //获取父文本id
        String parentId = pf.getId();
        //获取行数
        int count = pf.getLine();
        //获取文本内容
        List<String> contentList = pf.getLists();
        //设置分割粒度,行数
        int size = Constant.SIZE;
        //获取被分割后文件的数目
        int num = (count % size != 0) ? (int) (count / size) : 1;
        //根据翻译人数再次划分
        int num1 = (num % receivePeopleNum !=0)? (int) (receivePeopleNum % num ) : 1;
        //获取被分割文件的路径，被分割的文件要存在该路径下
        File parentFile = file.getParentFile();
        //分割文件
        int tmp = 0;
        ChildFile childFile = null;
        FileInfo fileInfo = null;
        for (int i = 0; i < num1; i++) {
            //设置文件id
            String fileId = UUID.randomUUID().toString();
            //设置分片文件存储地方
            String targetPath = parentFile.getAbsolutePath() + separator + fileId + i + "." + Constant.SUFFIX;
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetPath), encoding));
            String lineTxt = null;
            int line = 0;
            //循环一个最小分割粒度后切割文件
            for (int j = tmp; j <= contentList.size() - tmp; j++) {
                bufferedWriter.append(contentList.get(j));
                bufferedWriter.write("\n");
                line++;
                if (line % size == 0) {
                    tmp = ++j;
                    break;
                }
            }
            //子文本存库
            childFile = new ChildFile();
            //设置主键id
            childFile.setId(UUID.randomUUID().toString());
            //设置父文本id
            childFile.setParentId(parentId);
            //设置文本顺序
            childFile.setSort(i);
            //设置文件id
            childFile.setFileId(fileId);
            //设置文件路径
            childFile.setFilePath(targetPath);
            childFileList.add(childFile);

            //附件存库
            fileInfo = new FileInfo();
            //设置附件id
            fileInfo.setFileId(fileId);
            //设置附件路径
            fileInfo.setFilePath(targetPath);
            //设置附件名字
            fileInfo.setFileName(fileId);
            //设置附件类型
            fileInfo.setFileType(Constant.SUFFIX);
            fileInfoList.add(fileInfo);

            //放入返回map中
            map.put("childFileList",childFileList);
            map.put("fileInfoList",fileInfoList);
            //刷新
            bufferedWriter.flush();
        }
        return map;
    }
    
    /**
     * 获取多行数据
     *
     * @param sourceFilePath
     * @return
     * @throws IOException
     */
    public List<String> getMulitLine1(String sourceFilePath, int pageLine) throws IOException {
        List<String> lists = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(sourceFilePath);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String lineTxt = null;
        String resultText = "";
        int line = 1;
        int count = 0;
        while ((lineTxt = bufferedReader.readLine()) != null) {
            //字符相加
            resultText = StringUtils.plusString(resultText, lineTxt);
            //每3行划分一个
            if (line % pageLine == 0) {
                lists.add(resultText);
                System.out.println(resultText);
                resultText = "";
                count++;
            }
            line++;
        }
        System.out.println("count:" + count);

        return lists;
    }
    
    /**
     * 不足规定行数的则补充换行符
     *
     * @param sourceFilePath
     * @return
     * @throws IOException
     */
    public List<String> preFileByCount(String sourceFilePath, int pageLine) throws IOException {

        List<String> lists = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(sourceFilePath);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String lineTxt = null;
        int count = 0;
        while ((lineTxt = bufferedReader.readLine()) != null) {
            lists.add(lineTxt);
            count++;
        }
        switch (count % pageLine) {
            case 1:
                lists.add("\r\n");
            case 2:
                lists.add("\r\n");
                break;

        }
//        System.out.println(lists.toString());
        return lists;
    }
    
    
    
}
