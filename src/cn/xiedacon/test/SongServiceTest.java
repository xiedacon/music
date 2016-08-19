package cn.xiedacon.test;

import java.util.List;

import org.junit.Test;

import cn.xiedacon.service.SongService;
import cn.xiedacon.vo.SimpleSongVo;
import cn.xiedacon.vo.SongVo;

public class SongServiceTest extends BaseTest {

	@Test
	public void testSelectListBySongMenuIdOrderByRank() {
		SongService songService = this.getApplicationContext().getBean(SongService.class);

		List<SimpleSongVo> list = songService.selectListBySongMenuIdOrderByRank("1");

		System.out.println(list);
	}

	@Test
	public void testSelectListByAlbumIdOrderByRank() {
		SongService songService = this.getApplicationContext().getBean(SongService.class);

		List<SimpleSongVo> list = songService.selectListByAlbumIdOrderByRank("1");

		System.out.println(list);
	}

	@Test
	public void testSelectListBySongListIdOrderByRank() {
		SongService songService = this.getApplicationContext().getBean(SongService.class);

		List<SimpleSongVo> list = songService.selectListBySongListIdOrderByRank("1");

		System.out.println(list);
	}

	@Test
	public void testSelectById() {
		SongService songService = this.getApplicationContext().getBean(SongService.class);

		SongVo bean = songService.selectById("1");

		System.out.println(bean);
	}

	@Test
	public void testSelectLyricUriById() {
		SongService songService = this.getApplicationContext().getBean(SongService.class);

		String bean = songService.selectLyricUriById("1");

		System.out.println(bean);
	}

	@Test
	public void testSelectListBySingerIdOrderByCollectionNumLimit() {
		SongService songService = this.getApplicationContext().getBean(SongService.class);

		List<SongVo> bean = songService.selectListBySingerIdOrderByCollectionNumLimit("1", 0, 50);

		System.out.println(bean);
	}
	
	@Test
	public void testSelectFileUriById() {
		SongService songService = this.getApplicationContext().getBean(SongService.class);

		String bean = songService.selectFileUriById("1");

		System.out.println(bean);
	}
}
