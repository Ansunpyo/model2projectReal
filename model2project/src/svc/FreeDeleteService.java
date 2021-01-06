package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.FreeDAO;

public class FreeDeleteService {

	public int deleteFree(int free_num) {
		int result = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			FreeDAO freeDAO = FreeDAO.getInstance();
			freeDAO.setConnection(conn);
			result = freeDAO.deleteFree(free_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return result;
	}

}
