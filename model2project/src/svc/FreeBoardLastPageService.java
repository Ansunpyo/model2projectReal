package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.FreeDAO;

public class FreeBoardLastPageService {

	public int getFreeLastPage() {
		Connection conn = getConnection();
		FreeDAO freeDAO = FreeDAO.getInstance();
		freeDAO.setConnection(conn);
		int lastPage = freeDAO.getFreeNumber();
		close(conn);
		
		return lastPage;
	}

}
