package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.OneOnOneDAO;

public class OneOnOneLastPageService {

	public int getOneOnOneLastPage() {
		Connection conn = getConnection();
		OneOnOneDAO oneOnOneDAO = OneOnOneDAO.getInstance();
		oneOnOneDAO.setConnection(conn);
		int lastPage = oneOnOneDAO.getOneOnOneNumber();
		close(conn);
		
		return lastPage;
	}

}
