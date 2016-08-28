package cn.xiedacon.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.xiedacon.model.SongMenuSecondTag;
import cn.xiedacon.service.AlbumService;
import cn.xiedacon.service.SongListService;
import cn.xiedacon.service.SongMenuService;
import cn.xiedacon.service.SongMenuTagService;
import cn.xiedacon.service.UserService;
import cn.xiedacon.test.base.BaseTest;
import cn.xiedacon.vo.SimpleAlbumVo;
import cn.xiedacon.vo.SimpleSongListVo;
import cn.xiedacon.vo.SimpleSongMenuVo;
import cn.xiedacon.vo.SimpleUserVo;

public class A extends BaseTest {

	@Test
	public void test() {
		Map<String, Object> result = new HashMap<>();

		SongMenuService songMenuService = this.getApplicationContext().getBean(SongMenuService.class);
		List<SimpleSongMenuVo> songMenuList = new ArrayList<>();
		songMenuList.add(BuilderUtils.buildFrom(SimpleSongMenuVo.class, songMenuService.selectById("1")));
		songMenuList.add(BuilderUtils.buildFrom(SimpleSongMenuVo.class, songMenuService.selectById("2")));
		songMenuList.add(BuilderUtils.buildFrom(SimpleSongMenuVo.class, songMenuService.selectById("3")));
		songMenuList.add(BuilderUtils.buildFrom(SimpleSongMenuVo.class, songMenuService.selectById("4")));
		songMenuList.add(BuilderUtils.buildFrom(SimpleSongMenuVo.class, songMenuService.selectById("5")));
		songMenuList.add(BuilderUtils.buildFrom(SimpleSongMenuVo.class, songMenuService.selectById("6")));
		songMenuList.add(BuilderUtils.buildFrom(SimpleSongMenuVo.class, songMenuService.selectById("7")));
		songMenuList.add(BuilderUtils.buildFrom(SimpleSongMenuVo.class, songMenuService.selectById("8")));

		SongMenuTagService songMenuTagService = this.getApplicationContext().getBean(SongMenuTagService.class);
		List<SongMenuSecondTag> songMenuTagList = new ArrayList<>();
		songMenuTagList.add(songMenuTagService.selectSecondTagById("1"));
		songMenuTagList.add(songMenuTagService.selectSecondTagById("2"));
		songMenuTagList.add(songMenuTagService.selectSecondTagById("3"));
		songMenuTagList.add(songMenuTagService.selectSecondTagById("4"));
		songMenuTagList.add(songMenuTagService.selectSecondTagById("5"));

		AlbumService albumService = this.getApplicationContext().getBean(AlbumService.class);
		List<SimpleAlbumVo> albumList = new ArrayList<>();
		albumList.add(BuilderUtils.buildFrom(SimpleAlbumVo.class, albumService.selectById("1")));
		albumList.add(BuilderUtils.buildFrom(SimpleAlbumVo.class, albumService.selectById("2")));
		albumList.add(BuilderUtils.buildFrom(SimpleAlbumVo.class, albumService.selectById("3")));
		albumList.add(BuilderUtils.buildFrom(SimpleAlbumVo.class, albumService.selectById("4")));
		albumList.add(BuilderUtils.buildFrom(SimpleAlbumVo.class, albumService.selectById("5")));
		albumList.add(BuilderUtils.buildFrom(SimpleAlbumVo.class, albumService.selectById("6")));
		albumList.add(BuilderUtils.buildFrom(SimpleAlbumVo.class, albumService.selectById("7")));
		albumList.add(BuilderUtils.buildFrom(SimpleAlbumVo.class, albumService.selectById("8")));
		albumList.add(BuilderUtils.buildFrom(SimpleAlbumVo.class, albumService.selectById("9")));
		albumList.add(BuilderUtils.buildFrom(SimpleAlbumVo.class, albumService.selectById("10")));

		UserService userService = this.getApplicationContext().getBean(UserService.class);
		List<SimpleUserVo> userList = new ArrayList<>();
		userList.add(BuilderUtils.buildFrom(SimpleUserVo.class, userService.selectById("1")));
		userList.add(BuilderUtils.buildFrom(SimpleUserVo.class, userService.selectById("2")));
		userList.add(BuilderUtils.buildFrom(SimpleUserVo.class, userService.selectById("3")));
		userList.add(BuilderUtils.buildFrom(SimpleUserVo.class, userService.selectById("4")));
		userList.add(BuilderUtils.buildFrom(SimpleUserVo.class, userService.selectById("5")));

		SongListService songListService = this.getApplicationContext().getBean(SongListService.class);
		List<SimpleSongListVo> songLists = new ArrayList<>();
		songLists.add(BuilderUtils.buildFrom(SimpleSongListVo.class, songListService.selectById("1")));
		songLists.add(BuilderUtils.buildFrom(SimpleSongListVo.class, songListService.selectById("2")));
		songLists.add(BuilderUtils.buildFrom(SimpleSongListVo.class, songListService.selectById("3")));

		result.put("songMenus", songMenuList);
		result.put("songMenuTags", songMenuTagList);
		result.put("albums", albumList);
		result.put("users", userList);
		result.put("songLists", songLists);

		String json = JsonUtils.stringify(result);
		System.out.println(json);

	}
}
