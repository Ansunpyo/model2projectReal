package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.FavoritesDAO;

public class FavAddService {

	public int addFav(String id, int lecture_num) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			FavoritesDAO favoritesDAO = FavoritesDAO.getInstance();
			favoritesDAO.setConnection(conn);
			result = favoritesDAO.addFavorites(id, lecture_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

}
