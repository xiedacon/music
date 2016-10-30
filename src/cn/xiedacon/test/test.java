package cn.xiedacon.test;

import org.junit.Test;

public class test {

	@Test
	public void a(){
		String a = "aaa_aa/";
		System.out.println(a.matches("\\w+"));
	}
}
