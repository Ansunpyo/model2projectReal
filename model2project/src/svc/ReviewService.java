package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ReviewDAO;

public class ReviewService {

	public ArrayList[] getReviewList(int nowPage) {
		Connection conn = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(conn);
		ArrayList[] reviewList = reviewDAO.selectReviewList(nowPage);
		close(conn);
		
		return reviewList;
	}

}
