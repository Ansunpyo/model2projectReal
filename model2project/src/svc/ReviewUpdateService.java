package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.ReviewDAO;
import vo.Review;

public class ReviewUpdateService {

	public int updateReview(Review re) {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			ReviewDAO reviewDAO = ReviewDAO.getInstance();
			reviewDAO.setConnection(conn);
			result = reviewDAO.updateReview(re);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

}
