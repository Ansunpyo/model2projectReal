package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.FreeDAO;

public class FreeDeleteService {

	public int deleteFree(int free_num) {
		Connection conn = getConnection();
		FreeDAO freeDAO = FreeDAO.getInstance();
		freeDAO.setConnection(conn);
		int result = freeDAO.deleteFree(free_num);
		close(conn);
		
		return result;
	}

}
