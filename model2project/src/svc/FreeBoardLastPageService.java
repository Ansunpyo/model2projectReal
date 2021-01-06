package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.FreeDAO;

public class FreeBoardLastPageService {

	public int getFreeLastPage() {
		int lastPage = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			FreeDAO freeDAO = FreeDAO.getInstance();
			freeDAO.setConnection(conn);
			lastPage = freeDAO.getFreeNumber();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return lastPage;
	}

}
