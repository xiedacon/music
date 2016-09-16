package cn.xiedacon.test;

import java.util.List;

import org.junit.Test;

import cn.xiedacon.model.SongMenu;
import cn.xiedacon.service.SongMenuService;
import cn.xiedacon.test.base.BaseTest;
import cn.xiedacon.test.base.Priority;
import cn.xiedacon.util.PageBean;
import cn.xiedacon.util.UUIDUtils;

public class SongMenuServiceTest extends BaseTest {

	@Test
	public void testSelectSongMenuById() {
		SongMenuService songMenuService = this.getApplicationContext().getBean(SongMenuService.class);

		SongMenu bean = songMenuService.selectById(songMenu.getId());

		System.out.println(bean);
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
		PageBean<SongMenu> pageBean = songMenuService.selectPageBeanBySecondTagIdOrderByCollectionNum(secondTagId,
				page);

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

	@Test
	public void testUpdateIconById() {
		SongMenuService songMenuService = this.getApplicationContext().getBean(SongMenuService.class);

		songMenuService.updateIconById("aaa", songMenu.getId());
	}

	private SongMenu songMenu;

	@Test
	@Priority
	public void testInsert() {
		SongMenuService songMenuService = this.getApplicationContext().getBean(SongMenuService.class);
		songMenu = this.getFactory().get(SongMenu.class);
		songMenu.setId(UUIDUtils.randomUUID());
		songMenuService.insert(songMenu);
	}

	@Test
	public void testUpdate() {
		SongMenuService songMenuService = this.getApplicationContext().getBean(SongMenuService.class);
		songMenu.setName("test");
		songMenuService.update(songMenu);
	}

	@Test
	public void testDelete() {
		SongMenuService songMenuService = this.getApplicationContext().getBean(SongMenuService.class);
		songMenuService.delete(songMenu);
	}
}
