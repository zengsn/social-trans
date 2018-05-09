package com.crowd.utils;

import java.io.File;

public class Constant {

	public final static String VALID = "1";

	public final static String IN_VALID = "0";

	// 1M
	public final static long FILESIZE_0 = 1024 * 1024L;

	// 分割粒度
	public final static int LEVEL_SIZE_0 = 1;
	public final static int LEVEL_SIZE_1 = 2;

	// 分割后的文件名后缀
	public final static String SUFFIX = "txt";

	// 每页显示行数
	public final static int PAGE_LINE = 3;

	// 文件编码
	public final static String FILE_ENCODING = "GBK";
	// 分割粒度
	public final static int SIZE = 50;
	// 文件存放地址
	public final static String SOURCE_FILE_PATH = "C:" + File.separator
			+ "TestCst";
	public final static String DOWLOAD_FILE_PATH = "C:" + File.separator
			+ "socialTrans\\";

	// 头像宽度
	public final static int HEADIMAGE_WIDTH = 650;
	
	// 头像高度
	public final static int HEADIMAGE_HEIGHT = 650;
	// 根据行数手动设置章节
	public final static int CHAPTER_LINE = 2;
	//  与其他用户相似度比较
	public final static int SIMILAR_CONTEXT = 90;
	//机器翻译的最高相似度
	public final static int HIGH_SIMILAR = 90;
	//机器翻译最低相似度
	public final static int LOW_SIMILAR = 50;
}