package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.IntroDAO;

public class IntroDeleteProService {
	public boolean removeArticle(int intro_num) {
		boolean isRemoveSuccess = false;
		Connection conn = getConnection();
		IntroDAO introDAO = IntroDAO.getInstance();
		introDAO.setConnection(conn);
		int deleteCount = introDAO.deleteArticle(intro_num);
		
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
