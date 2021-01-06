package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.IntroDAO;

public class IntroListService {

	public int getListCount() {
		int listCount=0;
		Connection conn= null;
		try {
			conn = getConnection();
			IntroDAO introDAO=IntroDAO.getInstance();
			introDAO.setConnection(conn);
			listCount=introDAO.selectListCount();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return listCount;
	}

	public ArrayList[] getArticleList(int page, int limit) {
		ArrayList[] articleList=null;
		Connection conn = null;
		try {
			conn = getConnection();
			IntroDAO introDAO=IntroDAO.getInstance();
			introDAO.setConnection(conn);
			articleList=introDAO.selectArticleList(page, limit);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return articleList;
	
	}
}
