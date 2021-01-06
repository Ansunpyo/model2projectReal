package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.LectureDAO;
import vo.Subject;

public class LectureUploadPageService {

	public LinkedList<Subject> getSubList() {
		LinkedList<Subject> subList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			LectureDAO lectureDAO = LectureDAO.getInstance();
			lectureDAO.setConnection(conn);
			subList = lectureDAO.selectSubList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return subList;
	}

}
