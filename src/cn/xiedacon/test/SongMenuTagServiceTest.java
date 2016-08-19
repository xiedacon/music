package cn.xiedacon.test;

import java.util.List;

import org.junit.Test;

import cn.xiedacon.service.SongMenuTagService;
import cn.xiedacon.vo.SongMenuTagVo;

public class SongMenuTagServiceTest extends BaseTest {


	@Test
	public void testSelectAll() {
		SongMenuTagService songMenuTagService = this.getApplicationContext().getBean(SongMenuTagService.class);

		List<SongMenuTagVo> list = songMenuTagService.selectAll();

		System.out.println(list);
	}
}
