package cn.xiedacon.test;

import java.util.List;

import org.junit.Test;

import cn.xiedacon.service.CommentService;
import cn.xiedacon.util.PageBean;
import cn.xiedacon.vo.CommentVo;

public class CommentServiceTest extends BaseTest{

	@Test
	public void testSelectCommentsBySongMenuId(){
		CommentService commentService = this.getApplicationContext().getBean(CommentService.class);
		
		List<CommentVo> list = commentService.selectForHotBySongMenuId("1");
		
		System.out.println(list);
	}

	@Test
	public void testSelectPageBeanBySongMenuId(){
		CommentService commentService = this.getApplicationContext().getBean(CommentService.class);
		
		PageBean<CommentVo> list = commentService.selectPageBeanBySongMenuId("1",1);
		
		System.out.println(list);
	}
	
	@Test
	public void testSelectCommentsByAlbumId(){
		CommentService commentService = this.getApplicationContext().getBean(CommentService.class);
		
		List<CommentVo> list = commentService.selectForHotByAlbumId("1");
		
		System.out.println(list);
	}

	@Test
	public void testSelectPageBeanByAlbumId(){
		CommentService commentService = this.getApplicationContext().getBean(CommentService.class);
		
		PageBean<CommentVo> list = commentService.selectPageBeanByAlbumId("1",1);
		
		System.out.println(list);
	}
	
	@Test
	public void testSelectHotBySongListId(){
		CommentService commentService = this.getApplicationContext().getBean(CommentService.class);
		
		List<CommentVo> list = commentService.selectHotBySongListId("1");
		
		System.out.println(list);
	}
	
	@Test
	public void testSelectPageBeanBySongListIdLimit(){
		CommentService commentService = this.getApplicationContext().getBean(CommentService.class);
		
		PageBean<CommentVo> list = commentService.selectPageBeanBySongListIdLimit("1",1);
		
		System.out.println(list);
	}
	
	@Test
	public void testselectForHotBySongId(){
		CommentService commentService = this.getApplicationContext().getBean(CommentService.class);
		
		List<CommentVo> list = commentService.selectForHotBySongId("1");
		
		System.out.println(list);
	}
	
	@Test
	public void testSelectPageBeanBySongId(){
		CommentService commentService = this.getApplicationContext().getBean(CommentService.class);
		
		PageBean<CommentVo> list = commentService.selectPageBeanBySongId("1",1);
		
		System.out.println(list);
	}
}
