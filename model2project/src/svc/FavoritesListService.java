package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.FavoritesDAO;

public class FavoritesListService {

	public LinkedList[] getFavoritesList(String id) {
		LinkedList[] favorList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			FavoritesDAO favoritesDAO = FavoritesDAO.getInstance();
			favoritesDAO.setConnection(conn);
			favorList = favoritesDAO.selectFavoritesList(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return favorList;
	}

	public LinkedList<Integer> getFavoritesList(String id, LinkedList[] lectureList) {
		LinkedList<Integer> favorList = null;
		Connection conn = null;
		try {
			conn = getConnection();
			FavoritesDAO favoritesDAO = FavoritesDAO.getInstance();
			favoritesDAO.setConnection(conn);
			favorList = favoritesDAO.selectFavoritesList(id, lectureList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return favorList;
	}
}