package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import dao.IntroDAO;
import vo.Intro;

public class IntroDetailService {

	public ArrayList[] getArticle(int intro_num) {
		ArrayList[] articleList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			IntroDAO introDAO = IntroDAO.getInstance();
			introDAO.setConnection(conn);
			int updateCount = introDAO.updateReadCount(intro_num);
			
			if(updateCount > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
			
			articleList = introDAO.selectArticle(intro_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return articleList;
	}

	public Intro getArticleForModify(int intro_num) {
		Intro intro = null;
		Connection conn = null;
		try {
			conn = getConnection();
			IntroDAO introDAO = IntroDAO.getInstance();
			introDAO.setConnection(conn);
			intro = introDAO.selectArticleForModify(intro_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return intro;
	}

}
