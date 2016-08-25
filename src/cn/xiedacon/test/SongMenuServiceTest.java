package cn.xiedacon.test;

import java.util.List;

import org.junit.Test;

import cn.xiedacon.model.SongMenu;
import cn.xiedacon.service.SongMenuService;
import cn.xiedacon.test.base.BaseTest;
import cn.xiedacon.util.PageBean;

public class SongMenuServiceTest extends BaseTest {

	@Test
	public void testSelectSongMenuById() {
		SongMenuService songMenuService = this.getApplicationContext().getBean(SongMenuService.class);

		String id = "7";
		SongMenu songMenu = songMenuService.selectById(id);

		System.out.println(songMenu);
	}

	@Test
	public void testSelectPageBean() {
		SongMenuService songMenuService = this.getApplicationContext().getBean(SongMenuService.class);

		Integer page = 1;
		PageBean<SongMenu> pageBean = songMenuService.selectPageBean(page);

		System.out.println(pageBean);
	}

	@Test
	public void testSelectPageBeanOrderByCollectionNum() {
		SongMenuService songMenuService = this.getApplicationContext().getBean(SongMenuService.class);

		Integer page = 1;
		PageBean<SongMenu> pageBean = songMenuService.selectPageBeanOrderByCollectionNum(page);

		System.out.println(pageBean);
	}

	@Test
	public void testSelectPageBeanBySecondTagId() {
		SongMenuService songMenuService = this.getApplicationContext().getBean(SongMenuService.class);

		String secondTagId = "1";
		Integer page = 1;
		PageBean<SongMenu> pageBean = songMenuService.selectPageBeanBySecondTagId(secondTagId, page);

		System.out.println(pageBean);
	}

	@Test
	public void testSelectPageBeanBySecondTagIdOrderByCollectionNum() {
		SongMenuService songMenuService = this.getApplicationContext().getBean(SongMenuService.class);

		String secondTagId = "1";
		Integer page = 1;
		PageBean<SongMenu> pageBean = songMenuService
				.selectPageBeanBySecondTagIdOrderByCollectionNum(secondTagId, page);

		System.out.println(pageBean);
	}

	@Test
	public void testSelectListByUserId() {
		SongMenuService songMenuService = this.getApplicationContext().getBean(SongMenuService.class);

		List<SongMenu> bean = songMenuService.selectListByCollectorId("1");

		System.out.println(bean);
	}

	@Test
	public void testSelectListByCreatorId() {
		SongMenuService songMenuService = this.getApplicationContext().getBean(SongMenuService.class);

		List<SongMenu> bean = songMenuService.selectListByCreatorId("1");

		System.out.println(bean);
	}
}
