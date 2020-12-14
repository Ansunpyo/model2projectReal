package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MembersDAO;

public class MembersLastPageService {
	public int getMembersLastPage() {
		Connection conn = getConnection();
		MembersDAO membersDAO = MembersDAO.getInstance();
		membersDAO.setConnection(conn);
		int lastPage = membersDAO.getMembersNumber();
		close(conn);
		
		return lastPage;
	}

}