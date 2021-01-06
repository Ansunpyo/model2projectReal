package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.LectureDAO;

public class LectureLastPageService {

	public int getLectureLastPage() {
		int lastPage = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			LectureDAO lectureDAO = LectureDAO.getInstance();
			lectureDAO.setConnection(conn);
			lastPage = lectureDAO.getLectureNumber();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return lastPage;
	}

	public int getLectureLastPage(String id) {
		int lastPage = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			LectureDAO lectureDAO = LectureDAO.getInstance();
			lectureDAO.setConnection(conn);
			lastPage = lectureDAO.getLectureNumber(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return lastPage;
	}

}
