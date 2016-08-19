package cn.xiedacon.test;

import java.util.List;

import org.junit.Test;

import cn.xiedacon.service.AlbumService;
import cn.xiedacon.util.PageBean;
import cn.xiedacon.vo.AlbumVo;
import cn.xiedacon.vo.SimpleAlbumVo;

public class AlbumServiceTest extends BaseTest{

	@Test
	public void testSelectHotList(){
		AlbumService ablumService = this.getApplicationContext().getBean(AlbumService.class);
		
		List<SimpleAlbumVo> list = ablumService.selectHotList();
		
		System.out.println(list);
	}
	
	@Test
	public void testSelectById(){
		AlbumService ablumService = this.getApplicationContext().getBean(AlbumService.class);
		
		AlbumVo list = ablumService.selectById("1");
		
		System.out.println(list);
	}
	
	@Test
	public void testSelectPageBeanByTagIdOrderByCreateTimeLimitelectAlbumById(){
		AlbumService ablumService = this.getApplicationContext().getBean(AlbumService.class);
		
		PageBean<SimpleAlbumVo> bean = ablumService.selectPageBeanByTagIdOrderByCreateTimeLimit("1",1);
		
		System.out.println(bean);
	}
	
	@Test
	public void testSelectPageBeanBySingerIdOrderByCreateTime(){
		AlbumService ablumService = this.getApplicationContext().getBean(AlbumService.class);
		
		PageBean<SimpleAlbumVo> bean = ablumService.selectPageBeanBySingerIdOrderByCreateTime("1",1);
		
		System.out.println(bean);
	}
}
