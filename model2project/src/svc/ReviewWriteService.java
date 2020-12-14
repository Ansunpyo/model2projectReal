package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.ReviewDAO;
import vo.Review;

public class ReviewWriteService {

	public int writeReview(String id, Review re) {
		Connection conn = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(conn);
		int result = reviewDAO.writeReview(id, re);
		close(conn);
		
		return result;
	}

}
