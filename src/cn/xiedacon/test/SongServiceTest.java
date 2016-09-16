package cn.xiedacon.test;

import java.util.List;

import org.junit.Test;

import cn.xiedacon.model.Song;
import cn.xiedacon.service.SongService;
import cn.xiedacon.test.base.BaseTest;

public class SongServiceTest extends BaseTest {

	@Test
	public void testSelectListBySongMenuIdOrderByRank() {
		SongService songService = this.getApplicationContext().getBean(SongService.class);

		List<Song> list = songService.selectListBySongMenuIdOrderByRank("1");

		System.out.println(list);
	}

	@Test
	public void testSelectListByAlbumIdOrderByRank() {
		SongService songService = this.getApplicationContext().getBean(SongService.class);

		List<Song> list = songService.selectListByAlbumIdOrderByRank("1");

		System.out.println(list);
	}

	@Test
	public void testSelectListBySongListIdOrderByRank() {
		SongService songService = this.getApplicationContext().getBean(SongService.class);

		List<Song> list = songService.selectListBySongListIdOrderByRank("1");

		System.out.println(list);
	}

	@Test
	public void testSelectById() {
		SongService songService = this.getApplicationContext().getBean(SongService.class);

		Song bean = songService.selectById("1");

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

		List<Song> bean = songService.selectListBySingerIdOrderByCollectionNumLimit("1", 0, 50);

		System.out.println(bean);
	}
	
	@Test
	public void testSelectFileUriById() {
		SongService songService = this.getApplicationContext().getBean(SongService.class);

		String bean = songService.selectFileUriById("1");

		System.out.println(bean);
	}
}
