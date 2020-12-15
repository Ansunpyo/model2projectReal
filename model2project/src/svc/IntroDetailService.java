package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import dao.IntroDAO;
import vo.Intro;

public class IntroDetailService {

	public ArrayList[] getArticle(int intro_num) {
		Connection conn = getConnection();
		IntroDAO introDAO = IntroDAO.getInstance();
		introDAO.setConnection(conn);
		int updateCount = introDAO.updateReadCount(intro_num);
		
		if(updateCount > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		ArrayList[] articleList = introDAO.selectArticle(intro_num);
		if(conn != null) close(conn);
		
		return articleList;
	}

	public Intro getArticleForModify(int intro_num) {
		Connection conn = getConnection();
		IntroDAO introDAO = IntroDAO.getInstance();
		introDAO.setConnection(conn);
		Intro intro = introDAO.selectArticleForModify(intro_num);
		if(conn != null) close(conn);
		
		return intro;
	}

}
