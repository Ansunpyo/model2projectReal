package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.ReviewDAO;

public class MyReviewLastPageService {

	public int getMyReviewLastPage(String id) {
		int lastPage = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			ReviewDAO reviewDAO = ReviewDAO.getInstance();
			reviewDAO.setConnection(conn);
			lastPage = reviewDAO.getMyReviewNumber(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return lastPage;
	}

}
