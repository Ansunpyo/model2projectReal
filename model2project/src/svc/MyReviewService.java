package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.ReviewDAO;

public class MyReviewService {

	public LinkedList[] getMyReviewList(String id, int nowPage) {
		Connection conn = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(conn);
		LinkedList[] reviewList = reviewDAO.selectMyReviewList(id, nowPage);
		close(conn);
		
		return reviewList;
	}

}
