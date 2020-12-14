package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MembersDAO;

public class QuittersLastPageService {

	public int getQuittersLastPage() {
		Connection conn = getConnection();
		MembersDAO membersDAO = MembersDAO.getInstance();
		membersDAO.setConnection(conn);
		int lastPage = membersDAO.getQuittersNumber();
		close(conn);
		
		return lastPage;
	}

}
