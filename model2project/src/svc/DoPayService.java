package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.LectureDAO;

public class DoPayService {

	public int doPay(String id, int lecture_num, String type, String pay_code) {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			LectureDAO lectureDAO = LectureDAO.getInstance();
			lectureDAO.setConnection(conn);
			result = lectureDAO.doPay(id, lecture_num, type, pay_code);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

}
