package com.qingtian.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by machao on 2016/12/31.
 */
public class StringUtils {

    /**
     * 字符为空返回true
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 字符非空返回true
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 获取方法名
     *
     * @return
     */
    public static String getMethodName() {
        //获取方法名
        String methodName = Thread.currentThread().getStackTrace()[1].getClassName();
        return methodName;
    }

    /**
     * 日期格式转换为路径格式
     * 比如2016-12-08转换为2016\12\08\
     *
     * @param date 日期
     * @return
     */
    public static String dateToPath(String date) {
        String dateArray[] = date.split("-");
        String path = "";
        //定义分隔符，防止调用多次
        String separator = File.separator;
        for (int i = 0; i < dateArray.length; i++) {
            path += dateArray[i] + separator;
//            System.out.println(path);
        }
        return path;
    }

    /**
     * 两个字符串相加
     *
     * @param a
     * @param b
     * @return
     */
    public static String plusString(String a, String b) {
        StringBuilder builder = new StringBuilder(a);
        builder.append(b);
//        System.out.println(builder.toString());
        return builder.toString();
    }

    /**
     * 两个字符串相加，有换行
     *
     * @param a
     * @param b
     * @return
     */
    public static String plusNewLineString(String a, String b) {
        StringBuilder builder = new StringBuilder(a);
        builder.append("\r\n" + b);
//        System.out.println(builder.toString());
        return builder.toString();
    }

    /**
     * 改变图片尺寸
     *
     * @param srcFileName 源图片路径
     * @param tagFileName 目的图片路径
     * @param width       修改后的宽度
     * @param height      修改后的高度
     */
    public static void zoomImage(String srcFileName, String tagFileName, int width, int height) {
        try {
            BufferedImage bi = ImageIO.read(new File(srcFileName));
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(bi, 0, 0, width, height, null);
            ImageIO.write(tag, "jpg", new File(tagFileName));//画图
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void scale(String srcImageFile, String result, int height, int width, boolean bb) {
        try {
            double ratio = 0.0; // 缩放比例
            File f = new File(srcImageFile);
            BufferedImage bi = ImageIO.read(f);
            Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);
            // 计算比例
            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
                if (bi.getHeight() > bi.getWidth()) {
                    ratio = (new Integer(height)).doubleValue()
                            / bi.getHeight();
                } else {
                    ratio = (new Integer(width)).doubleValue() / bi.getWidth();
                }
                AffineTransformOp op = new AffineTransformOp(AffineTransform
                        .getScaleInstance(ratio, ratio), null);
                itemp = op.filter(bi, null);
            }
            if (bb) {//补白
                BufferedImage image = new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, width, height);
                if (width == itemp.getWidth(null))
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                else
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                g.dispose();
                itemp = image;
            }
            ImageIO.write((BufferedImage) itemp, "JPEG", new File(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int countSum(String str) {
        int unicodeCount = 0;
        int szCount = 0;
        int zmCount = 0;

        for (int i = 0; i < str.length(); i++) {

            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
                szCount++;
            } else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                zmCount++;
            } else {
                unicodeCount++;
            }
        }
//        System.out.println("Unicode:" + unicodeCount);
//        System.out.println("数字：" + szCount);
//        System.out.println("字母：" + zmCount);
        int sum = szCount + zmCount + unicodeCount;
        return sum;
    }

    public static int countSum2(String str) {
        int abccount = 0;
        int numcount = 0;
        int spacecount = 0;
        int othercount = 0;
        char[] b = str.toCharArray();
        for (int i = 0; i < b.length; i++) {
            if (b[i] >= 'a' && b[i] <= 'z' || b[i] >= 'A' && b[i] <= 'Z') {
                abccount++;
            } else if (b[i] >= '0' && b[i] <= '9') {
                numcount++;
            } else if (b[i] == ' ') {
                spacecount++;
            } else {
                othercount++;
            }
        }
        int sum = abccount + numcount + spacecount + othercount;
//        System.out.println("字符串中含有的英文字母数为：" + abccount);
//        System.out.println("字符串中含有的数字数为：" + numcount);
//        System.out.println("字符串中含有的空格数为：" + spacecount);
//        System.out.println("字符串中含有的其他字符为：" + othercount);
        return sum;
    }

    public static  double div(double a,double b){
        double result= 0;
        if(b!=0){
            return a/b;
        }else {
            return result;
        }
    }


    public static void main(String[] args) {
//        //==========================================统计字数=========================================================================
//        String str = "展恒理财，2004年在北京成立，是国内最大的理财咨询类机构之一。获得国家颁发的独立基金销售牌照.是2013年中国网球公开赛10大核心赞助商之一。公司成立10年来，在为客户进行全面的家庭财务规划方面积累了十分丰富的经验。目前拥有中高端忠实客户10000多名，配置客户资金超过200亿元，位列行业排名前三强。";
//
//        System.out.println("[总字符数1]："+countSum(str));
//        System.out.println("--------------------");
//        System.out.println("[总字符数2]："+countSum2(str));
//        System.out.println("--------------------");
//        System.out.println("[总字符数3]："+str.length());
    }
}


