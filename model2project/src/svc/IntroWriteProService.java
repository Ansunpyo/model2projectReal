package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import dao.IntroDAO;
import vo.Intro;
public class IntroWriteProService {
	
	public static boolean registArticle(String id, Intro intro) throws Exception {
		
		boolean isWriteSuccess = false;
		Connection conn = getConnection();
		IntroDAO introDAO = IntroDAO.getInstance();
		introDAO.setConnection(conn);
		int insertCount = introDAO.insertArticle(id, intro);
		
		if(insertCount > 0) {
			commit(conn);
			isWriteSuccess = true;
		}
		else {
			rollback(conn);
		}
		
		close(conn);
		return isWriteSuccess;

	}
	
}