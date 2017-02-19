import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qingtian on 2017/2/19.
 */
public class TestSplitTxt {


    public static void main(String[] args) {
        TestSplitTxt.splitTxt(3);
    }

    /**
     * 把一个txt划分成几等份
     * @param count
     */
    public static void splitTxt(int count) {
        try {
            String path  = "E:\\UpAndDown\\test\\b.txt";
            FileReader read = new FileReader(path);
            BufferedReader br = new BufferedReader(read);
            String row;
            List<FileWriter> flist = new ArrayList<FileWriter>();
            for (int i = 0; i < count; i++) {
                flist.add(new FileWriter("E:/UpAndDown/test/text" + i + ".txt"));
            }
            int rownum = 1;// 计数器
            while ((row = br.readLine()) != null) {
                flist.get(rownum % count).append(row + "\r\n");
                rownum++;
            }
            for (int i = 0; i < flist.size(); i++) {
                flist.get(i).close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
