package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.ReviewDAO;
import vo.Review;

public class ReviewUpdatePageService {

	public Review updateReview(int review_num) {
		Connection conn = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(conn);
		Review re = reviewDAO.updateReviewPage(review_num);
		close(conn);
		
		return re;
	}

}
