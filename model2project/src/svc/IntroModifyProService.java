package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.IntroDAO;
import vo.Intro;

public class IntroModifyProService {

	public boolean isArticleWriter(int intro_num, String password) {
		boolean isArticleWriter = false;
		Connection conn = null;
		try {
			conn = getConnection();
			IntroDAO introDAO = IntroDAO.getInstance();
			introDAO.setConnection(conn);
			isArticleWriter = introDAO.isArticleIntroWriter(intro_num, password);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return isArticleWriter;
	}

	public boolean modifyArticle(Intro article) {
		boolean isModifySuccess = false;
		Connection conn = null;
		try {
			conn = getConnection();
			IntroDAO introDAO = IntroDAO.getInstance();
			introDAO.setConnection(conn);
			int updateCount = introDAO.updateArticle(article);
			
			if(updateCount > 0) {
				commit(conn);
				isModifySuccess = true;
			} else {
				rollback(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return isModifySuccess;
	}

}
