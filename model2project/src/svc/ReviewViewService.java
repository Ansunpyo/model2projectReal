package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ReviewDAO;

public class ReviewViewService {

	public ArrayList<Object> getReviewView(int review_num) {
		Connection conn = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(conn);
		ArrayList<Object> reviewList = reviewDAO.selectReviewView(review_num);
		close(conn);
		
		return reviewList;
	}

}
