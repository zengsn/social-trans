package com.qingtian.utils;

import java.io.*;
import java.sql.*;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public class EntityMysql {
    /**
     * 出口
     * TODO
     *
     * @param args
     */
    public static void main(String[] args) {

            String tableName = "p_permission";//表名
            String path = "com.qingtian.apps.permission.entity";//包路径,如不存在则创建该文件夹
            boolean isCover=true;//如果文件已存在是否覆盖
            new EntityMysql(path, tableName,isCover);
    }


    private String packageOutPath; //指定实体生成所在包的路径
    private String authorName = "";//作者名字
    private String tablename;//表名
    private String javaClassName; //java类名;
    private String[] colnames; // 列名数组
    private String[] colnameTips;//列注释数组
    private String[] colTypes; //列名类型数组
    private int[] colSizes; //列名大小数组
    private boolean f_util = false; // 是否需要导入包java.util.*
    private boolean f_sql = false; // 是否需要导入包java.sql.*

    //数据库连接
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/cst";
    private static final String NAME = "root";
    private static final String PASS = "123456";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    public EntityMysql() {
    }

    /*
         * 构造函数
         */
    public EntityMysql(String packageOutPath, String tablename, boolean isCover) {
        this.packageOutPath = packageOutPath;
        this.tablename = tablename;
        this.javaClassName = this.getJavaClassName();

        //创建连接
        Connection con = null;
        //查要生成实体类的表
        String sql = "select * from " + tablename;
        PreparedStatement pStemt = null;
        try {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            con = DriverManager.getConnection(URL, NAME, PASS);
            pStemt = con.prepareStatement(sql);
            ResultSetMetaData rsmd = pStemt.getMetaData();
            int size = rsmd.getColumnCount();   //统计列
            colnames = new String[size];
            colnameTips = new String[size];
            colTypes = new String[size];
            colSizes = new int[size];

            ResultSet rs = con.getMetaData().getColumns(null, NAME, tablename.toUpperCase(), "%");
            int j = 0;
            while (rs.next()) {
                String remarks = rs.getString("REMARKS");
                colnameTips[j++] = remarks;
            }

            for (int i = 0; i < size; i++) {


                colnames[i] = rsmd.getColumnName(i + 1);
                colTypes[i] = rsmd.getColumnTypeName(i + 1);

                if (colTypes[i].equalsIgnoreCase("datetime") || colTypes[i].equalsIgnoreCase("date")
                        || colTypes[i].equalsIgnoreCase("timestamp") || colTypes[i].equalsIgnoreCase("time")) {
                    f_util = true;
                }
                if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")) {
                    f_sql = true;
                }
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
            }

            String content = parse(colnames, colnameTips, colTypes, colSizes);

            try {
                PrintWriter out = null;
                File directory = new File("");

                String outputPath = directory.getAbsolutePath() + "/src/main/java/" + this.packageOutPath.replace(".", "/") + "/" + initcap(javaClassName) + ".java";
                File file = new File(directory.getAbsolutePath() + "/src/main/java/" + this.packageOutPath.replace(".", "/"));
                if (!file.exists()) {
                    file.mkdirs();
                }
                file = new File(outputPath);
                if (file.exists()) {
                    System.out.println("==================================该路径文件已存在：" + outputPath);
                    if (isCover) {
                        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputPath), "UTF-8")));
                        System.out.println("覆盖该文件: " + outputPath);
                    }else {
                        System.out.println("文件已存在，不进行覆盖");
                    }
                }else{
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputPath), "UTF-8")));
                    System.out.println("生成文件: " + outputPath);
                }
                if (out != null) {
                    out.println(content);
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过表名忽略下划线及下划线前面字母处理返回java类名；
     *
     * @return
     */
    public String getJavaClassName() {
        String getJavaClassName = tablename.substring(tablename.indexOf('_') + 1, tablename.length());
        return getJavaClassName;
    }

    /**
     * 功能：生成实体类主体代码
     *
     * @param colnames
     * @param colTypes
     * @param colSizes
     * @return
     */
    private String parse(String[] colnames, String[] colnameTips, String[] colTypes, int[] colSizes) {
        StringBuffer sb = new StringBuffer();
        sb.append("package " + this.packageOutPath + ";\r\n");
        //判断是否导入工具包
        sb.append("import java.io.Serializable;\r\n");
        if (f_util) {
            sb.append("import java.util.Date;\r\n");
        }
        if (f_sql) {
            sb.append("import java.sql.*;\r\n");
        }

        sb.append("\r\n");
        //注释部分
        sb.append("   /**\r\n");
        sb.append("    * " + javaClassName + " 实体类\r\n");
        sb.append("    * " + new Date() + " " + this.authorName + "\r\n");
        sb.append("    */ \r\n");
        //实体部分
        sb.append("\r\n\r\npublic class " + initcap(javaClassName) + " implements Serializable {\r\n");
        processAllAttrs(sb);//属性
        processAllMethod(sb);//get set方法
        sb.append("}\r\n");

        return sb.toString();
    }

    /**
     * 功能：生成所有属性
     *
     * @param sb
     */
    private void processAllAttrs(StringBuffer sb) {

        for (int i = 0; i < colnames.length; i++) {
            sb.append("\t/**\r\n" +
                    "\t*" + colnameTips[i] + "\r\n" +
                    "\t*/" + "\r\n" +
                    "\tprivate " + sqlType2JavaType(colTypes[i]) + " " + colnames[i] + ";\r\n\r\n");
        }

    }

    /**
     * 功能：生成所有方法
     *
     * @param sb
     */
    private void processAllMethod(StringBuffer sb) {

        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tpublic void set" + initcap(colnames[i]) + "(" + sqlType2JavaType(colTypes[i]) + " " +
                    colnames[i] + "){\r\n");
            sb.append("\tthis." + colnames[i] + "=" + colnames[i] + ";\r\n");
            sb.append("\t}\r\n");
            sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + initcap(colnames[i]) + "(){\r\n");
            sb.append("\t\treturn " + colnames[i] + ";\r\n");
            sb.append("\t}\r\n");
        }

    }

    /**
     * 功能：将输入字符串的首字母改成大写
     *
     * @param str
     * @return
     */
    private String initcap(String str) {

        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }

        return new String(ch);
    }

    /**
     * 功能：获得列的数据类型
     *
     * @param sqlType
     * @return
     */
    private String sqlType2JavaType(String sqlType) {

        if (sqlType.equalsIgnoreCase("bit")) {
            return "Boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "Byte";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "Short";
        } else if (sqlType.equalsIgnoreCase("int")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "Long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "Float";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney")) {
            return "Double";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("date")
                || sqlType.equalsIgnoreCase("time") || sqlType.equalsIgnoreCase("timestamp")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blod";
        }

        return null;
    }


    //===========================================================================================================================



}
