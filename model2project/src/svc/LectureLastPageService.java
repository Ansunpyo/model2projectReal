package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.LectureDAO;

public class LectureLastPageService {

	public int getLectureLastPage() {
		Connection conn = getConnection();
		LectureDAO lectureDAO = LectureDAO.getInstance();
		lectureDAO.setConnection(conn);
		int lastPage = lectureDAO.getLectureNumber();
		close(conn);
		
		return lastPage;
	}

}
