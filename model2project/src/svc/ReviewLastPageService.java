package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.ReviewDAO;

public class ReviewLastPageService {

	public int getReviewLastPage() {
		Connection conn = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(conn);
		int lastPage = reviewDAO.getReviewNumber();
		close(conn);
		
		return lastPage;
	}

}
