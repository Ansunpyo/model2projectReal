package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.IntroDAO;

public class IntroDeleteProService {

	public boolean isArticleWriter(int number, String pass) {
		boolean isArticleWriter = false;
		Connection conn = getConnection();
		IntroDAO introDAO = IntroDAO.getInstance();
		introDAO.setConnection(conn);
		isArticleWriter = introDAO.isArticleIntroWriter(number, pass);
		if (conn != null) close(conn);
		
		return isArticleWriter;
	}

	public boolean removeArticle(int number) {
		boolean isRemoveSuccess = false;
		Connection conn = getConnection();
		IntroDAO introDAO = IntroDAO.getInstance();
		introDAO.setConnection(conn);
		int deleteCount = introDAO.deleteArticle(number);
		
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
