package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.ReviewDAO;

public class ReviewViewService {

	public LinkedList<Object> getReviewView(int review_num) {
		Connection conn = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(conn);
		LinkedList<Object> reviewList = reviewDAO.selectReviewView(review_num);
		close(conn);
		
		return reviewList;
	}

}
