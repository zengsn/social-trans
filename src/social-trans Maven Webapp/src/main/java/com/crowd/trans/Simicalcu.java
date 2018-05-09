package com.crowd.trans;  

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;


    
public class Simicalcu {   
	public static void main(String[] args) throws UnsupportedEncodingException {
		String text = "dsadsad+ dasdsa";
		String result = Simicalcu.translatePost1(text); 
		
		System.out.println(result);
	}
	  /* 
     * 计算相似度 
     * */  
    public static double SimilarDegree(String strA, String strB){     
        String newStrA = removeSign(strA);      
        String newStrB = removeSign(strB);  
        //用较大的字符串长度作为分母，相似子串作为分子计算出字串相似度  
        int temp = Math.max(newStrA.length(), newStrB.length());      
        int temp2 = longestCommonSubstring(newStrA, newStrB).length();     
        return temp2 * 1.0 / temp;      
    }    
    
    /* 
     * 结果转换成百分比形式  
     * */     
    public static String similarityResult(double result){      
        return  NumberFormat.getPercentInstance(new Locale( "en ", "US ")).format(result);     
    }    
    /* 
     * 将字符串的所有数据依次写成一行 
     * */  
    public static String removeSign(String str) {     
        StringBuffer sb = new StringBuffer();   
        //遍历字符串str,如果是汉字数字或字母，则追加到ab上面  
        for (char item : str.toCharArray())     
            if (charReg(item)){      
                sb.append(item);    
            }    
        return sb.toString();    
    }    
    
      
    /* 
     * 判断字符是否为汉字，数字和字母， 
     * 因为对符号进行相似度比较没有实际意义，故符号不加入考虑范围。 
     * 汉字：[0x4e00,0x9fa5]（或十进制[19968,40869]）
     * 数字：[0x30,0x39]（或十进制[48, 57]）
		小写字母：[0x61,0x7a]（或十进制[97, 122]）
		大写字母：[0x41,0x5a]（或十进制[65, 90]）
     * */  
    public static boolean charReg(char charValue) {      
        return (charValue >= 0x4E00 && charValue <= 0X9FA5) || (charValue >= 'a' && charValue <= 'z')  
                || (charValue >= 'A' && charValue <= 'Z')  || (charValue >= '0' && charValue <= '9');      
    }      
    
    
    
    /* 
     * 求公共子串，采用动态规划算法。 
     * 其不要求所求得的字符在所给的字符串中是连续的。 
     *  
     * */  
    public static String longestCommonSubstring(String strA, String strB) {     
        char[] chars_strA = strA.toCharArray();  
        char[] chars_strB = strB.toCharArray();   
        int m = chars_strA.length;     
        int n = chars_strB.length;   
        int[][] matrix = new int[m + 1][n + 1];     
        for (int i = 1; i <= m; i++) {    
            for (int j = 1; j <= n; j++) {      
                if (chars_strA[i - 1] == chars_strB[j - 1])     
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;      
                else     
                    matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i - 1][j]);     
            }     
        }  
        char[] result = new char[matrix[m][n]];      
        int currentIndex = result.length - 1;     
        while (matrix[m][n] != 0) {     
            if (matrix[n] == matrix[n - 1])    
                n--;     
            else if (matrix[m][n] == matrix[m - 1][n])      
                m--;     
            else {     
                result[currentIndex] = chars_strA[m - 1];     
                currentIndex--;    
                n--;     
                m--;    
            }    
        }      
       return new String(result);     
    }    
      
      
   
    public static boolean isChinese(String string){
        int n = 0;
        for(int i = 0; i < string.length(); i++) {
            n = (int)string.charAt(i);
            if(!(19968 <= n && n <40869)) {
                return false;
            }
        }
        return true;
    }
    public static boolean isContainChinese(String str) {

        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
 
    public static String translatePost(String str) {
    	boolean isChinese = Simicalcu.isContainChinese(str);
    	JSONObject o = new JSONObject();
    	String url = "http://zsn.cc/trans4me/api.php";
		if (isChinese) {
			o.put("lang", "cn2en");
		} else {
			o.put("lang", "en2cn");
		}
		o.put("key", "SocialTrans2017");
		o.put("in", str);
		System.out.println(o.toString());
		JSONObject json = HttpTool.httpPost(url,o);
		System.out.println(json);
		String result = (String)(json.get("result")); 
		return result;
    }
    public static String translateGet(String str) throws UnsupportedEncodingException{
    	boolean isChinese = Simicalcu.isContainChinese(str);
    	String url="";
		if (isChinese) {
			URLEncoder.encode("aa=中国", "UTF-8");
			url = "http://zsn.cc/trans4me/api.php?key=SocialTrans2017&lang=cn2en&in=";
			url = url + str;
		} else {
			url = "http://zsn.cc/trans4me/api.php?key=SocialTrans2017&lang=en2cn&in=";
			url = url + str;
		}
		String result ="";
		try {
			JSONObject json = HttpTool.httpGet(url);
			System.out.println(json);
			result = (String)(json.get("result")); 
		}catch (Exception e) {
			System.out.println("-----------------请求时间过长-----------------");
		}
		return result;
    }
    public static String translatePost1(String str) throws UnsupportedEncodingException{
    	boolean isChinese = Simicalcu.isContainChinese(str);
    	 Map<String, String> map = new HashMap<String, String>(); 
    	String url = "http://zsn.cc/trans4me/api.php";
    	
		if (isChinese) {
			map.put("lang", "cn2en");
		} else {
			map.put("lang", "en2cn");
		}
		map.put("key", "SocialTrans2017");
		map.put("in", str);
		System.out.println(map.toString());
		String result = HttpTool.doPost(url, map, "UTF-8", false);
		
		result = new String(result.getBytes("gbk"),"UTF-8");
		
		
		return result;

    }
} 