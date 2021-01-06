package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.IntroDAO;
import vo.Intro;
public class IntroWriteProService {
	
	public static boolean registArticle(String id, Intro intro) throws Exception {
		
		boolean isWriteSuccess = false;
		Connection conn = null;
		try {
			conn = getConnection();
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return isWriteSuccess;

	}
	
}