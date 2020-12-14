package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.ReviewDAO;
import vo.Lecture;

public class ReviewWritePageService {

	public LinkedList<Lecture> reviewWritePage() {
		Connection conn = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(conn);
		LinkedList<Lecture> lecList = reviewDAO.selectLectureList();
		close(conn);
		
		return lecList;
	}

}
