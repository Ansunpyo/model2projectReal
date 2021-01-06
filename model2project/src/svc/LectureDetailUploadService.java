package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.LectureDAO;
import vo.Lecture;
import vo.Lecture_Video;

public class LectureDetailUploadService {

	public int lectureDetailUpload(String id, Lecture lec, Lecture_Video vid) {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			LectureDAO lectureDAO = LectureDAO.getInstance();
			lectureDAO.setConnection(conn);
			result = lectureDAO.lectureDetailUpload(id, lec, vid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

}
