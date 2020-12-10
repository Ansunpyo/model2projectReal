package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ReviewDAO;
import vo.Lecture;

public class ReviewWritePageService {

	public ArrayList<Lecture> reviewWritePage() {
		Connection conn = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(conn);
		ArrayList<Lecture> lecList = reviewDAO.selectLectureList();
		close(conn);
		
		return lecList;
	}

}
