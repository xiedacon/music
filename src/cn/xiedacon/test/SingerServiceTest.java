package cn.xiedacon.test;

import java.util.List;

import org.junit.Test;

import cn.xiedacon.service.SingerService;
import cn.xiedacon.vo.SimpleSingerVo;
import cn.xiedacon.vo.SingerVo;

public class SingerServiceTest extends BaseTest{

	@Test
	public void testSelectListLimit(){
		SingerService singerService = this.getApplicationContext().getBean(SingerService.class);
		
		List<SimpleSingerVo> list = singerService.selectListLimit(0,30);
		
		System.out.println(list);
	}
	
	@Test
	public void testSelectListOrderByCollectionNumLimit(){
		SingerService singerService = this.getApplicationContext().getBean(SingerService.class);
		
		List<SimpleSingerVo> list = singerService.selectListOrderByCollectionNumLimit(0,100);
		
		System.out.println(list);
	}
	
	@Test
	public void testSelectListByClassifyIdOrderByCollectionNumLimit(){
		SingerService singerService = this.getApplicationContext().getBean(SingerService.class);
		
		List<SimpleSingerVo> list = singerService.selectListByClassifyIdOrderByCollectionNumLimit(1001,0,30);
		
		System.out.println(list);
	}
	
	@Test
	public void testSelectById(){
		SingerService singerService = this.getApplicationContext().getBean(SingerService.class);
		
		SingerVo bean = singerService.selectById("1");
		
		System.out.println(bean);
	}
	
	@Test
	public void testSelectIntroductionById(){
		SingerService singerService = this.getApplicationContext().getBean(SingerService.class);
		
		String bean = singerService.selectIntroductionById("1");
		
		System.out.println(bean);
	}
}
