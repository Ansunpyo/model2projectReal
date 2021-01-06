package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.ReviewDAO;
import vo.Lecture;

public class ReviewWritePageService {

	public LinkedList<Lecture> reviewWritePage() {
		LinkedList<Lecture> lecList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			ReviewDAO reviewDAO = ReviewDAO.getInstance();
			reviewDAO.setConnection(conn);
			lecList = reviewDAO.selectLectureList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return lecList;
	}

}
