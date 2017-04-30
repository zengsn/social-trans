package com.qingtian.apps.system.taskTranslate;

import com.qingtian.apps.system.File.entity.FileInfo;
import com.qingtian.apps.system.File.entity.TaskFile;
import com.qingtian.apps.task.entity.TranslateComment;
import com.qingtian.utils.Constant;
import com.qingtian.utils.StringUtils;
import com.qingtian.utils.ToolUtils;
import org.workSpace.utils.RandomGUID;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by qingtian on 2017/2/23.
 */
public class SplitFile {

    //按行存放文本内容
    public List<String> lists = new ArrayList<>();
    //文件编码格式
    String encoding = Constant.FILE_ENCODING;

    public static void main(String argv[]) {
        String filePath = "F:\\machao1\\test1.txt";
        String filePath1= "F:\\machao1\\0D372E17-8A86-0CCC-AF51-AA63A583349111.txt";
        String filePath2= "F:\\machao1\\test\\abc.txt";
        String filePath3= "F:\\machao1\\test\\致谢.txt";
        try{
            SplitFile sf = new SplitFile();
            TaskFile taskFile = sf.getFileCountByFilePath(filePath3);
            taskFile.getLine();
            taskFile.getLineText();
            taskFile.getLists();
            taskFile.getWordCount();
            System.out.println("hello");
        }catch (Exception x){

        }
    }

    public final static String separator = File.separator;

