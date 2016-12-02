package cn.xiedacon.test;

import java.util.List;

import org.junit.Test;

import cn.xiedacon.model.AlbumTag;
import cn.xiedacon.service.AlbumTagService;
import cn.xiedacon.test.base.BaseTest;

public class AlbumTagServiceTest extends BaseTest{

	@Test
	public void testSelectAll(){
		AlbumTagService ablumTagService = this.getApplicationContext().getBean(AlbumTagService.class);
		List<AlbumTag> ablumTagList = ablumTagService.selectList();
		
		System.out.println(ablumTagList);
	}
}
