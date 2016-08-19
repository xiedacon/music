package cn.xiedacon.test;

import java.util.List;

import org.junit.Test;

import cn.xiedacon.service.SongListService;
import cn.xiedacon.vo.SimpleSongListVo;
import cn.xiedacon.vo.SongListVo;

public class SongListServiceTest extends BaseTest {

	@Test
	public void testSelectForIndex() {
		SongListService songListService = this.getApplicationContext().getBean(SongListService.class);

		List<SimpleSongListVo> list = songListService.selectForIndex();

		System.out.println(list);
	}

	@Test
	public void testSelectById() {
		SongListService songListService = this.getApplicationContext().getBean(SongListService.class);

		SongListVo bean = songListService.selectById("1");

		System.out.println(bean);
	}

	@Test
	public void testSelectList() {
		SongListService songListService = this.getApplicationContext().getBean(SongListService.class);

		List<SongListVo> list = songListService.selectList();

		System.out.println(list);
	}
}
