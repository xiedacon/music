package cn.xiedacon.util;

public class Constant {

	public static Integer SUCCESS = 200;
	public static Integer INFO = 302;
	public static Integer ERROR = 404;
	public static String SUCCESS_RETURNNAME = "data";

	public static Integer PAGE_DEFAULT = 1;
	public static Integer BEGIN_DEFAULT = 0;
	public static Integer SINGER_SHOW_NUM = 100;
	public static Integer SINGER_SHOW_SONGNUM = 50;
	public static Boolean VISIBLE_DEFAULT = Boolean.TRUE;
	public static Integer NUM_DEFAULT = 0;
	public static Integer LIMIT_DEFAULT = 10;

	public static Integer EXCEL_BEGINNUM = 4;
	public static String EXCEL_SONGLIST_TEMPLATE = "template/songListTemplate.xlsx";

	public static String JSONURI_CLASSIFY = "jsons/userClassify.json";
	
	// 用户默认设置
	public static String USER_ICON_DEFAULT = "image/default.jpg";

	// 歌单默认设置
	public static String SONGMENU_IOCN_DEFAULT = "image/default_songMenu.jpg";
	public static Boolean SONGMENU_ISPUBLIC_DEFAULT = Boolean.TRUE;

	// 评论默认配置
	public static Integer COMMENT_HOT_AGREENUM = 10;
	public static Boolean COMMENT_ISCHECKED_DEFAULT = Boolean.FALSE;
}
