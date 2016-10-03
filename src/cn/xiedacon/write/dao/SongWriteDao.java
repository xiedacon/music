package cn.xiedacon.write.dao;

public interface SongWriteDao {

	void updatePlayNumById(Integer playNum, String id);

	void updateCommentNumById(Integer commentNum, String id);

}
