package cn.xiedacon.test;

import java.util.List;

import org.junit.Test;

import cn.xiedacon.service.SongMenuService;
import cn.xiedacon.util.PageBean;
import cn.xiedacon.vo.SimpleSongMenuVo;
import cn.xiedacon.vo.SongMenuVo;

public class SongMenuServiceTest extends BaseTest {

	@Test
	public void testSelectForIndex() {
		SongMenuService songMenuService = this.getApplicationContext().getBean(SongMenuService.class);

		List<SimpleSongMenuVo> list = songMenuService.selectForIndex();

		System.out.println(list);
	}

	@Test
	public void testSelectSongMenuById() {
		SongMenuService songMenuService = this.getApplicationContext().getBean(SongMenuService.class);

		String id = "7";
		SongMenuVo songMenu = songMenuService.selectSongMenuById(id);

		System.out.println(songMenu);
	}

	@Test
	public void testSelectPageBean() {
		SongMenuService songMenuService = this.getApplicationContext().getBean(SongMenuService.class);

		Integer page = 1;
		PageBean<SimpleSongMenuVo> pageBean = songMenuService.selectPageBean(page);

		System.out.println(pageBean);
	}

	@Test
	public void testSelectPageBeanOrderByCollectionNum() {
		SongMenuService songMenuService = this.getApplicationContext().getBean(SongMenuService.class);

		Integer page = 1;
		PageBean<SimpleSongMenuVo> pageBean = songMenuService.selectPageBeanOrderByCollectionNum(page);

		System.out.println(pageBean);
	}

	@Test
	public void testSelectPageBeanBySecondTagId() {
		SongMenuService songMenuService = this.getApplicationContext().getBean(SongMenuService.class);

		String secondTagId = "1";
		Integer page = 1;
		PageBean<SimpleSongMenuVo> pageBean = songMenuService.selectPageBeanBySecondTagId(secondTagId, page);

		System.out.println(pageBean);
	}

	@Test
	public void testSelectPageBeanBySecondTagIdOrderByCollectionNum() {
		SongMenuService songMenuService = this.getApplicationContext().getBean(SongMenuService.class);

		String secondTagId = "1";
		Integer page = 1;
		PageBean<SimpleSongMenuVo> pageBean = songMenuService
				.selectPageBeanBySecondTagIdOrderByCollectionNum(secondTagId, page);

		System.out.println(pageBean);
	}

	@Test
	public void testSelectListByUserId() {
		SongMenuService songMenuService = this.getApplicationContext().getBean(SongMenuService.class);

		List<SimpleSongMenuVo> bean = songMenuService.selectListByCollectorId("1");

		System.out.println(bean);
	}

	@Test
	public void testSelectListByCreatorId() {
		SongMenuService songMenuService = this.getApplicationContext().getBean(SongMenuService.class);

		List<SimpleSongMenuVo> bean = songMenuService.selectListByCreatorId("1");

		System.out.println(bean);
	}
}
