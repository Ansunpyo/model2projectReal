package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.LectureDAO;

public class PaidCheckService {

	public boolean paidCheck(String id, int lecture_num) {
		boolean paid = false;
		Connection conn = null;
		try {
			conn = getConnection();
			LectureDAO lectureDAO = LectureDAO.getInstance();
			lectureDAO.setConnection(conn);
			paid = lectureDAO.paidCheck(id, lecture_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return paid;
	}

}
