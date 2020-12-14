package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.ReviewDAO;
import vo.Review;

public class ReviewUpdateService {

	public int updateReview(Review re) {
		Connection conn = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(conn);
		int result = reviewDAO.updateReview(re);
		close(conn);
		
		return result;
	}

}
