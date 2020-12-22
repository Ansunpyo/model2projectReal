package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.LinkedList;

import dao.FavoritesDAO;

public class FavoritesListService {

	public LinkedList[] getFavoritesList(String id) {
		Connection conn = getConnection();
		FavoritesDAO favoritesDAO = FavoritesDAO.getInstance();
		favoritesDAO.setConnection(conn);
		LinkedList[] favorList = favoritesDAO.selectFavoritesList(id);
		close(conn);
		
		return favorList;
	}

	public LinkedList<Integer> getFavoritesList(String id, LinkedList[] lectureList) {
		Connection conn = getConnection();
		FavoritesDAO favoritesDAO = FavoritesDAO.getInstance();
		favoritesDAO.setConnection(conn);
		LinkedList<Integer> favorList = favoritesDAO.selectFavoritesList(id, lectureList);
		close(conn);
		
		return favorList;
	}
}