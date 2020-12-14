package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.ReviewDAO;

public class ReviewDeleteService {

	public int deleteReview(int review_num) {
		Connection conn = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(conn);
		int result = reviewDAO.deleteReview(review_num);
		close(conn);
		
		return result;
	}

}
