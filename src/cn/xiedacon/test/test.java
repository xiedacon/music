package cn.xiedacon.test;

import org.junit.Test;

public class test {

	@Test
	public void a(){
		String a = "59:00";
		System.out.println(a.matches("[0-5]\\d:[0-5]\\d"));
	}
}
