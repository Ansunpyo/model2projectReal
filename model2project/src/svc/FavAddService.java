package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.FavoritesDAO;

public class FavAddService {

	public int addFav(String id, int lecture_num) {
		Connection conn = getConnection();
		FavoritesDAO favoritesDAO = FavoritesDAO.getInstance();
		favoritesDAO.setConnection(conn);
		int result = favoritesDAO.addFavorites(id, lecture_num);
		close(conn);
		
		return result;
	}

}
