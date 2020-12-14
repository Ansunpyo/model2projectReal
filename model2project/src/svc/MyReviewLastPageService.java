package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.ReviewDAO;

public class MyReviewLastPageService {

	public int getMyReviewLastPage(String id) {
		Connection conn = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(conn);
		int lastPage = reviewDAO.getMyReviewNumber(id);
		close(conn);
		
		return lastPage;
	}

}
