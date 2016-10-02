package cn.xiedacon.write.dao;

public interface SongListWriteDao {

	void updatePlayNumById(Integer playNum, String id);

	void updateCommentNumById(Integer commentNum, String id);

}
