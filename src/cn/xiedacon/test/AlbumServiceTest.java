package cn.xiedacon.test;


import org.junit.Test;

import cn.xiedacon.model.Album;
import cn.xiedacon.service.AlbumService;
import cn.xiedacon.test.base.BaseTest;
import cn.xiedacon.util.PageBean;

public class AlbumServiceTest extends BaseTest{

	@Test
	public void testSelectById(){
		AlbumService ablumService = this.getApplicationContext().getBean(AlbumService.class);
		
		Album list = ablumService.selectById("1");
		
		System.out.println(list);
	}
	
	@Test
	public void testSelectPageBeanByTagIdOrderByCreateTimeLimitelectAlbumById(){
		AlbumService ablumService = this.getApplicationContext().getBean(AlbumService.class);
		
		PageBean<Album> bean = ablumService.selectPageBeanByTagIdOrderByCreateTimeLimit("1",1);
		
		System.out.println(bean);
	}
	
	@Test
	public void testSelectPageBeanBySingerIdOrderByCreateTime(){
		AlbumService ablumService = this.getApplicationContext().getBean(AlbumService.class);
		
		PageBean<Album> bean = ablumService.selectPageBeanBySingerIdOrderByCreateTime("1",1);
		
		System.out.println(bean);
	}
}
