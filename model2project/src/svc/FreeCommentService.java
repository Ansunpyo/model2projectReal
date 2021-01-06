package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.FreeDAO;
import vo.Free_Comment;

public class FreeCommentService {

	public int freeCommentExtra(Free_Comment fc, int number, int parent, int free_num) {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			FreeDAO freeDAO = FreeDAO.getInstance();
			freeDAO.setConnection(conn);
			result = freeDAO.exComment(fc, number, parent, free_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

	public int freeCommentDelete(int free_num, int comment_num) {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			FreeDAO freeDAO = FreeDAO.getInstance();
			freeDAO.setConnection(conn);
			result = freeDAO.delComment(free_num, comment_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

	public int freeCommentWrite(int free_num, int number, String contents) {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			FreeDAO freeDAO = FreeDAO.getInstance();
			freeDAO.setConnection(conn);
			result = freeDAO.writeComment(free_num, number, contents);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

}
