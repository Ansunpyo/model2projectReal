package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.LectureDAO;
import vo.Lecture;
import vo.Lecture_Video;

public class LectureUploadService {

	public int lectureUpload(String id, Lecture lec, Lecture_Video vid) {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			LectureDAO lectureDAO = LectureDAO.getInstance();
			lectureDAO.setConnection(conn);
			result = lectureDAO.lectureUpload(id, lec, vid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

	public int lectureModify(Lecture lec) {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			LectureDAO lectureDAO = LectureDAO.getInstance();
			lectureDAO.setConnection(conn);
			result = lectureDAO.lectureModify(lec);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

}
