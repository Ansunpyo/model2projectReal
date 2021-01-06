package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.OneOnOneDAO;

public class OneOnOneLastPageService {

	public int getOneOnOneLastPage() {
		int lastPage = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			OneOnOneDAO oneOnOneDAO = OneOnOneDAO.getInstance();
			oneOnOneDAO.setConnection(conn);
			lastPage = oneOnOneDAO.getOneOnOneNumber();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return lastPage;
	}

}
