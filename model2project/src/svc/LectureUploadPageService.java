package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.LectureDAO;
import vo.Subject;

public class LectureUploadPageService {

	public LinkedList<Subject> getSubList() {
		Connection conn = getConnection();
		LectureDAO lectureDAO = LectureDAO.getInstance();
		lectureDAO.setConnection(conn);
		LinkedList<Subject> subList = lectureDAO.selectSubList();
		close(conn);
		
		return subList;
	}

}
