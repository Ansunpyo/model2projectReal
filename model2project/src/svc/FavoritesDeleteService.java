package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.FavoritesDAO;

public class FavoritesDeleteService {

	public int deleteFavorites(String id, String lecture_num) {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			FavoritesDAO favoritesDAO = FavoritesDAO.getInstance();
			favoritesDAO.setConnection(conn);
			result = favoritesDAO.deleteFavorites(id, lecture_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

}
