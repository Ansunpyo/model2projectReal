package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.LectureDAO;

public class LectureDetailDeleteService {
	public boolean removeArticle(int chapter, int lecture_num) {
		boolean isRemoveSuccess = false;
		Connection conn = null;
		try {
			conn = getConnection();
			LectureDAO lectureDAO = LectureDAO.getInstance();
			lectureDAO.setConnection(conn);
			int deleteCount = lectureDAO.deleteArticle(chapter, lecture_num);
			
			if(deleteCount > 0) {
				commit(conn);
				isRemoveSuccess = true;
			} else {
				rollback(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return isRemoveSuccess;
	}

}
