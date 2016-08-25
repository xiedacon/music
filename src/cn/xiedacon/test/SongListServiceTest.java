package cn.xiedacon.test;

import java.util.List;

import org.junit.Test;

import cn.xiedacon.model.SongList;
import cn.xiedacon.service.SongListService;
import cn.xiedacon.test.base.BaseTest;

public class SongListServiceTest extends BaseTest {

	@Test
	public void testSelectById() {
		SongListService songListService = this.getApplicationContext().getBean(SongListService.class);

		SongList bean = songListService.selectById("1");

		System.out.println(bean);
	}

	@Test
	public void testSelectList() {
		SongListService songListService = this.getApplicationContext().getBean(SongListService.class);

		List<SongList> bean = songListService.selectList();

		System.out.println(bean);
	}
}
