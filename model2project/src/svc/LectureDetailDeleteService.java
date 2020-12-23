package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.LectureDAO;

public class LectureDetailDeleteService {
	public boolean removeArticle(int chapter, int lecture_num) {
		boolean isRemoveSuccess = false;
		Connection conn = getConnection();
		LectureDAO lectureDAO = LectureDAO.getInstance();
		lectureDAO.setConnection(conn);
		int deleteCount = lectureDAO.deleteArticle(chapter, lecture_num);
		
		if(deleteCount > 0) {
			commit(conn);
			isRemoveSuccess = true;
		} else {
			rollback(conn);
		}
		if(conn != null) close(conn);
		return isRemoveSuccess;
	}

}
