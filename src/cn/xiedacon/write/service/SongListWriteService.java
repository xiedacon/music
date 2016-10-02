package cn.xiedacon.write.service;

public interface SongListWriteService {

	void updatePlayNumById(Integer playNum, String id);

	void updateCommentNumById(Integer commentNum, String id);
}
