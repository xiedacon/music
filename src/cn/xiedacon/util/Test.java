package cn.xiedacon.util;

public class Test {

	public static void main(String[] args) {
		System.out.println("begin");
		
		long begin = System.currentTimeMillis();
		testAdd("a");
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
		
		begin = System.currentTimeMillis();
		testStringBuffer("a");
		end = System.currentTimeMillis();
		System.out.println(end - begin);
		
		System.out.println("end");
	}

	public static void testAdd(String str) {
		String a = "";
		for (int i = 0; i < 1000000; i++) {
			a += str;
		}
	}

	public static void testStringBuffer(String str) {
		StringBuffer a = new StringBuffer();
		for (int i = 0; i < 1000000; i++) {
			a.append(str);
		}
	}
}