    /**
     * 获取文件的行数
     *
     * @param sourceFilePath
     * @return
     */
    public int getFileCount(String sourceFilePath) {
        //文件行数
        int count = 0;
        String encoding = "GBK";
        //1：获取文件
        File file = new File(sourceFilePath);
        try {
            //2:判断文件是否存在
            if (file.isFile() && file.exists()) {

                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                //3:读取文件获取行数
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    count++;
                }
                read.close();
                return count;
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 通过inputstream来获取处理后的文件
     * @param path
     * @return
     */
    public TaskFile getFileCountByFilePath(String path)throws IOException{
        //获取输入流
        FileInputStream in = new FileInputStream(path);
        //获取reader,设置编码格式
        InputStreamReader inputStreamReader = new InputStreamReader(in,encoding);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        //获取文件行数和文本内容
        String lineText = null;
        TaskFile taskFile = new TaskFile();
        //行数
        int line = 0;
        //文本字数
        int wordCount = 0;
        //文本内容
//        List<String> contentList = new ArrayList<>();
        List<String> contentList = new LinkedList<>();
        String tmpLineTxt = null;
        while((lineText = reader.readLine())!=null){
            tmpLineTxt = lineText + tmpLineTxt;
            contentList.add(lineText);
            //统计文本字数
            wordCount = wordCount + StringUtils.countSum(lineText);
            //统计行数
            line++;
            //每5000行手动设置章节
            //章节
            int chapterNum = 1;
            if(line % Constant.CHAPTER_LINE ==0){
                String ChapterName= "第"+chapterNum+"章";
                contentList.add(ChapterName);
                chapterNum = chapterNum +1;
            }
        }
        reader.close();
        taskFile.setLine(line);
        taskFile.setLists(contentList);
        taskFile.setWordCount(wordCount);
        taskFile.setLineText(tmpLineTxt);
        return taskFile;
    }

//    /**
//     * 通过inputstream来获取处理后的文件
//     * @param path
//     * @return
//     */
//    public TaskFile getFileCountByFilePath(String path)throws IOException{
//        //获取输入流
//        FileInputStream in = new FileInputStream(path);
//        //获取reader,设置编码格式
//        InputStreamReader inputStreamReader = new InputStreamReader(in,encoding);
//        BufferedReader reader = new BufferedReader(inputStreamReader);
//        //获取文件行数和文本内容
//        String lineText = null;
//        TaskFile taskFile = new TaskFile();
//        //行数
//        int line = 0;
//        //文本内容
//        List<String> contentList = new ArrayList<>();
//        while((lineText = reader.readLine())!=null){
//            contentList.add(lineText);
//            line++;
//        }
//        reader.close();
//        taskFile.setLine(line);
//        taskFile.setLists(contentList);
//        return taskFile;
//    }


    /**
     * 分割文件
     * @param taskFile
     * @return
     * @throws IOException
     */
    public List<FileInfo> sqlitFile1(TaskFile taskFile,String sourceFilePath,String fileCode)throws Exception{
        //获取文件
        File file = new File(sourceFilePath);
        if (!file.isFile() || !file.exists()) {
            throw new Exception("指定文件不存在");
        }
        List<FileInfo> list = new ArrayList<>();
        //获取行数
        int count = taskFile.getLine();
        //获取文本内容
        List<String> contentList = taskFile.getLists();
        //设置分割粒度
        int size = Constant.SIZE;
        //获取被分割后文件的数目
        int num = (count % size != 0) ? (int) (count / size) : 1;
        //获取被分割文件的路径，被分割的文件要存在该路径下
        File parentFile = file.getParentFile();
        //分割文件
        int tmp=0;

        for (int i = 0; i <= num; i++) {
            //设置文件id
            String fileId = new RandomGUID().toString();
            //设置分片文件存储地方
            String targetPath = parentFile.getAbsolutePath() + separator + fileId + i + "." + Constant.SUFFIX;
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetPath), encoding));
            String lineTxt = null;
            int line = 0;
            //循环一个最小分割粒度后切割文件
            for(int j=tmp;j<=contentList.size()-tmp;j++){
                bufferedWriter.append(contentList.get(j));
                bufferedWriter.write("\n");
                line++;
                if (line % size == 0) {
                    tmp = ++j;
                    break;
                }
            }
            FileInfo reFileInfo = new FileInfo();
            reFileInfo.setFileId(fileId);
            reFileInfo.setFilePath(targetPath);
            reFileInfo.setFileName(fileId);
            reFileInfo.setChildFile("1");
            reFileInfo.setFileType(Constant.SUFFIX);
            reFileInfo.setFileCode(fileCode);
            list.add(reFileInfo);
            bufferedWriter.flush();
        }
        return list;
    }
    /**
     * 切割文件
     *
     * @param sourceFilePath
     * @throws Exception
     */
    public  void sqlitFile(String sourceFilePath) throws Exception {
        //获取文件
        File file = new File(sourceFilePath);
        if (!file.isFile() || !file.exists()) {
            throw new Exception("指定文件不存在");
        }
        //获取文件总行数
        int count = getFileCount(sourceFilePath);
//        System.out.println("count:" + count);
        //设置分割粒度
        int size = Constant.SIZE;
        //获取被分割后文件的数目
        int num = (count % size != 0) ? (int) (count / size) : 1;
//        System.out.println("num:" + num);

        //获取被分割文件的路径，被分割的文件要存在该路径下
        File parentFile = file.getParentFile();
        InputStreamReader read = new InputStreamReader(
                new FileInputStream(file), encoding);
        BufferedReader bufferedReader = new BufferedReader(read);
        //分割文件
        for (int i = 0; i <= num; i++) {
            String targetPath = parentFile.getAbsolutePath() + separator + new RandomGUID().toString() + i + "."+Constant.SUFFIX;
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetPath), encoding));
            String lineTxt = null;
            int line = 0;
            //循环一个最小分割粒度后切割文件
            while ((lineTxt = bufferedReader.readLine()) != null) {
                bufferedWriter.append(lineTxt);
                bufferedWriter.write("\n");
                line++;
                if (line % size == 0) {
                    break;
                }
            }
            bufferedWriter.flush();
        }
        read.close();
    }


    /**
     * 获取多行数据
     * @param sourceFilePath
     * @return
     * @throws IOException
     */
    public List<String> getMulitLine1(String sourceFilePath,int pageLine) throws IOException {
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
            resultText = StringUtils.plusString(resultText,lineTxt);
            //每3行划分一个
            if(line % pageLine==0){
                lists.add(resultText);
                System.out.println(resultText);
                resultText = "";
                count++;
            }
            line++;
        }
        System.out.println("count:"+count);

        return lists;
    }

    /**
     * 不足规定行数的则补充换行符
     * @param sourceFilePath
     * @return
     * @throws IOException
     */
    public List<String> preFileByCount(String sourceFilePath,int pageLine)throws IOException{

        List<String> lists = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(sourceFilePath);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String lineTxt = null;
        int count = 0;
        while((lineTxt = bufferedReader.readLine())!=null){
            lists.add(lineTxt);
            count++;
        }
        switch (count % pageLine){
            case 1:lists.add("\r\n");
            case 2:lists.add("\r\n");break;

        }
//        System.out.println(lists.toString());
        return lists;
    }


    /**
     * 获取多行数据
     * @param sourceFilePath
     * @return
     * @throws IOException
     */
    public List<String> getMulitLine2(String sourceFilePath,int pageLine) throws IOException{
        //不足规定行数补足
        List<String> lists = preFileByCount(sourceFilePath,pageLine);
        //返回的list
        List<String> reLists = new ArrayList<>();
        //初始行数
        int line = 1;
        String resultText = "";
        //划分：每隔规定行就往list中添加String
        for(int i=0;i<=lists.size();i++){
            resultText = StringUtils.plusNewLineString(resultText,lists.get(i));
            if(line % Constant.PAGE_LINE==0){
                reLists.add(resultText);
//                System.out.println(resultText);
                resultText = "";
            }
            line++;
        }
        return reLists;
    }

    /**
     * 获取多行数据
     * @param sourceFilePath
     * @return
     * @throws IOException
     */
    public List<TranslateComment> getMulitLine(String sourceFilePath,String fileId,int pageLine) throws IOException{
        //不足规定行数补足
        List<String> lists = preFileByCount(sourceFilePath,pageLine);
        //返回的list
        List<TranslateComment> reLists = new ArrayList<>();
        //初始行数
        int line = 1;
        int count = 1;
        String resultText = "";
        //划分：每隔规定行就往list中添加String
        for(int i=0;i<lists.size();i++){
            resultText = StringUtils.plusNewLineString(resultText,lists.get(i));
            if(line % Constant.PAGE_LINE==0){
                TranslateComment tc = new TranslateComment();
                tc.setComment(resultText);
                tc.setCommentId(count);
                tc.setFileId(fileId);
                reLists.add(tc);
//                System.out.println(resultText);
                resultText = "";
                count++;
            }
            line++;
        }
        return reLists;
    }


    /**
     * 获取经过处理的文件
     * @param sourceFilePath
     * @return
     * @throws IOException
     */
    public List<TranslateComment> getFile(String sourceFilePath,String fileId,int pageLine) throws IOException {
        //获取经过处理后的文件
        List<TranslateComment> list = getMulitLine(sourceFilePath,fileId,pageLine);
        return list;
    }
}
