package com.qingtian.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
     * @return
     */
    public static String getMethodName(){
        //获取方法名
        String methodName = Thread.currentThread().getStackTrace()[1].getClassName();
        return methodName;
    }

    /**
     * 日期格式转换为路径格式
     * 比如2016-12-08转换为2016\12\08\
     * @param date 日期
     * @return
     */
    public static String dateToPath(String date){
        String dateArray[] = date.split("-");
        String path = "";
        //定义分隔符，防止调用多次
        String separator = File.separator;
        for(int i=0;i<dateArray.length;i++){
            path += dateArray[i] + separator;
//            System.out.println(path);
        }
        return path;
    }

    /**
     * 两个字符串相加
     * @param a
     * @param b
     * @return
     */
    public static String plusString(String a,String b){
        StringBuilder builder = new StringBuilder(a);
        builder.append(b);
//        System.out.println(builder.toString());
        return builder.toString();
    }

    /**
     * 两个字符串相加，有换行
     * @param a
     * @param b
     * @return
     */
    public static String plusNewLineString(String a,String b){
        StringBuilder builder = new StringBuilder(a);
        builder.append("\r\n"+b);
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

//    public static void main(String[] args) {
//        String sourcePath = "F:\\image\\1.jpg";
//        String aimPath = "F:\\image\\3.jpg";
//        zoomImage(sourcePath,aimPath,650,650);
//    }

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

    public static void main(String[] args) {
        String sourcePath = "F:\\image\\1.jpg";
        String aimPath = "F:\\image\\5.jpg";
//        zoomImage(sourcePath, aimPath, 650, 650);
        scale(sourcePath,aimPath,650,650,false);

//        plusNewLineString("hello","world");
    }
}
