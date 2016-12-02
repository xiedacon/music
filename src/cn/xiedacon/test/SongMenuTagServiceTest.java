package cn.xiedacon.test;

import java.util.List;

import org.junit.Test;

import cn.xiedacon.model.SongMenuFirstTag;
import cn.xiedacon.service.SongMenuTagService;
import cn.xiedacon.test.base.BaseTest;

public class SongMenuTagServiceTest extends BaseTest {


	@Test
	public void testSelectAll() {
		SongMenuTagService songMenuTagService = this.getApplicationContext().getBean(SongMenuTagService.class);

		List<SongMenuFirstTag> list = songMenuTagService.selectList();

		System.out.println(list);
	}
}
