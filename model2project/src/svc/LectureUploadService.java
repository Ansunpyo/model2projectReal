package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.LectureDAO;
import vo.Lecture;
import vo.Lecture_Video;

public class LectureUploadService {

	public int lectureUpload(String id, Lecture lec, Lecture_Video vid) {
		Connection conn = getConnection();
		LectureDAO lectureDAO = LectureDAO.getInstance();
		lectureDAO.setConnection(conn);
		int result = lectureDAO.lectureUpload(id, lec, vid);
		close(conn);
		
		return result;
	}

}
