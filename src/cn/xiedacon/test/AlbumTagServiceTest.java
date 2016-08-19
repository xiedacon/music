package cn.xiedacon.test;

import java.util.List;

import org.junit.Test;

import cn.xiedacon.service.AlbumTagService;
import cn.xiedacon.vo.AlbumTagVo;

public class AlbumTagServiceTest extends BaseTest{

	@Test
	public void testSelectAll(){
		AlbumTagService ablumTagService = this.getApplicationContext().getBean(AlbumTagService.class);
		List<AlbumTagVo> ablumTagList = ablumTagService.selectList();
		
		System.out.println(ablumTagList);
	}
}
