package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.LectureDAO;
import vo.Lecture_Video;

public class MainLectureService {

	public LinkedList<Lecture_Video> getLectureThumbnail() {
		LinkedList<Lecture_Video> lecList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			LectureDAO lectureDAO = LectureDAO.getInstance();
			lectureDAO.setConnection(conn);
			lecList = lectureDAO.getLectureThumbnail();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return lecList;
	}

}
