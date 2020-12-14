package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.ReviewDAO;

public class ReviewService {

	public LinkedList[] getReviewList(int nowPage) {
		Connection conn = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(conn);
		LinkedList[] reviewList = reviewDAO.selectReviewList(nowPage);
		close(conn);
		
		return reviewList;
	}

}
