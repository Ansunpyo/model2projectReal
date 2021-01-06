package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.ReviewDAO;

public class ReviewViewService {

	public LinkedList<Object> getReviewView(int review_num) {
		LinkedList<Object> reviewList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			ReviewDAO reviewDAO = ReviewDAO.getInstance();
			reviewDAO.setConnection(conn);
			reviewList = reviewDAO.selectReviewView(review_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return reviewList;
	}

	public LinkedList[] getReviewCom(int review_num) {
		LinkedList[] reviewComList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			ReviewDAO reviewDAO = ReviewDAO.getInstance();
			reviewDAO.setConnection(conn);
			reviewComList = reviewDAO.selectReviewCom(review_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return reviewComList;
	}

}
