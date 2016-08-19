package cn.xiedacon.test;

import java.util.List;

import org.junit.Test;

import cn.xiedacon.service.SongMenuSecondTagService;
import cn.xiedacon.vo.SongMenuSecondTagVo;

public class SongMenuSecondTagServiceTest extends BaseTest {

	@Test
	public void testSelectForIndex() {
		SongMenuSecondTagService songMenuSecondTagService = this.getApplicationContext().getBean(SongMenuSecondTagService.class);

		List<SongMenuSecondTagVo> list = songMenuSecondTagService.selectForIndex();

		System.out.println(list);
	}
	
	@Test
	public void testSelectAll() {
		SongMenuSecondTagService songMenuSecondTagService = this.getApplicationContext().getBean(SongMenuSecondTagService.class);

		List<SongMenuSecondTagVo> list = songMenuSecondTagService.selectAll();

		System.out.println(list);
	}
}
