package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.IntroDAO;
import vo.Intro;

public class IntroModifyProService {

	public boolean isArticleWriter(int intro_num, String pass) {
		boolean isArticleWriter = false;
		Connection conn = getConnection();
		IntroDAO introDAO = IntroDAO.getInstance();
		introDAO.setConnection(conn);
		isArticleWriter = introDAO.isArticleIntroWriter(intro_num, pass);
		if(conn != null) close(conn);
		return isArticleWriter;
	}

	public boolean modifyArticle(Intro article) {
		boolean isModifySuccess = false;
		Connection conn = getConnection();
		IntroDAO introDAO = IntroDAO.getInstance();
		introDAO.setConnection(conn);
		int updateCount = introDAO.updateArticle(article);
		
		if(updateCount > 0) {
			commit(conn);
			isModifySuccess = true;
		} else {
			rollback(conn);
		}
		
		if(conn != null) close(conn);
		return isModifySuccess;
	}

}
