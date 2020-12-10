package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.FavoritesDAO;

public class FavoritesDeleteService {

	public int deleteFavorites(String id, String lecture_num) {

		Connection conn = getConnection();
		FavoritesDAO favoritesDAO = FavoritesDAO.getInstance();
		favoritesDAO.setConnection(conn);
		int result = favoritesDAO.deleteFavorites(id, lecture_num);
		close(conn);
		
		return result;
	}

}
