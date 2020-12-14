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
}