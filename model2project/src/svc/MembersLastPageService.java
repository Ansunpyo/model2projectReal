package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MembersDAO;

public class MembersLastPageService {
	public int getMembersLastPage() {
		int lastPage = 0;
		Connection conn = null;
		try {
			conn = getConnection();
			MembersDAO membersDAO = MembersDAO.getInstance();
			membersDAO.setConnection(conn);
			lastPage = membersDAO.getMembersNumber();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) close(conn);
		}
		
		return lastPage;
	}

}